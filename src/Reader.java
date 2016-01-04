
import java.util.List;

/*
    � demander le chargement de l�ensemble des r�glages � partir d�un fichier,

    � obtenir une valeur � partir � partir de sa cl� de r�glage,

    � obtenir une r�f�rence � un groupe � partir sa cl� de groupe,

    � acc�der � un groupe ou une valeur de mani�re relative, c�est � dire � partir d�un autre groupe,
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
