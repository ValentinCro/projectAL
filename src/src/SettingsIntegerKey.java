
public class SettingsIntegerKey implements SettingsKey {

    private Integer key;
    private String name;

    public SettingsIntegerKey(String name, Integer key) {
        this.name = name;
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = Integer.getInteger(key.toString());
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
