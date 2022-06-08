import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserHome extends JFrame implements ActionListener {
    String title;
                JFrame frame;
                JButton info = new JButton("Информация о работниках");
                JButton name_search = new JButton("Поиск по имени");
                JButton live_search = new JButton("Поиск по проживанию");
                JButton best_employee = new JButton("Лучшие сотрудники");
                JButton age_search = new JButton("Возраст сотрудников");
                JButton add_employee = new JButton("Добавить сотрудника");
                JButton delete_employee = new JButton("Удалить сотрудника");
                JButton exit = new JButton("Выход");

                public static void main(String[] args) {
                        EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                        try {
                                                UserHome window = new UserHome();
                                                window.frame.setVisible(true);
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                        });
                }
                public UserHome(){
                        createWindow();
                }
                public void createWindow() {
                        frame = new JFrame();
                        frame.setTitle("Меню");
                        frame.getContentPane().setBackground(Color.white);
                        frame.setBounds(10, 100, 800, 1000);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().setLayout(null);
                        frame.setVisible(true);

                        // label: orange colored background
                        JPanel panel = new JPanel();
                        panel.setBackground(Color.white);
                        panel.setBounds(300, 150, 500, 472);
                        frame.getContentPane().add(panel);
                        panel.setLayout(null);

                        JLabel lblNewLabel_1 = new JLabel("Меню");
                        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 19));
                        lblNewLabel_1.setBounds(80, 0, 107, 29);
                        panel.add(lblNewLabel_1);


                        add_employee.setBounds(20, 50, 210, 60);
                        panel.add(add_employee);
                        add_employee.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        dispose();
                                        Add_Employee a = new Add_Employee();
                                    a.Add_employee();
                                }
                        });
                       delete_employee.setBounds(20, 100, 210, 60);
                        panel.add(delete_employee);
                        delete_employee.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        dispose();
                                        Delete_Employee a = new Delete_Employee();
                                        a.Delete_Employee();
                                }
                        });
                        info.setBounds(20, 150, 210, 60);
                        panel.add(info);
                        info.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        dispose();
                                        Info b = new Info(title);
                                }
                        });
                        name_search.setBounds(20, 200, 200, 60);
                        panel.add(name_search);
                        name_search.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        dispose();
                                        Name_Search c = new Name_Search();
                                        c.Name_Search();
                                }
                        });
                        live_search.setBounds(20, 250, 200, 60);
                        panel.add(live_search);
                        live_search.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        dispose();
                                        Live_Search d = new Live_Search();
                                        d.Live_Search();
                                }
                        });
                        exit.setBounds(20, 300, 200, 60);
                        panel.add(exit);
                        exit.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                        System.exit(0);
                                }
                        });


                }

                @Override
                public void actionPerformed(ActionEvent e) {

                }
        }