import com.sun.org.apache.bcel.internal.generic.NEW;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class ReaderJSON implements Reader {
    private String fileName;

    public ReaderJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<SettingsKey> getSettings() {
        ArrayList<SettingsKey> keys = new ArrayList<>();

        String text = "";
        String line = null;
        try {
            FileReader fr = new FileReader(new File(fileName));
            try{
                BufferedReader br = new BufferedReader(fr);
                // open input stream test.txt for reading purpose.
                while ((line = br.readLine()) != null) {
                    text += line;
                }
                br.close();
                fr.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            JSONObject obj = new JSONObject(text);
            keys.addAll(parseJSON(obj));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return keys;
    }

    private ArrayList<SettingsKey> parseJSON(JSONObject obj) {
        ArrayList<SettingsKey> keys = new ArrayList<>();
        for (String key : obj.keySet()) {
            if (obj.optInt(key, -1) != -1) {
                    keys.add(new SettingsIntegerKey(key, obj.getInt(key)));
            } else if (obj.optDouble(key, -1.0) != -1.0) {
                    keys.add(new SettingsFloatKey(key, obj.getDouble(key)));
            } else {
                if (obj.optJSONObject(key) != null) {
                    JSONObject object = obj.optJSONObject(key);
                    SettingsGroupKey gk = new SettingsGroupKey(key);
                    ArrayList<SettingsKey> tmp = parseJSON(object);
                    for(SettingsKey k : tmp) {
                        gk.addKey(k);
                    }
                    keys.add(gk);
                } else {
                    keys.add(new SettingsStringKey(key, obj.get(key).toString()));
                }
            }
        }
        return keys;
    }

    @Override
    public Object getKeyBySettingKey(String key) {
        ArrayList<SettingsKey> keys = getSettings();
        for (SettingsKey k : keys) {
            if (k.getName().equals(key)) {
                return k.getKey();
            }
        }
        return null;
    }

    @Override
    public Object getKeyByGroupKey(String key) {
        ArrayList<SettingsKey> keys = getSettings();
        for (SettingsKey k : keys) {
            if (k.getName().equals(key)) {
                return k.getKey();
            }
        }
        return null;
    }
}
