import javax.swing.*;
import java.awt.*;

public class Title {
    Main main;
    List list;
    JButton infoButton;
    JTextField titleField;
    JPanel panel1;

    public Title(Main main, List list) {
        this.main = main;
        this.list = list;
    }

    public void createTitlePanel() {
        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder(main.border, "Title"));

        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(180, 20));
        panel1.add(titleField);

        infoButton = new JButton("Save"); //set the title
        infoButton.setPreferredSize(new Dimension(100, 20));
        infoButton.addActionListener(this.main);
        infoButton.setActionCommand("set title");
        panel1.add(infoButton);
    }

    public void saveTitle() {
        list.setTitle(titleField.getText());
        System.out.println("Title: " + titleField.getText());
        titleField.setText(" ");
    }
}
