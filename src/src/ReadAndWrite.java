import java.util.ArrayList;

public interface ReadAndWrite {
    void getSettings();

    void save(ArrayList<SettingsKey> keys);
}
