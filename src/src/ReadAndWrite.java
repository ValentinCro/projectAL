import java.util.ArrayList;
import java.util.List;

public interface ReadAndWrite {
    void getSettings();

    void save(ArrayList<SettingsKey> keys);

    void addKey(List<SettingsKey> keys, String key);

    boolean removeKey(List<SettingsKey> keys, String key);

    boolean removeGroup(List<SettingsKey> keys, String key);

    Object getValueBySettingKey(List<SettingsKey> keys, String key);

    SettingsKey getKeyByGroupKey(List<SettingsKey> keys, String key);
}
