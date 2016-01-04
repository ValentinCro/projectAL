import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Edit {

    private JFrame mainFrame;
    private JTree table;
    private ReadAndWrite raw;
    private List<SettingsKey> keys;
    private JButton createKey;
    private JButton removeKey;
    private JButton save;
    private JTextField nameKey;
    private JPanel panelTree;

    public Edit(String nameFile) {
        raw = new ConcretReadAndWrite(nameFile);
        keys = raw.getSettings();

        createView();
        createController();
        placeComponnent();
    }

    public void createView() {
        mainFrame = new JFrame("EditSettings");
        panelTree = new JPanel(new BorderLayout());
        table = new JTree(new DefaultTreeModel(initSetting(Edit.this.keys)));
        nameKey = new JTextField("[clé de groupe].nom clé valeur");
        nameKey.setColumns(15);
        createKey = new JButton("Ajouter/modifier une clé");
        removeKey = new JButton("Supprimer une clé");
        save = new JButton("Sauvegarder");
    }

    public void createController() {
        createKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit.this.raw.addKey(Edit.this.keys, Edit.this.nameKey.getText());
                table = new JTree(new DefaultTreeModel(initSetting(Edit.this.keys)));
                panelTree.removeAll();
                panelTree.add(table);

                panelTree.revalidate();
            }
        });
        removeKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Edit.this.nameKey.getText().contains(" ")) {
                    Edit.this.raw.removeKey(Edit.this.keys, Edit.this.nameKey.getText());
                } else {
                    Edit.this.raw.removeGroup(Edit.this.keys, Edit.this.nameKey.getText());
                }
                table = new JTree(new DefaultTreeModel(initSetting(Edit.this.keys)));
                panelTree.removeAll();
                panelTree.add(table);

                panelTree.revalidate();
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit.this.raw.save(keys);
            }
        });
    }

    public void  placeComponnent() {
        JPanel p = new JPanel(new BorderLayout()); {
            panelTree.add(table);
            p.add(panelTree, BorderLayout.CENTER);
            JPanel l = new JPanel(); {
                l.add(nameKey);
                l.add(createKey);
                l.add(removeKey);
            }
            p.add(l, BorderLayout.NORTH);
            l = new JPanel(); {
                l.add(save);
            }
            p.add(l, BorderLayout.SOUTH);
        }

        mainFrame.add(p);
    }

    public void display() {
        mainFrame.pack();
        mainFrame.setSize(800, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public DefaultMutableTreeNode initSetting(List<SettingsKey> keys) {
        DefaultMutableTreeNode r = new DefaultMutableTreeNode("Settings");
        for ( SettingsKey k : keys) {
            if (k.getClass() == SettingsGroupKey.class) {
                DefaultMutableTreeNode f = new DefaultMutableTreeNode(k.getName());
                List<DefaultMutableTreeNode> childs = child((SettingsGroupKey) k);
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

    public List<DefaultMutableTreeNode> child(SettingsGroupKey father) {
        List<DefaultMutableTreeNode> childes = new ArrayList<>();
        for ( SettingsKey k : father.getKeys()) {
            if (k.getClass() == SettingsGroupKey.class) {
                DefaultMutableTreeNode f = new DefaultMutableTreeNode(k.getName());
                List<DefaultMutableTreeNode> childs = child((SettingsGroupKey) k);
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
                new Edit(args[0]).display();
            }
        });
    }
}
