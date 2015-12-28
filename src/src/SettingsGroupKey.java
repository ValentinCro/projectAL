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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        String ch = name + ".";

        for(SettingsKey key : keys) {
            ch += key.print();
        }

        return ch;
    }
}
