
public class SettingsStringKey implements SettingsKey {

    private String key;
    private String name;

    public SettingsStringKey(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return name + " " + key + "\n";
    }
}
