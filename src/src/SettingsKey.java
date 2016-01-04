/**
 * Interface SettingsKey define a contract about the usability of keys, regardless of their type.
 * @see SettingsFloatKey
 * @see SettingsStringKey
 * @see SettingsGroupKey
 * @see SettingsIntegerKey
 */
public interface SettingsKey {
    /**
     * getKey is used to return the value of the key with name getName.
     * @return the value of the key.
     */
    Object getKey();

    /**
     * setKey is used to modify the value of the key named getName.
     * @param key The value to add or modify to the key named getName.
     */
    void setKey(Object key);

    /**
     * getName is used to return the name of the key.
     * @return The name of the key.
     */
    String getName();

    /**
     * setName is a setter to modify the name of the key.
     * @param name The name of key to set.
     */
    void setName(String name);

    /**
     * toString is here to print the value and the name of the key.
     * @param tab String to add tabulations for the output to build a tree.
     * @return A string build with tab, the name and the value.
     */
    String toString(String tab);
}
