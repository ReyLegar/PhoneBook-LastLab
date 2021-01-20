import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatureNewUserFrame implements ActionListener {
    JTextField name;
    JTextField surname;
    JTextField middleName;
    JTextField phoneNumber;

    JLabel nameWr, surnameWr, middleNameWr, phoneNumberWr;
    final JFrame jFrame = new JFrame("Создание нового пользователя");
    public CreatureNewUserFrame(){
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(200, 300);
        jFrame.setResizable(false);
        name = new JTextField(10);
        surname = new JTextField(10);
        middleName = new JTextField(10);
        phoneNumber = new JTextField(10);
        JButton createButton = new JButton("Создать");
        nameWr = new JLabel("Введите имя: ");
        surnameWr = new JLabel("Введите фамилию: ");
        middleNameWr = new JLabel("Введите отчество(если есть): ");
        phoneNumberWr = new JLabel("Введите номер телефона: ");
        jFrame.add(nameWr);
        jFrame.add(name);
        jFrame.add(surnameWr);
        jFrame.add(surname);
        jFrame.add(middleNameWr);
        jFrame.add(middleName);
        jFrame.add(phoneNumberWr);
        jFrame.add(phoneNumber);
        jFrame.add(createButton);
        createButton.addActionListener((ActionListener) this);
        jFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Создать")){
            jFrame.dispose();
        }
    }
}
