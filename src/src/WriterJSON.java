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
            save += JSONkeys.get(cpt).toString();
            if (cpt + 1 < JSONkeys.size()) {
                save += ",\n";
            }
        }

        try {
            FileWriter fw  = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(save);
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
        String [] splited = key.split("\\.");
        String [] var = splited[splited.length - 1].split(" ");
        boolean find = false;
        if (splited.length > 1) {
            SettingsGroupKey sgk = new SettingsGroupKey("");
            sgk.setKeys(keys);
            for (int cpt = 0; cpt < splited.length; cpt++) {
                find = false;
                for (SettingsKey k : sgk.getKeys()) {
                    if (cpt < splited.length - 1) {
                        if (k.getName().equals(splited[cpt])) {
                            sgk = (SettingsGroupKey) k;
                        }
                        find = true;
                    } else {
                        if (k.getName().equals(var[0])) {
                            k.setKey(var[1]);
                            find = true;
                        }
                    }
                }
                if (!find) {
                    break;
                }
            }
            if (!find) {
                sgk = new SettingsGroupKey(splited[0]);
                keys.add(sgk);
                for (int cpt = 1; cpt < splited.length; cpt++) {
                    if (cpt < splited.length - 1) {
                        SettingsGroupKey tmp = new SettingsGroupKey(splited[cpt]);
                        sgk.addKey(tmp);
                        sgk = tmp;
                    } else {
                        try
                        {
                            sgk.addKey(new SettingsIntegerKey(var[0], Integer.parseInt(var[1])));
                        }
                        catch(NumberFormatException e)
                        {
                            try {
                                sgk.addKey(new SettingsFloatKey(var[0], Double.parseDouble(var[1])));
                            } catch(NumberFormatException q) {
                                sgk.addKey(new SettingsStringKey(var[0], var[1]));
                            }

                        }
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
                try
                {
                    keys.add(new SettingsIntegerKey(var[0], Integer.parseInt(var[1])));
                }
                catch(NumberFormatException e)
                {
                    try {
                        keys.add(new SettingsFloatKey(var[0], Double.parseDouble(var[1])));
                    } catch(NumberFormatException q) {
                        keys.add(new SettingsStringKey(var[0], var[1]));
                    }

                }
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
}
