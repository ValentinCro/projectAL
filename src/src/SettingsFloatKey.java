
public class SettingsFloatKey implements SettingsKey {

    private Double key;
    private String name;

    public SettingsFloatKey(String name, Double key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public void setKey(Object key) {
        this.key = Double.valueOf(key.toString());
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
        return tab + name + " = " + key + " (Float)\n";
    }
}
