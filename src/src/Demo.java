import java.util.ArrayList;

public class Demo {
    public static void main(String [] args) {
        ReadAndWrite raw = new ConcretReadAndWrite("settings.json");
        ArrayList<SettingsKey> keys = new ArrayList<>();

        SettingsGroupKey calcul = new SettingsGroupKey("calcul");
        SettingsGroupKey algo1 = new SettingsGroupKey("algo1");
        SettingsKey maxIter = new SettingsIntegerKey("maxIter", 3);
        SettingsKey maxIter2 = new SettingsIntegerKey("maxIter2", 3);
        algo1.addKey(maxIter);
        algo1.addKey(maxIter2);
        SettingsGroupKey algo2 = new SettingsGroupKey("algo2");
        algo2.addKey(maxIter);
        algo2.addKey(maxIter2);
        calcul.addKey(algo1);
        calcul.addKey(algo2);

        keys.add(calcul);

        raw.save(keys);
    }
}
