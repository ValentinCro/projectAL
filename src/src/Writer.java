import java.util.ArrayList;

/*
    — demander la sauvegarde de l’ensemble des réglages vers un fichier,

    — ajouter ou modifier une valeur à partir à partir de sa clé de réglage et d’une nouvelle valeur,

    — supprimer une valeur à partir à partir de sa clé de réglage,

    — supprimer un groupe à partir sa clé de groupe,
*/
public interface Writer {

    void save(ArrayList<SettingsKey> keys);

    boolean addKey(SettingsKey key);

    boolean removeValue(SettingsKey key);

    boolean removeGroup(SettingsGroupKey key);
}
