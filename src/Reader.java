
import java.util.List;

/*
    — demander le chargement de l’ensemble des réglages à partir d’un fichier,

    — obtenir une valeur à partir à partir de sa clé de réglage,

    — obtenir une référence à un groupe à partir sa clé de groupe,

    — accéder à un groupe ou une valeur de manière relative, c’est à dire à partir d’un autre groupe,
*/
public interface Reader {
    /**
     * Return a list with all settings
     * @return
     */
    List<SettingsKey> getSettings();

    /**
     * @param keys
     * @param key
     * @return the value of the key specified
     * the key is specified by the way of acces
     * exemple : calcul.algo1.maxIter
     */
    Object getValueBySettingKey(List<SettingsKey> keys, String key);

    /**
     * @param keys
     * @param key
     * @return the group key specified
     * the key is specified by the way of acces
     * exemple : calcul.algo1
     */
    SettingsKey getKeyByGroupKey(List<SettingsKey> keys, String key);
}
