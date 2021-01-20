import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.lang.Comparable;


class UserFrame implements Comparable<UserFrame> {
    String name;
    String surname;
    String middleName;
    String numberPhone;

    UserFrame(String surname, String name, String middleName, String numberPhone){
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.numberPhone = numberPhone;

    }

    public String getPhone(){
        return this.numberPhone;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }


    @Override
    public int compareTo(UserFrame p) {
        return surname.compareTo(p.getSurname());
    }
}

class phoneSortComparator implements Comparator<UserFrame> {

    @Override
    public int compare(UserFrame o1, UserFrame o2) {
        return o1.getPhone().compareTo(o2.getPhone());

    }
}

public class MainFrame implements ActionListener {
    JTextField name;
    JTextField surname;
    JTextField middleName;
    JTextField phoneNumber;
    JLabel nameWr, surnameWr, middleNameWr, phoneNumberWr, sortingWr;
    JLabel numRecordingWr;
    Label numRecording;
    JComboBox comboSorting;
    public TreeSet<UserFrame> data = new TreeSet<UserFrame>(new phoneSortComparator());
    MainFrame() {
        try {

            File file = new File("test.txt");

            FileReader fr = new FileReader(file);

            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                String[] lineFields = line.split(" ");
                UserFrame user = new UserFrame(lineFields[0], lineFields[1], lineFields[2], lineFields[3]);
                data.add(user);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    //    TreeSet<UserFrame> res = (TreeSet<UserFrame>)data.descendingSet();



        JFrame jfrm = new JFrame("Phone book");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(new FlowLayout(FlowLayout.CENTER));
        jfrm.setSize(320, 400);
        jfrm.setResizable(false);
        String[] items = {
                "фамилии",
                "номеру телефона"
        };
        JButton saveBut = new JButton("Сохранить");
        JButton correctBut = new JButton("Редактировать");
        JButton writeDB = new JButton("Запись в БД");
        JButton delete = new JButton("Удалить");
        JButton search = new JButton("Поиск");
        comboSorting = new JComboBox(items);
        saveBut.addActionListener((ActionListener) this);
        correctBut.addActionListener((ActionListener) this);
        writeDB.addActionListener((ActionListener) this);
        delete.addActionListener((ActionListener) this);
        search.addActionListener((ActionListener) this);
        comboSorting.addActionListener(this);
        name = new JTextField(10);
        surname = new JTextField(10);
        middleName = new JTextField(10);
        phoneNumber = new JTextField(10);
        nameWr = new JLabel("Введите имя:                                  ");
        surnameWr = new JLabel("Введите фамилию:                       ");
        middleNameWr = new JLabel("Введите отчество(если есть):     ");
        phoneNumberWr = new JLabel("Введите номер телефона:          ");
        numRecording = new Label(Integer.toString(data.size()));
        numRecordingWr = new JLabel("Количество записей:");
        sortingWr = new JLabel("Сортировать по: ");

        jfrm.add(surnameWr);
        jfrm.add(surname);
        jfrm.add(nameWr);
        jfrm.add(name);
        jfrm.add(middleNameWr);
        jfrm.add(middleName);
        jfrm.add(phoneNumberWr);
        jfrm.add(phoneNumber);
        jfrm.add(saveBut);
        jfrm.add(correctBut);
        jfrm.add(writeDB);
        jfrm.add(delete);
        jfrm.add(search);
        jfrm.add(sortingWr);
        jfrm.add(comboSorting);
        jfrm.add(numRecordingWr);
        jfrm.add(numRecording);


        for (UserFrame text : data) {
            System.out.println(text.surname + " " + text.name + " " + text.middleName + " " + text.numberPhone);
        }


        jfrm.setVisible(true);
    }

    void addInTreeSet(String name, String surname, String middle, String phone) {
        data.add(new UserFrame(name, surname, middle, phone));
    }

    public UserFrame findNodePhoneNumber(String phone) {
        Iterator<UserFrame> iterator = data.iterator();
        while(iterator.hasNext()) {
            UserFrame node = iterator.next();
            String nodePhone = node.getPhone();
            if(nodePhone.equals(phone))
                return node;
        }
        return null;
    }
    public UserFrame findNodeSurname(String surname) {
        Iterator<UserFrame> iterator = data.iterator();
        while(iterator.hasNext()) {
            UserFrame node = iterator.next();
            String nodeSurname = node.getSurname();
            if(nodeSurname.equals(surname))
                return node;
        }
        return null;
    }
    public UserFrame allParametres(String surname, String name, String numberPhone){
        Iterator<UserFrame> iterator = data.iterator();
        while(iterator.hasNext()) {
            UserFrame node = iterator.next();
            String nodeSurname = node.getSurname();
            String nodeName = node.getName();
            String nodePhone = node.getPhone();
            if(nodeSurname.equals(surname) && nodeName.equals(name) && nodePhone.equals(numberPhone));
                return node;
        }
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Редактировать")) {
            if(name.getText().equals("") || surname.getText().equals("") || phoneNumber.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Данные введены не полностью");
            else if(!phoneNumber.getText().matches("^\\+7\\d{10}$")){
                JOptionPane.showMessageDialog(null, "Некорректный номер телефона");}
            else{
                UserFrame node = findNodePhoneNumber(phoneNumber.getText());
                if(node != null) {
                    Iterator<UserFrame> myIterUser = data.iterator();
                    while(myIterUser.hasNext()) {
                        TreeSet<UserFrame> redactionNode = new TreeSet<>();
                        UserFrame nodeTwo = myIterUser.next();
                        if (nodeTwo.numberPhone.equals(phoneNumber.getText()) && nodeTwo.name.equals(name.getText()) && nodeTwo.surname.equals(surname.getText())) {
                            if (middleName.getText().equals("")) {
                                middleName.setText(node.middleName);
                            }
                            myIterUser.remove();
                            Iterator<UserFrame> myIter = redactionNode.iterator();
                            while(myIter.hasNext()){
                                UserFrame nodeThree = myIter.next();
                                data.add(nodeThree);
                            }
                        }
                        else
                            redactionNode.add(nodeTwo);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Пользователь не найден");

            }
        }
        if (ae.getActionCommand().equals("Удалить")) {
            UserFrame node = findNodePhoneNumber(phoneNumber.getText());
            if(node != null){
                Iterator<UserFrame> myIterUser = data.iterator();
                while(myIterUser.hasNext()) {
                    UserFrame nodeTwo = myIterUser.next();
                    if (nodeTwo.numberPhone.equals(phoneNumber.getText()))
                        myIterUser.remove();
                }
                name.setText("");
                surname.setText("");
                middleName.setText("");
                phoneNumber.setText("");
                numRecording.setText(Integer.toString(data.size()));
            }
            else{
                JOptionPane.showMessageDialog(null, "Пользователь не найден");
            }
        }
        if (ae.getActionCommand().equals("Поиск")) {
            if(!phoneNumber.getText().equals("")){
                UserFrame node = findNodePhoneNumber(phoneNumber.getText());
                if(node != null)
                JOptionPane.showMessageDialog(null, "Пользователь с таким номером телефона:\n" +
                        node.surname + " " + node.name + " " + node.numberPhone);
                else
                    JOptionPane.showMessageDialog(null, "Пользователи не найдены");
            }
            else if(!name.getText().equals("") && !surname.getText().equals("")){
                String ansStr = "Найдены пользователи:\n";
                System.out.println(ansStr.length());
                Iterator<UserFrame> iterator = data.iterator();
                while(iterator.hasNext()) {
                    UserFrame node = iterator.next();
                    String nodeSurname = node.getSurname();
                    String nodeName = node.getName();
                    if(nodeName.equals(name.getText()) && nodeSurname.equals(surname.getText())){
                        ansStr += nodeSurname + " " + nodeName + " " + node.middleName + " " + node.numberPhone + "\n";
                    }
                }
                if(ansStr.length() > 22)
                    JOptionPane.showMessageDialog(null, ansStr);
                else{
                    JOptionPane.showMessageDialog(null, "Пользователи не найдены");
                    }
            }
            else{
                JOptionPane.showMessageDialog(null, "Данные введены неверно");
            }

        }
        if (ae.getActionCommand().equals("Запись в БД")) {
            String it = (String) comboSorting.getSelectedItem();
            if (it.equals("номеру телефона"))
                try (FileWriter writer = new FileWriter("test.txt", false)) {
                    for (UserFrame text : data) {
                        writer.write(text.surname + " " + text.name + " " + text.middleName + " " + text.numberPhone);
                        writer.append("\n");
                    }
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }
            else if (it.equals("фамилии")) {
                TreeSet<UserFrame> res = (TreeSet<UserFrame>) data.descendingSet();
                try (FileWriter writer = new FileWriter("test.txt", false)) {
                    for (UserFrame text : res) {
                        writer.write(text.surname + " " + text.name + " " + text.middleName + " " + text.numberPhone);
                        writer.append("\n");
                    }
                    writer.flush();
                } catch (IOException ex) {

                    System.out.println(ex.getMessage());
                }

            }
        }
        if (ae.getActionCommand().equals("Сохранить")) {
            if(name.getText().equals("") || surname.getText().equals("") || phoneNumber.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Данные введены не полностью");
            else{
                if (!phoneNumber.getText().matches("^\\+7\\d{10}$")) {
                    System.out.println("Incorrect phone number");
                    JOptionPane.showMessageDialog(null, "Некорректный ввод номера телефона");
                }
                else{
                    UserFrame node = findNodePhoneNumber(phoneNumber.getText());
                    if(node == null) {
                        addInTreeSet(surname.getText(), name.getText(), middleName.getText(), phoneNumber.getText());
                        name.setText("");
                        surname.setText("");
                        middleName.setText("");
                        phoneNumber.setText("");
                        for (UserFrame text : data) {
                            System.out.println(text.surname + " " + text.name + " " + text.middleName + " " + text.numberPhone);
                        }
                        numRecording.setText(Integer.toString(data.size()));
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Номер телефона используется у " + node.getSurname() + " "
                                + node.getName());
                    }
        }
    }
} }}

