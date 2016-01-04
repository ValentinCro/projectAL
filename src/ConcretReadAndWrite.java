import java.util.List;

public class ConcretReadAndWrite implements ReadAndWrite {

    private Reader reader;
    private Writer writer;

    public ConcretReadAndWrite(String fileName) {
        reader = new ReaderJSON(fileName);
        writer = new WriterJSON(fileName);
    }

    @Override
    public List<SettingsKey> getSettings() {
        return reader.getSettings();
    }

    @Override
    public void save(List<SettingsKey> keys) {
        writer.save(keys);
    }

    @Override
    public void addKey(List<SettingsKey> keys, String key) {
        writer.addKey(keys, key);
    }

    @Override
    public boolean removeKey(List<SettingsKey> keys, String key) {
        return writer.removeValue(keys, key);
    }

    @Override
    public boolean removeGroup(List<SettingsKey> keys, String key) {
        return writer.removeGroup(keys, key);
    }

    @Override
    public Object getValueBySettingKey(List<SettingsKey> keys, String key) {
        return reader.getValueBySettingKey(keys, key);
    }

    @Override
    public SettingsKey getKeyByGroupKey(List<SettingsKey> keys, String key) {
        return reader.getKeyByGroupKey(keys, key);
    }

}
