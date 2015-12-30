
public class Display {
    public static void main(String []args) {
        OnlyRead read = new ConcretOnlyRead(args[0]);

        read.getSettings();
    }
}
