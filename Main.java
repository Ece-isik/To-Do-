import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class Main implements ActionListener {
    List list = new List(this);
    Title title = new Title(this, list);
    Label label = new Label(this, list);
    Date_Time date_time = new Date_Time(this,list);
    File file = new File(this, list);
    JFrame window;
    JPanel panelWest, panelEast;
    JTabbedPane tabbedPane;
    JScrollPane scrollPane;
    Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    DefaultListModel listModel;
    JList jList;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        window();
        createTabbedPane();
        createJList();
        createAddButtons();
        createPanelWest();
        label.createNewLabel();
        label.addNewLabel();
        window.revalidate();
        window.repaint();
    }

    public void window() {
        window = new JFrame("Do Not Forget To Do");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(850, 550);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    public void createTabbedPane() { //To choose a function
        tabbedPane = new JTabbedPane();
        ImageIcon tick = new ImageIcon("C:\\Users\\pc\\IdeaProjects\\ToDoList\\src\\tick2.png");

        title.createTitlePanel();
        tabbedPane.addTab("Title", tick, title.panel1, "Crete title");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        date_time.spinnerDate();
        tabbedPane.addTab("Date", tick, date_time.panel2, "Add date & time");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        label.createLabelPanel();
        tabbedPane.addTab("Label", tick, label.panel4, "Add labels");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        file.createPanel();
        tabbedPane.addTab("File", tick, file.panel3, "File operation");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        window.add(tabbedPane, BorderLayout.NORTH);
    }

    public void createJList() { //To print the to-do list
        listModel = new DefaultListModel();
        jList = new JList(listModel);
        jList.setBackground(Color.LIGHT_GRAY);
        jList.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        scrollPane = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createTitledBorder(border, "List"));
        scrollPane.setPreferredSize(new Dimension(250, 320));
        window.add(scrollPane, BorderLayout.CENTER);

    }
    public void createPanelWest(){
        panelWest = new JPanel(new BorderLayout());
        panelWest.setPreferredSize(new Dimension(250,500));
        panelWest.setBorder(BorderFactory.createTitledBorder(border, "Show"));
        panelWest.add(label.jPanel3, BorderLayout.SOUTH);
        panelWest.add(label.jPanel2, BorderLayout.NORTH);
        window.add(panelWest, BorderLayout.WEST);
        label.commonLabelsList(); // create JList and listModel
    }
    public void createAddButtons(){
        panelEast = new JPanel();
        panelEast.setPreferredSize(new Dimension(150,500));
        list.add();
        list.delete();
        window.add(panelEast, BorderLayout.EAST);
    }
    public void setNull(){ //to initialize the variables
        list.title = "No Title";
        list.date_time = null;
        list.label = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "set title":
                title.saveTitle();
                break;
            case "set date":
                date_time.saveDate();
                break;
            case "add list":
                list.warning();
                break;
            case "delete":
                list.deleteFunction();
                break;
            case "button3":
                label.labelWindow.setVisible(true);
                break;
            case "add label":
                label.checkLabelList();
                break;
            case "create label":
                label.createLabel();
                break;
            case "save label":
                label.listWindow.dispose();
                break;
            case "choose label":
                label.addLabel();
                break;
            case "see label":
                label.createSets();
                label.showLabels();
                break;
            case "delete label":
                label.deleteLabel();
                break;
            case "save file":
                file.saveFunction();
                break;
            case "open file":
                file.openFunction();
                break;
            case "exit":
                file.exitFunction();
                break;
        }
    }
}
