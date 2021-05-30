import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Label {
    Main main;
    List list;
    JButton button1, button2, button3, button4, button5, button6, button7;
    JFrame labelWindow, listWindow;
    JLabel jLabel, jLabel2, jLabel3, jLabel4;
    JTextField textField;
    JPanel panel4, jPanel, jPanel2, jPanel3;
    JList labelJList, commonLabels;
    DefaultListModel labelListModel, listModel;
    JScrollPane scrollPane;
    ArrayList<JCheckBox> checkboxes;
    ArrayList<Set> sets;
    ArrayList<String> labels;
    int numberOfLabels = 0, numberOfSelectedBox = 0;
    JDialog confirmMessage;

    public Label(Main main, List list) {
        this.main = main;
        this.list = list;
    }

    public void createNewLabel() {
        labelWindow = new JFrame("Create New Label");
        labelWindow.setSize(330, 100);
        labelWindow.setLayout(new BorderLayout());
        labelWindow.setVisible(false);
        labelWindow.setLocationRelativeTo(null);

        jLabel = new JLabel("(Write a label, then click on \"Create\" button.)");
        jLabel.setFont(new Font("Bahnschrift", Font.ITALIC, 12));
        labelWindow.add(jLabel, BorderLayout.NORTH);

        textField = new JTextField();
        labelWindow.add(textField);

        button1 = new JButton("Create");
        button1.setToolTipText("Create new labels");
        button1.addActionListener(this.main);
        button1.setActionCommand("create label");
        labelWindow.add(button1, BorderLayout.SOUTH);


    }

    public void addNewLabel() { //window of adding label(s) for the to-do
        listWindow = new JFrame("Add Label"); // Window for JList
        listWindow.setSize(350, 350);
        listWindow.setLayout(new BorderLayout(0, 10));
        listWindow.setVisible(false);
        listWindow.setLocationRelativeTo(null);

        labelListModel = new DefaultListModel();// JList for labels
        labelJList = new JList(labelListModel);
        labelJList.setBackground(Color.LIGHT_GRAY);
        scrollPane = new JScrollPane(labelJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createTitledBorder(main.border, "Add Label"));
        scrollPane.setPreferredSize(new Dimension(350, 300));
        listWindow.add(scrollPane, BorderLayout.CENTER);

        jPanel = new JPanel(new BorderLayout());

        jLabel4 = new JLabel("Select a label, then click on \"Add\" button. Click on \"Save\" button to finish.");
        jLabel4.setFont(new Font("Bahnschrift", Font.ITALIC, 12));
        listWindow.add(jLabel4, BorderLayout.NORTH);

        button4 = new JButton("Add"); // Button to add labels
        button4.setToolTipText("Add a label");
        button4.addActionListener(this.main);
        button4.setActionCommand("choose label");
        button4.setPreferredSize(new Dimension(175, 30));
        jPanel.add(button4, BorderLayout.WEST);

        button5 = new JButton("Save"); // Button to save labels
        button5.setToolTipText("Save the chosen labels");
        button5.addActionListener(this.main);
        button5.setActionCommand("save label");
        button5.setPreferredSize(new Dimension(175, 30));
        jPanel.add(button5, BorderLayout.EAST);

        listWindow.add(jPanel, BorderLayout.SOUTH);

    }

    public void createLabel() {// click on create button
        if (numberOfLabels > 5) { // at most 6 labels can be added
            createConfirmMessage("Capacity of label list is full! Cannot be added new labels.");
            listWindow.dispose();
        } else {
            labelListModel.addElement(textField.getText());
            labels.add(textField.getText());
            JCheckBox box = new JCheckBox(textField.getText());//list.getTitle() + " || " + list.getDate_time()
            jPanel2.add(box);
            checkboxes.add(box);
        }
        System.out.println("Added: " + textField.getText());
        numberOfLabels++;
        System.out.println("Total number of labels: " + numberOfLabels);
        main.window.revalidate();
        main.window.repaint();
    }

    public void addLabel() { // adds labels to 'to-do'
        String value = String.valueOf(labelJList.getModel().getElementAt(labelJList.getSelectedIndex()));
        list.label.add(value);
        System.out.println("Label is added: " + value);
    }

    public void checkLabelList() {
        if (numberOfLabels == 0)
            createConfirmMessage("At first, you must create new labels.");
        else
            listWindow.setVisible(true);
    }

    public void createConfirmMessage(String msg) { // a confirm message about removing boxes
        confirmMessage = new JDialog();
        JOptionPane.showMessageDialog(confirmMessage, msg, "Warning", JOptionPane.ERROR_MESSAGE);
    }

    public void createLabelPanel() {
        panel4 = new JPanel(new BorderLayout(10, 10));
        panel4.setBorder(BorderFactory.createTitledBorder(main.border, "Label"));

        JPanel panelA = new JPanel();
        panelA.setBorder(BorderFactory.createTitledBorder(main.border, "Add"));

        jLabel2 = new JLabel("Click button to add labels to your to-do:");
        jLabel2.setFont(new Font("Bahnschrift", Font.ITALIC, 12));
        panelA.add(jLabel2);
        button2 = new JButton("Add");
        button2.setToolTipText("Add labels for the list");
        button2.setPreferredSize(new Dimension(100, 25));
        button2.addActionListener(this.main);
        button2.setActionCommand("add label");
        panelA.add(button2);
        panel4.add(panelA, BorderLayout.WEST);

        JPanel panelB = new JPanel();
        panelB.setBorder(BorderFactory.createTitledBorder(main.border, "Create"));
        jLabel3 = new JLabel("Click button to create new labels:");
        jLabel3.setFont(new Font("Bahnschrift", Font.ITALIC, 12));
        panelB.add(jLabel3);

        button3 = new JButton("Create");
        button3.setToolTipText("Create new labels to add into combo box");
        button3.setPreferredSize(new Dimension(100, 25));
        button3.addActionListener(this.main);
        button3.setActionCommand("button3");
        panelB.add(button3);
        panel4.add(panelB, BorderLayout.EAST);

        jPanel3 = new JPanel();
        button6 = new JButton("Show"); // show the 'to-do's that have common labels
        button6.setToolTipText("See 'to-do's that have the chosen labels above.");
        button6.setPreferredSize(new Dimension(100, 25));
        button6.addActionListener(this.main);
        button6.setActionCommand("see label");

        button7 = new JButton("Delete");
        button7.setToolTipText("Delete the selected labels above.");
        button7.setPreferredSize(new Dimension(100, 25));
        button7.addActionListener(this.main);
        button7.setActionCommand("delete label");

        jPanel3.add(button6);
        jPanel3.add(button7);

        jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(250, 100));
        checkboxes = new ArrayList<>();
        sets = new ArrayList<>();
        labels = new ArrayList<>();
    }

    public void deleteLabel(){ // to delete the selected check boxes
        if(checkboxes != null) {
            for (int i = 0; i < checkboxes.size(); i++) {
                if (checkboxes.get(i).isSelected()) {
                    System.out.println(checkboxes.get(i).getText() + " is removed.");
                    labelListModel.remove(i);
                    jPanel2.remove(checkboxes.get(i)); // to remove from panel
                    checkboxes.remove(i); // to remove from arraylist of check boxes
                    jPanel2.updateUI();
                    numberOfLabels--;
                }
            }
        }
    }

    public void createSets() {
        sets = new ArrayList<>();
        labels = new ArrayList<>();
        for (int j =0; j<checkboxes.size();j++) {
            labels.add(checkboxes.get(j).getText());
        }
        for (int i = 0; i < numberOfLabels; i++) {
            Set<String> set = new HashSet<>();
            for (int t = 0; t < list.list.size(); t++) {
                if (list.list.get(t).getLabel().contains(labels.get(i))) {
                    set.add(list.list.get(t).getTitle() + " || " + list.list.get(t).getDate_time());
                }
            }
            sets.add(set);
            System.out.println((i+1) + ". set: " + set);
        }
    }

    public void showLabels() {
            listModel.removeAllElements(); // remove elements from listModel
        for (int z = 0; z < numberOfLabels; z++) {
            if (checkboxes.get(z).isSelected()) {
                numberOfSelectedBox++;
            }
        }

        if (numberOfSelectedBox == 0)
            createConfirmMessage("At first, you must choose one or more of labels.");

        else {
            int i;
            Set<String> intersection = new HashSet<>();
            if (numberOfSelectedBox == 1) {
                for (i = 0; i < numberOfLabels; i++) {
                    if (checkboxes.get(i).isSelected()) {
                        Iterator<String> s = sets.get(i).iterator();
                        while (s.hasNext())
                            listModel.addElement(s.next());
                    }
                }
            } else if(numberOfSelectedBox > 1){
                for (i = 0; i < numberOfLabels; i++) {
                    if (checkboxes.get(i).isSelected()) {
                        intersection = new HashSet<>(sets.get(i));
                        break;
                    }
                }
                for (int j = i+1; j < numberOfLabels; j++) {
                    if (checkboxes.get(j).isSelected())
                        intersection.retainAll(sets.get(j));
                }
                if (intersection != null) {
                    System.out.println("Intersection: " + intersection);
                    Iterator<String> s = intersection.iterator();
                    while (s.hasNext())
                        listModel.addElement(s.next());
                } else
                    createConfirmMessage("There is no 'to-do' which has the selected labels.");
            }
        }
        commonLabels.updateUI();
        numberOfSelectedBox = 0;
    }


    public void commonLabelsList() {
        listModel = new DefaultListModel();
        commonLabels = new JList(listModel);
        commonLabels.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(commonLabels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(250, 200));
        main.panelWest.add(scrollPane, BorderLayout.CENTER);
        main.window.revalidate();
        main.window.repaint();
    }
}
