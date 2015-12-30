
public class SettingsStringKey implements SettingsKey {

    private String key;
    private String name;

    public SettingsStringKey(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String print(String tab) {
        return tab + name + " = " + key + "(String)\n";
    }
}
