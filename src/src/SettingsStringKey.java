
public class SettingsStringKey implements SettingsKey {

    private String key;
    private String name;

    public SettingsStringKey(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public void setKey(Object key) {
        this.key = key.toString();
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
        return tab + name + " = " + key + "(String)\n";
    }
}
