import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class List implements Comparable<List>{
    Main main;
    ArrayList<List> list;
    ArrayList<String> label;
    JButton deleteButton, addButton;
    String title;
    Date date_time;

    public List(String title, Date date_time, ArrayList<String> label){
        this.title = title;
        this.date_time = date_time;
        this.label = label;
    }

    public List(Main main) {
        this.main = main;
        title = "No Title";
        list = new ArrayList<>();
        label = new ArrayList<>();
    }
    public void add() {
        addButton = new JButton("Save");
        addButton.setPreferredSize(new Dimension(100, 25));
        addButton.addActionListener(this.main);
        addButton.setActionCommand("add list");
        main.panelEast.add(addButton);
    }

    public void delete() {
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 25));
        deleteButton.addActionListener(this.main);
        deleteButton.setActionCommand("delete");
        main.panelEast.add(deleteButton);
        main.jList.setVisibleRowCount(-1);
        deleteButton.setEnabled(false);
        main.jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (main.jList.getSelectedIndex() == -1) {
                        deleteButton.setEnabled(false);

                    } else {
                        deleteButton.setEnabled(true);
                        deleteButton.revalidate();
                    }
                }
            }
        });
    }
    public void deleteFunction() {
        int index = main.jList.getSelectedIndex();
        main.listModel.remove(index); // to remove from list model
        main.scrollPane.updateUI();
        System.out.println("Element is deleted at the index " + index);
        list.remove(index); // to remove from ArrayList of lists
        print();
    }

    public void createList(){ // adding lists into arrayList
        list.add(new List(getTitle(),getDate_time(),getLabel()));
    }

    public void updateList(){
        for(int s=0;s<list.size();s++) {
            if (list.get(s).getDate_time() != null)
                Collections.sort(list);
        }
        printJList();
    }

    public void warning(){
        if(getDate_time() == null) {
            JDialog confirmMessage = new JDialog();
            JOptionPane.showMessageDialog(confirmMessage, "You must pick date & time before adding the 'to-do'.",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }else{
            createList();

            updateList();
            print();
            main.setNull();
        }
    }

    public void print() { // to print the lists
        if (list != null) {
            for(int i=0;i<list.size();i++) {
                String msg = "";
                if(label.size()==0){
                    System.out.println((i+1) + " --> " + list.get(i).getTitle() + " " + list.get(i).getDate_time() + " No Label");
                }else {
                    for (int j = 0; j < label.size(); j++) {
                        msg += label.get(j);
                        msg += " ";
                    }
                    System.out.println((i+1) + " --> " + list.get(i).getTitle() + " " + list.get(i).getDate_time() + " " + msg);
                }
            }
        } else {
            System.out.println("List is empty!!");
        }
        System.out.println("size of list:" + list.size());
    }
    public void printJList() { // add elements into JList
        main.listModel.removeAllElements(); // remove all elements in list model
        if (list != null) {
            for (int t = 0; t < list.size(); t++) {
                String labelText = "";

                if (label.size() == 0) {
                    labelText = "";
                    main.listModel.addElement(list.get(t).getTitle() + " // " + list.get(t).getDate_time());
                } else {
                    for (int i = 0; i < list.get(t).label.size(); i++) {
                        labelText += "#";
                        labelText += list.get(t).label.get(i);
                        labelText += " ";
                    }

                    main.listModel.addElement(list.get(t).getTitle() + " // " + list.get(t).getDate_time() + " // " + labelText);
                }
            }
        } else {
            System.out.println("List is empty!! JList cannot be created.");
        } main.jList.updateUI();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public ArrayList<String> getLabel() {
        return label;
    }

    public void setLabel(ArrayList<String> label) {
        this.label = label;
    }

    @Override
    public int compareTo(List o) {// to sort the arrayList by date&time

        return this.getDate_time().compareTo(o.getDate_time());
    }
}