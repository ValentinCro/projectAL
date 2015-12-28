import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.parser.*;

public class ReaderJSON implements Reader {
    String fileName;

    public ReaderJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<SettingsKey> getSettings() throws FileNotFoundException {
        ArrayList<SettingsKey> keys = new ArrayList<>();

        String text = "";
        String line = null;
        try{
            FileReader fr = new FileReader(new File(fileName));
            BufferedReader br = new BufferedReader(fr);
            // open input stream test.txt for reading purpose.
            while ((line = br.readLine()) != null) {
               text += line;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SettingsKey getKey(SettingsKey key) {
        return null;
    }

    @Override
    public SettingsKey getKey(SettingsGroupKey key) {
        return null;
    }
}
