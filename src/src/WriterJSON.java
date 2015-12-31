import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterJSON implements Writer {

    String fileName;

    public WriterJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(ArrayList<SettingsKey> keys) {
        ArrayList<JSONObject> JSONkeys = new ArrayList<>();
        for (SettingsKey key : keys) {
            JSONObject obj = new JSONObject();
            if (key.getClass() == SettingsGroupKey.class) {
                obj.put(key.getName(), parseKey(((SettingsGroupKey) key).getKeys()));
            } else {
                obj.put(key.getName(), key.getKey());
            }
            JSONkeys.add(obj);
        }
        String save = "";
        for (int cpt = 0; cpt < JSONkeys.size(); cpt++) {
            String ch = JSONkeys.get(cpt).toString().substring(1, JSONkeys.get(cpt).toString().length()-1);
            save += ch;
            if (cpt + 1 < JSONkeys.size()) {
                save += ",\n";
            }
        }

        try {
            FileWriter fw  = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("{" + save + "}");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject parseKey(List<SettingsKey> keys) {
        JSONObject obj = new JSONObject();
        for (SettingsKey key : keys) {
            if (key.getClass() == SettingsGroupKey.class) {
                obj.put(key.getName(), parseKey(((SettingsGroupKey) key).getKeys()));
            } else {
                obj.put(key.getName(),  key.getKey());
            }
        }
        return obj;
    }

    @Override
    public void addKey(List<SettingsKey> keys, String key) {
        String [] var = key.split(" ");
        String [] splited = var[0].split("\\.");
        var[0] = splited[splited.length - 1];
        boolean find = false;
        int i = 0;
        if (splited.length > 1) {
            SettingsGroupKey sgk = new SettingsGroupKey("");
            sgk.setKeys(keys);
            for (int cpt = 0; cpt < splited.length; cpt++) {
                find = false;
                for (int cpt2 = 0; cpt2 < sgk.getKeys().size(); cpt2++) {
                    if (cpt < splited.length - 1) {
                        if (sgk.getKeys().get(cpt2).getName().equals(splited[cpt])) {
                            sgk = (SettingsGroupKey) sgk.getKeys().get(cpt2);
                            i++;
                            find = true;
                        }
                    } else {
                        if (sgk.getKeys().get(cpt2).getName().equals(var[0])) {
                            sgk.getKeys().remove(sgk.getKeys().get(cpt2));
                            sgk.addKey(createKey(var[0], var[1]));
                            find = true;
                        }
                    }
                }
                if (!find) {
                    break;
                }
            }
            if (!find) {
                System.out.print(i);
                for (int cpt = i; cpt < splited.length; cpt++) {
                    if (cpt < splited.length - 1) {
                        SettingsGroupKey tmp = new SettingsGroupKey(splited[cpt]);
                        sgk.addKey(tmp);
                        sgk = tmp;
                    } else {
                        sgk.addKey(createKey(var[0], var[1]));
                    }
                }
            }
        } else {
            for (SettingsKey k : keys) {
                if (k.getName().equals(var[0])) {
                    k.setKey(var[1]);
                    find = true;
                }
            }
            if (!find) {
                keys.add(createKey(var[0], var[1]));
            }
        }
    }

    @Override
    public boolean removeValue(List<SettingsKey> keys, String key) {
        String [] splited = key.split("\\.");
        boolean find = false;
        if (splited.length > 1) {
            SettingsGroupKey sgk = new SettingsGroupKey("");
            sgk.setKeys(keys);
            for (int cpt = 0; cpt < splited.length; cpt++) {
                for (int cpt2 = 0; cpt2 < sgk.getKeys().size(); cpt2++) {
                    if (sgk.getKeys().get(cpt2).getName().equals(splited[cpt])) {
                        if (cpt == splited.length - 1) {
                            sgk.getKeys().remove(cpt2);
                            find = true;
                        } else {
                            sgk = (SettingsGroupKey) sgk.getKeys().get(cpt2);
                        }
                    }
                }
            }
        } else {
            for (int cpt = 0; cpt < keys.size(); cpt++) {
                if (keys.get(cpt).getName().equals(key)) {
                    keys.remove(cpt);
                    find = true;
                }
            }
        }
        return find;
    }

    @Override
    public boolean removeGroup(List<SettingsKey> keys, String key) {
        String [] splited = key.split("\\.");
        boolean find = false;
        SettingsGroupKey sgk = new SettingsGroupKey("");
        sgk.setKeys(keys);
        for (int cpt = 0; cpt < splited.length; cpt++) {
            for (int cpt2 = 0; cpt2 < sgk.getKeys().size(); cpt2++) {
                if (sgk.getKeys().get(cpt2).getName().equals(splited[cpt])) {
                    if (cpt == splited.length - 1) {
                        sgk.getKeys().remove(cpt2);
                        find = true;
                    } else {
                        sgk = (SettingsGroupKey) sgk.getKeys().get(cpt2);
                    }
                }
            }
        }
        return find;
    }

    private SettingsKey createKey(String name, Object value) {
        SettingsKey sg = null;
        try
        {
            sg = new SettingsIntegerKey(name, Integer.parseInt(value.toString()));
        }
        catch(NumberFormatException e)
        {
            try {
                sg = new SettingsFloatKey(name, Double.parseDouble(value.toString()));
            } catch(NumberFormatException q) {
                sg = new SettingsStringKey(name, value.toString());
            }

        }
        return sg;
    }
}
