import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewEdit {

    private JFrame mainFrame;
    private JTree table;
    private ReadAndWrite raw;
    private ArrayList<SettingsKey> keys;
    private JButton createKey;

    public ViewEdit() {
        raw = new ConcretReadAndWrite("settings.json");

        createView();
        createController();
        placeComponnent();
    }

    public void createView() {
        mainFrame = new JFrame("EditSettings");

        table = new JTree(new DefaultTreeModel(initSetting()));

        createKey = new JButton("Créer une clé");
    }

    public void createController() {

    }

    public void  placeComponnent() {
        JPanel p = new JPanel(new BorderLayout()); {
            p.add(table, BorderLayout.CENTER);
            JPanel l = new JPanel(); {
                l.add(createKey);
            }
            p.add(l, BorderLayout.NORTH);
        }

        mainFrame.add(p);
    }

    public void display() {
        mainFrame.pack();
        mainFrame.setSize(400, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public DefaultMutableTreeNode initSetting() {
        keys = raw.getSettings();
        DefaultMutableTreeNode r = new DefaultMutableTreeNode("Settings");
        for ( SettingsKey k : keys) {
            if (k.getClass() == SettingsGroupKey.class) {
                DefaultMutableTreeNode f = new DefaultMutableTreeNode(k.getName());
                ArrayList<DefaultMutableTreeNode> childs = child((SettingsGroupKey) k);
                for (DefaultMutableTreeNode ch : childs) {
                    f.add(ch);
                }
                r.add(f);
            } else {
                r.add(new DefaultMutableTreeNode(k.getName() + " : " + k.getKey()));
            }
        }
        return r;
    }

    public ArrayList<DefaultMutableTreeNode> child(SettingsGroupKey father) {
        ArrayList<DefaultMutableTreeNode> childes = new ArrayList<>();
        for ( SettingsKey k : father.getKeys()) {
            if (k.getClass() == SettingsGroupKey.class) {
                DefaultMutableTreeNode f = new DefaultMutableTreeNode(k.getName());
                ArrayList<DefaultMutableTreeNode> childs = child((SettingsGroupKey) k);
                for (DefaultMutableTreeNode ch : childs) {
                    f.add(ch);
                }
                childes.add(f);
            } else {
                childes.add(new DefaultMutableTreeNode(k.getName() + " : " + k.getKey()));
            }
        }

        return childes;
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewEdit().display();
            }
        });
    }
}
