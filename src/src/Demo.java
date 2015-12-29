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
        calcul.addKey(algo1);

        keys.add(calcul);
        raw.save(keys);

        for(SettingsKey sk : keys) {
            System.out.println(sk.print(""));
        }

        System.out.println("maxIter vaut : " + raw.getValueBySettingKey(keys, "calcul.algo1.maxIter2"));
        System.out.println("algo1 : " + raw.getKeyByGroupKey(keys, "calcul").getName());

        System.out.println("Modifification de calcul.algo1.maxIter à 5 et ajout de Iter");
        raw.addKey(keys, "calcul.algo1.maxIter 5");
        raw.addKey(keys, "Iter test");

        for(SettingsKey sk : keys) {
            System.out.println(sk.print(""));
        }

        System.out.println("Suppression de maxIter2 : " + raw.removeKey(keys, "calcul.algo1.maxIter2"));
        System.out.println("Suppression de Iter : " + raw.removeKey(keys, "Iter"));
        for(SettingsKey sk : keys) {
            System.out.println(sk.print(""));
        }

        System.out.println("Suppression du groupe calcul : " + raw.removeKey(keys, "calcul"));
        for(SettingsKey sk : keys) {
            System.out.println(sk.print(""));
        }
    }
}
