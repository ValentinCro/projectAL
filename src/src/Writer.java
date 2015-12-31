import java.util.ArrayList;
import java.util.List;

/*
    � demander la sauvegarde de l�ensemble des r�glages vers un fichier,

    � ajouter ou modifier une valeur � partir � partir de sa cl� de r�glage et d�une nouvelle valeur,

    � supprimer une valeur � partir � partir de sa cl� de r�glage,

    � supprimer un groupe � partir sa cl� de groupe,
*/
public interface Writer {

    void save(ArrayList<SettingsKey> keys);

    void addKey(List<SettingsKey> keys, String key);

    boolean removeValue(List<SettingsKey> keys, String key);

    boolean removeGroup(List<SettingsKey> keys, String key);
}
