import java.util.List;

/*
    � demander la sauvegarde de l�ensemble des r�glages vers un fichier,

    � ajouter ou modifier une valeur � partir � partir de sa cl� de r�glage et d�une nouvelle valeur,

    � supprimer une valeur � partir � partir de sa cl� de r�glage,

    � supprimer un groupe � partir sa cl� de groupe,
*/
public interface Writer {
    /**
     * Save All list's settings
     * @param keys
     */
    void save(List<SettingsKey> keys);

    /**
     * @param keys
     * @param key
     * Add a key to the List
     * The keys is specified by a way
     * exemple calcul.algo1.maxIter value
     * If the key already exist it modified the existing key
     */
    void addKey(List<SettingsKey> keys, String key);

    /**
     * @param keys
     * @param key
     * @return if the operation work
     * Remove a key to the List
     * The keys is specified by a way
     * exemple calcul.algo1.maxIter
     */
    boolean removeValue(List<SettingsKey> keys, String key);

    /**
     * @param keys
     * @param key
     * @return  if the operation work
     * Add a group key to the List
     * The keys is specified by a way
     * exemple calcul.algo1
     */
    boolean removeGroup(List<SettingsKey> keys, String key);
}
