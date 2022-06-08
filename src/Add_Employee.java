import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Add_Employee implements ActionListener{
    JFrame frame;
    JLabel box,title,label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    JButton b1,b2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Add_Employee window = new Add_Employee();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void Add_employee(){
        frame = new JFrame("Добавить работника");
        frame.setBackground(Color.white);
        frame.setLayout(null);

        box = new JLabel();
        box.setBounds(0,0,900,700);
        box.setLayout(null);

        title = new JLabel("Информация");
        title.setBounds(320,30,500,50);
        title.setFont(new Font("serif",Font.ITALIC,25));
        title.setForeground(Color.black);
        box.add(title);
        frame.add(box);


        label1 = new JLabel("Имя");
        label1.setBounds(50,150,100,30);
        label1.setFont(new Font("serif",Font.BOLD,20));
        box.add(label1);

        t1=new JTextField();
        t1.setFont(new Font("serif",Font.BOLD,15));
        t1.setBounds(200,150,150,30);
        box.add(t1);

        label2 = new JLabel("Возраст");
        label2.setBounds(400,150,200,30);
        label2.setFont(new Font("serif",Font.BOLD,20));
        box.add(label2);

        t2=new JTextField();
        t2.setFont(new Font("serif",Font.BOLD,15));
        t2.setBounds(600,150,150,30);
        box.add(t2);

        label3 = new JLabel("Адрес");
        label3.setBounds(50,200,100,30);
        label3.setFont(new Font("serif",Font.BOLD,20));
        box.add(label3);

        t3=new JTextField();
        t3.setFont(new Font("serif",Font.BOLD,15));
        t3.setBounds(200,200,150,30);
        box.add(t3);

        label4 = new JLabel("Семейное положение");
        label4.setBounds(400,200,200,30);
        label4.setFont(new Font("serif",Font.BOLD,20));
        box.add(label4);

        t4=new JTextField();
        t4.setFont(new Font("serif",Font.BOLD,15));
        t4.setBounds(600,200,150,30);
        box.add(t4);

        label5 = new JLabel("Зарплата");
        label5.setBounds(50,250,100,30);
        label5.setFont(new Font("serif",Font.BOLD,20));
        box.add(label5);

        t5=new JTextField();
        t5.setFont(new Font("serif",Font.BOLD,15));
        t5.setBounds(200,250,150,30);
        box.add(t5);




        label12 = new JLabel();
        label12.setBounds(200,450,250,200);
        box.add(label12);

        label13 = new JLabel("");
        label13.setBounds(600,450,250,200);
        box.add(label13);

        b1 = new JButton("Ок");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(250,550,150,40);
        box.add(b1);

        b2=new JButton("Отмена");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(450,550,150,40);
        box.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        frame.setVisible(true);
        frame.setSize(900,700);
        frame.setLocation(200,20);
    }




    @Override
    public void actionPerformed(ActionEvent ae) {

        String a  = t1.getText();
        String bb = t2.getText();
        String c  = t3.getText();
        String d  = t4.getText();
        String e  = t5.getText();
        if(ae.getSource() == b1) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/kurs",
                        "roott", "root");
                PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("insert into employee values('"+a+"','"+bb+"','"+c+"','"+d+"','"+e+"')");
                st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Успешно");
            }catch (Exception ee) {
                System.out.println("Ошибка:" + ee);
            }
        }

    }



}