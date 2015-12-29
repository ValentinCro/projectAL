import java.io.FileNotFoundException;

public class ConcretOnlyRead implements OnlyRead {

    private Reader reader;

    public ConcretOnlyRead(String fileName) {
        reader = new ReaderJSON(fileName);
    }

    @Override
    public void getSettings() {
        for (SettingsKey key : reader.getSettings()) {
            System.out.print(key.print(""));
        }
    }
}
