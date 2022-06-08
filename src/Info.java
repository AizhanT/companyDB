import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;


public class Info {

    private boolean status;

    public Info(String title) {

        // Creating Window using JFrame
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(800, 500);

        // Adding Table View
        frame.add(getTablePanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel getTablePanel() {

        JPanel tableJPanel = new JPanel();

        tableJPanel.setLayout(new BorderLayout());

        // Column Header
        String[] columns = {

                "Имя", "Возраст", "Адрес",
                "Семейное положение", "Зарплата" };

        // Getting Data for Table from Database
        Object[][] data = getEmployeeDetails();

        // Creating JTable object passing data and header
        JTable employeeTable = new JTable(data, columns);


        tableJPanel.add(employeeTable.getTableHeader(), BorderLayout.NORTH);
        tableJPanel.add(employeeTable, BorderLayout.CENTER);

        return tableJPanel;
    }

    private Object[][] getEmployeeDetails() {

        Object[][] data = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";
        final String CONNECTION_URL = "jdbc:mysql://localhost:3306/kurs";
        final String USERNAME = "roott";
        final String PASSWORD = "root";

        final String QUERY = "Select name, age, marital-status, address, salary from employee";

        try {

            // Loading the Driver
            Class.forName(DRIVER_NAME);

            // Getting Database Connection Object by Passing URL, Username and Password
            Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery(QUERY);

            int rowCount = getRowCount(rs); // Row Count
            int columnCount = getColumnCount(rs); // Column Count

            data = new Object[rowCount][columnCount];

            // Starting from First Row for Iteration
            rs.beforeFirst();

            int i = 0;

            while (rs.next()) {

                int j = 0;

                data[i][j++] = rs.getString("name");
                data[i][j++] = rs.getInt("age");
                data[i][j++] = rs.getString("marital-status");
                data[i][j++] = rs.getString("address");
                data[i][j++] = rs.getInt("salary");

                i++;
            }

            status = true;

            // Closing the Resources;
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data;
    }

    // Method to get Row Count from ResultSet Object
    private int getRowCount(ResultSet rs) {

        try {

            if(rs != null) {

                rs.last();

                return rs.getRow();
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    // Method to get Column Count from ResultSet Object
    private int getColumnCount(ResultSet rs) {

        try {

            if(rs != null)
                return rs.getMetaData().getColumnCount();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public String toString() {

        return (status) ? "Data Listed Successfully" : "Application Error Occurred";
    }

    public static void main(String[] args) {

        String title = "Информация о работниках";

        Info employeeDetails = new Info(title);

        System.out.println(employeeDetails);
    }
}