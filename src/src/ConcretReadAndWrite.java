import java.util.ArrayList;

public class ConcretReadAndWrite implements ReadAndWrite {

    private Reader reader;
    private Writer writer;

    public ConcretReadAndWrite(String fileName) {
        reader = new ReaderJSON(fileName);
        writer = new WriterJSON(fileName);
    }

    @Override
    public void getSettings() {
        for (SettingsKey key : reader.getSettings()) {
            System.out.print(key.print());
        }
    }

    @Override
    public void save(ArrayList<SettingsKey> keys) {
        writer.save(keys);
    }

}
