
public class SettingsFloatKey implements SettingsKey {

    private float key;
    private String name;

    public SettingsFloatKey(String name, float key) {
        this.name = name;
        this.key = key;
    }

    public float getKey() {
        return key;
    }

    public void setKey(float key) {
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
