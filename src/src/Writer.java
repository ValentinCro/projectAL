import java.util.ArrayList;
import java.util.List;

/*
    — demander la sauvegarde de l’ensemble des réglages vers un fichier,

    — ajouter ou modifier une valeur à partir à partir de sa clé de réglage et d’une nouvelle valeur,

    — supprimer une valeur à partir à partir de sa clé de réglage,

    — supprimer un groupe à partir sa clé de groupe,
*/
public interface Writer {

    void save(ArrayList<SettingsKey> keys);

    void addKey(List<SettingsKey> keys, String key);

    boolean removeValue(List<SettingsKey> keys, String key);

    boolean removeGroup(List<SettingsKey> keys, String key);
}
