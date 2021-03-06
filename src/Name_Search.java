import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Name_Search implements ActionListener{
    Frame frame1;
    JLabel nameLabel;
    JTextField nameTextField;
    JButton fetchButton;
    JButton resetButton;

    JFrame frame2;//creating object of second JFrame
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table;//Creating object of JTable
    Connection connection;//Creating object of Connection class
    Statement statement;//Creating object of Statement class
    int flag=0;

    void Name_Search(){
        frame1 = new JFrame();
        frame1.setTitle("Поиск по БД");//setting the title of first JFrame
        GridBagLayout bagLayout = new GridBagLayout();//creating object of GridBagLayout
        GridBagConstraints bagConstraints = new GridBagConstraints();//creating object of GridBagConstratints
        frame1.setSize(500, 300);//setting the size of first JFrame
        frame1.setLayout(bagLayout);//setting the layout to GridBagLayout of first JFrame

        bagConstraints.insets = new Insets(15, 40, 0, 0);//Setting the padding between the components and neighboring components

        //Setting the property of JLabel and adding it to the first JFrame
        nameLabel = new JLabel("Имя");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        frame1.add(nameLabel, bagConstraints);

        //Setting the property of JTextfield and adding it to the first JFrame
        nameTextField = new JTextField(15);
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        frame1.add(nameTextField, bagConstraints);

        //Setting the property of JButton(Fetch Data) and adding it to the first JFrame
        fetchButton = new JButton("Найти");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.ipadx = 60;
        frame1.add(fetchButton, bagConstraints);

        //Setting the property of JButton(Reset Data) and adding it to the second JFrame
        resetButton = new JButton("Очистить");
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 1;
        frame1.add(resetButton, bagConstraints);

        //adding ActionListener to both buttons
        fetchButton.addActionListener(this);
        resetButton.addActionListener(this);


        frame1.setVisible(true);//Setting the visibility of First JFrame
        frame1.validate();//Performing relayout of the First JFrame


    }

    public static void main(String[] args) {
        new Live_Search();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchButton) {

            String userName = nameTextField.getText().toString();//getting text of username text field and storing it in a String variable
            frameSecond(userName);//passing the text value to frameSecond method

        }
        if (e.getSource() == resetButton) {
            nameTextField.setText("");//resetting the value of username text field
        }

    }


    public void frameSecond(String userName) {

        //setting the properties of second JFrame
        frame2 = new JFrame("Результат");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(400, 400);

        //Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("Имя");
        defaultTableModel.addColumn("Возраст");
        defaultTableModel.addColumn("Семейное положение");
        defaultTableModel.addColumn("Зарплата");
        defaultTableModel.addColumn("Адрес");




        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/kurs",
                    "roott", "root");
            statement = connection.createStatement();//crating statement object
            String query = "select * from employee where name = '" + userName + "'";//Storing MySQL query in A string variable
            ResultSet resultSet = statement.executeQuery(query);//executing query and storing result in ResultSet


            while (resultSet.next()) {

                //Retrieving details from the database and storing it in the String variables
                String name = resultSet.getString("name");
                String roll = resultSet.getString("age");
                String dept = resultSet.getString("marital-status");
                String dept1 = resultSet.getString("salary");
                String dept2 = resultSet.getString("address");
                if (userName.equalsIgnoreCase(name)) {
                    flag = 1;
                    defaultTableModel.addRow(new Object[]{name, roll, dept, dept1, dept2});//Adding row in Table
                    frame2.setVisible(true);//Setting the visibility of second Frame
                    frame2.validate();
                    break;
                }

            }

            if (flag == 0) {
                JOptionPane.showMessageDialog(null, "Не найден");//When invalid username is entered
            }


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }
}

