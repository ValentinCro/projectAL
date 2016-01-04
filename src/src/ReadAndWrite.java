import java.util.List;

public interface ReadAndWrite {
    /**
     * @return All settingKeys in a List
     * call Reader.getSettings
     */
    List<SettingsKey> getSettings();

    /**
     * @param keys
     * call Writer.save
     */
    void save(List<SettingsKey> keys);

    /**
     * @param keys
     * @param key
     * call Writer.addKey
     */
    void addKey(List<SettingsKey> keys, String key);

    /**
     *
     * @param keys
     * @param key
     * @return if the method work or not
     * call Writer.removeKey
     */
    boolean removeKey(List<SettingsKey> keys, String key);

    /**
     * @param keys
     * @param key
     * @return if the method work or not
     * call Writer.removeGroup
     */
    boolean removeGroup(List<SettingsKey> keys, String key);

    /**
     * @param keys
     * @param key
     * @return the key value
     * call Reader.getValueBySettingKey
     */
    Object getValueBySettingKey(List<SettingsKey> keys, String key);

    /**
     * @param keys
     * @param key
     * @return return the groupKey object
     * call Reader.getKeyByGroupKey
     */
    SettingsKey getKeyByGroupKey(List<SettingsKey> keys, String key);
}
