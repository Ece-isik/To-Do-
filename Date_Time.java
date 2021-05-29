import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Date_Time {
    Main main;
    List list;
    JSpinner spinner;
    JPanel panel2;
    JButton button;
    SpinnerDateModel spinnerDateModel;

    public Date_Time(Main main, List list) {
        this.main = main;
        this.list = list;
    }
    public void spinnerDate(){
        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder(main.border, "Date & Time"));

        button = new JButton("Save");
        button.setPreferredSize(new Dimension(100, 20));
        button.addActionListener(this.main);
        button.setActionCommand("set date");

        spinnerDateModel = new SpinnerDateModel();
        spinner = new JSpinner(spinnerDateModel);
        panel2.add(spinner);
        panel2.add(button);
    }
    public void saveDate(){
        Date date = spinnerDateModel.getDate();
        list.setDate_time(date);
        System.out.println("Date & Time: " + spinnerDateModel.getValue());
    }
}
