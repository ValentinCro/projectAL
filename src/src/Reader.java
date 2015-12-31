import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    � demander le chargement de l�ensemble des r�glages � partir d�un fichier,

    � obtenir une valeur � partir � partir de sa cl� de r�glage,

    � obtenir une r�f�rence � un groupe � partir sa cl� de groupe,

    � acc�der � un groupe ou une valeur de mani�re relative, c�est � dire � partir d�un autre groupe,
*/
public interface Reader {

    ArrayList<SettingsKey> getSettings();

    Object getValueBySettingKey(List<SettingsKey> keys, String key);

    SettingsKey getKeyByGroupKey(List<SettingsKey> keys, String key);
}
