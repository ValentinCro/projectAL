import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    — demander le chargement de l’ensemble des réglages à partir d’un fichier,

    — obtenir une valeur à partir à partir de sa clé de réglage,

    — obtenir une référence à un groupe à partir sa clé de groupe,

    — accéder à un groupe ou une valeur de manière relative, c’est à dire à partir d’un autre groupe,
*/
public interface Reader {

    ArrayList<SettingsKey> getSettings();

    Object getValueBySettingKey(List<SettingsKey> keys, String key);

    SettingsKey getKeyByGroupKey(List<SettingsKey> keys, String key);
}
