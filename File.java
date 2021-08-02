import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import java.io.FileOutputStream;


public class File {
    Main main;
    List list;
    int response;
    String fileName, fileDirectory;
    JPanel panel3, panel;
    JButton button1, button2, button3;
    JLabel label;

    public File(Main main, List list) {
        this.main=main;
        this.list=list;
    }

    public void createPanel(){
        panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder(main.border, "File"));

        button1 = new JButton("Save"); // create button for save operation
        button1.setToolTipText("Save the file");
        button1.setPreferredSize(new Dimension(100, 25));
        button1.addActionListener(this.main);
        button1.setActionCommand("save file");
        panel3.add(button1);

        button2 = new JButton("Open"); // create button for open operation
        button2.setToolTipText("Open a file");
        button2.setPreferredSize(new Dimension(100, 25));
        button2.addActionListener(this.main);
        button2.setActionCommand("open file");
        panel3.add(button2);

        button3 = new JButton("Exit"); // create button for exit function
        button3.setToolTipText("Exit");
        button3.setPreferredSize(new Dimension(100, 25));
        button3.addActionListener(this.main);
        button3.setActionCommand("exit");
        panel3.add(button3);
    }

    public void saveFunction() { // to save the lists into file
        FileDialog fileDialog = new FileDialog(main.window, "Save File", FileDialog.SAVE);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile();
            fileDirectory = fileDialog.getDirectory();
            main.window.setTitle(fileName);
        }
        try {
           java.io.File file = new java.io.File(fileDirectory + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (int i = 0; i < list.list.size(); i++) {
                String msg = " ";
                for (int j = 0; j < list.list.get(i).getLabel().size(); j++) {
                        msg += " -- ";
                        msg += list.list.get(i).getLabel().get(j);
                }
                String strDate = format.format(list.list.get(i).getDate_time()); // String format of the date
                bw.write(list.list.get(i).getTitle() + " -- " + strDate + msg);
                bw.newLine();
            }bw.close();
            System.out.println(fileName + " is saved as file.");
        } catch (Exception e) {
            System.out.println("File is not saved.");
        }

    }
    public void openFunction(){
        FileDialog fileDialog = new FileDialog(main.window, "Open File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile();
            fileDirectory = fileDialog.getDirectory();
            main.window.setTitle(fileName);
        }
        try {
            FileReader fr = new FileReader(fileDirectory + fileName, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(fr);
            main.listModel.removeAllElements(); // remove elements from list model
            String line = null;
             ArrayList<String> newLabelList;
            while ((line = br.readLine()) != null) {
                newLabelList = new ArrayList<>();
                String[] arr = line.split(" -- ", 0);
                for(int i=2; i< arr.length; i++){
                    newLabelList.add(arr[i]);
                }
                Date date = format.parse(arr[1]);
                list.list.add(new List(arr[0], date, newLabelList));
                list.setLabel(newLabelList);
                for(int t=0;t<newLabelList.size();t++){
                    newLabelList.remove(t);
                }
            }
            list.print();
            list.printJList();
            main.setNull();
            br.close();
        } catch (Exception e) {
            System.out.println("File is not opened.");
        }
    }
    public void exitFunction() {
        messagePanel();
        panel.setVisible(true);
        if (response == 0) { //Yes
            saveFunction();
        } else if (response == 1) { //No
            System.exit(0);
        }
    }
    public void messagePanel() { //Sending a message which clarifies if user wants a file to be saved
        panel = new JPanel();
        panel.setSize(250, 100);
        panel.setLayout(null);

        label = new JLabel("Would you like to save that file before closing?");
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(40, 80, 280, 20);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
        response = JOptionPane.showConfirmDialog(null, panel, "Warning",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }
}
