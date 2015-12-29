
public class Display {
    public static void main(String []args) {
        OnlyRead read = new ConcretOnlyRead("settings.json");

        read.getSettings();
    }
}
