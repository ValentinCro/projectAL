import java.util.ArrayList;

public class WriterJSON implements Writer {
    @Override
    public void save(ArrayList<SettingsKey> keys) {

    }

    @Override
    public boolean addKey(SettingsKey key) {
        return false;
    }

    @Override
    public boolean removeValue(SettingsKey key) {
        return false;
    }

    @Override
    public boolean removeGroup(SettingsGroupKey key) {
        return false;
    }
}
