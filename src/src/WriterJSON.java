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
        System.out.print(save);

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
    public boolean addKey(SettingsKey key) {
        return false;
    }

    @Override
    public boolean removeValue(SettingsKey key) {
        return false;
    }

    @Override
    public boolean removeGroup(SettingsGroupKey key) {
        return false;
    }
}
