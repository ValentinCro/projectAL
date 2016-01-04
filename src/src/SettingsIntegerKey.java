
public class SettingsIntegerKey implements SettingsKey {

    private Integer key;
    private String name;

    public SettingsIntegerKey(String name, Integer key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public void setKey(Object key) {
        this.key = Integer.valueOf(key.toString());
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
        return tab + name + " = " + key + " (Integer)\n";
    }
}
