package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Employee extends JFrame implements ActionListener{
    JTable table;
    JButton back, submit;

    JTextField jobField = new JTextField(20), Result = new JTextField(20);

    JComboBox<String> selectquery;

    Employee(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel l1 = new JLabel("Name");
        l1.setBounds(40,35,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Age");
        l2.setBounds(170,35,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Gender");
        l3.setBounds(290,35,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Job");
        l4.setBounds(400,35,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(540,35,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);

        JLabel l6 = new JLabel("Phone");
        l6.setBounds(670,35,100,20);
        l6.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l6);

        JLabel l7 = new JLabel("Email");
        l7.setBounds(790,35,100,20);
        l7.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l7);

        JLabel l8 = new JLabel("ID");
        l8.setBounds(910,35,100,20);
        l8.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l8);

        selectquery = new JComboBox<>(new String[] {"Total salaries for Male Employees", "Total salaries for Female Employees",
                "How many Employees are in the system", "How many Employees Who work at a certain job", "Total Salaries for Employees Who work at a certain job",
        "Average Age for All Employees"});
        selectquery.setBounds(150,0,250,25);
        selectquery.setBackground(Color.WHITE);
        selectquery.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(selectquery);

//        JLabel l10 = new JLabel("Start Date: ");
//        l10.setBounds(420,0,100,20);
//        l10.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
//        add(l10);

        jobField.setBounds(420,0,100,20);
        add(jobField);

//        JLabel l11 = new JLabel("End Date: ");
//        l11.setBounds(630,0,100,20);
//        l11.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
//        add(l11);

//        EndDateField.setBounds(700,0,100,20);
//        add(EndDateField);

        JLabel l12 = new JLabel("Result: ");
        l12.setBounds(650,0,100,20);
        l12.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l12);

        Result.setBounds(700,0,100,20);
        add(Result);

        table = new JTable();
        table.setBounds(0,65,1000,400);
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        submit = new JButton("Submit");
        submit.setBounds(470,500,120,30);
        submit.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        submit.setForeground(goldColor);
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setOpaque(false);  // Make it non-opaque
        submit.setBorder(roundedBorder);
        submit.setUI(new CustomButtonUI());
        add(submit);

        back = new JButton("Back");
        back.setBounds(330,500,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        setBounds(300,150,1000,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String jobo = jobField.getText();

        if(e.getSource() == back){
            setVisible(false);
            new Reception(UserSession.isManager);
        }else if (e.getSource() == submit){
            try {

                if (selectquery.getSelectedItem() == "Total salaries for Male Employees"){

                    Connection conn = new Connector().getConnection();
                    String query = "SELECT SUM(salary) AS TotalSALA FROM employee WHERE gender = 'Male'";
                    PreparedStatement stmt = conn.prepareStatement(query);

                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()){
                        double totalIncome = rs.getDouble("TotalSALA");
                        Result.setText(String.valueOf(totalIncome));
                    } else {
                        Result.setText("No data found.");
                    }
                }else if (selectquery.getSelectedItem() == "How many Employees are in the system"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(ID) AS TotalEmps FROM employee";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        int Totalcustomers = rs.getInt("TotalEmps");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }
                }else if (selectquery.getSelectedItem() == "Total salaries for Female Employees"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT SUM(salary) AS TotalSALA1 FROM employee WHERE gender = 'Female'";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        int Totalcustomers = rs.getInt("TotalSALA1");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }

                }
                else if (selectquery.getSelectedItem() == "How many Employees Who work at a certain job"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(ID) AS TotalJOBO FROM employee WHERE job  = ? ";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, jobo);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        int Totalcustomers = rs.getInt("TotalJOBO");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }
                } else if (selectquery.getSelectedItem() == "Total Salaries for Employees Who work at a certain job"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT SUM(salary) AS Totalsal FROM employee WHERE job  = ? ";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, jobo);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        double Totalcustomers = rs.getInt("Totalsal");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }
                } else if (selectquery.getSelectedItem() == "Average Age for All Employees"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT AVG(age) AS avar FROM employee";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        double Totalcustomers = rs.getInt("avar");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }

                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }

        }

    }
    public static void main(String[] args){
        new Employee();
    }
}
