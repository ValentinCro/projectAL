
public class SettingsFloatKey implements SettingsKey {

    private Double key;
    private String name;

    public SettingsFloatKey(String name, Double key) {
        this.name = name;
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = Double.valueOf(key.toString());
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
