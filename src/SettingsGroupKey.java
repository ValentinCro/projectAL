
import java.util.ArrayList;
import java.util.List;

public class SettingsGroupKey implements SettingsKey  {

    private List<SettingsKey> keys;
    private String name;

    public SettingsGroupKey(String name) {
        this.name = name;
        this.keys = new ArrayList<>();
    }

    public void addKey(SettingsKey key) {
        keys.add(key);
    }

    @Override
    public Object getKey() {
        String ch = "";
        for(SettingsKey k : keys) {
            ch += k.getKey();
        }
        return ch;
    }

    @Override
    public void setKey(Object key) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(String tab) {
        String ch = tab + name + "\n";

        for(SettingsKey key : keys) {
            ch += tab + key.toString("\t");
        }

        return ch;
    }

    public List<SettingsKey> getKeys() {
        return keys;
    }

    public void setKeys(List<SettingsKey> keys) {
        this.keys = keys;
    }
}
