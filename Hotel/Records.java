package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Records extends JFrame implements ActionListener{
    JTable table;
    JButton back, submit, delete;

    JTextField EndDateField = new JTextField(20), startDateField = new JTextField(20), Result = new JTextField(20);
    ;


    JComboBox<String> selectquery;

    Records(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel l1 = new JLabel("Document Type");
        l1.setBounds(10,35,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Number");
        l2.setBounds(160,35,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Name");
        l3.setBounds(290,35,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Gender");
        l4.setBounds(410,35,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Country");
        l5.setBounds(510,35,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);

        JLabel l6 = new JLabel("Room Number");
        l6.setBounds(640,35,100,20);
        l6.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l6);

        JLabel l7 = new JLabel("Checkin Time");
        l7.setBounds(760,35,100,20);
        l7.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l7);

        JLabel l8 = new JLabel("CheckOut Time");
        l8.setBounds(870,35,100,20);
        l8.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l8);

        JLabel l9 = new JLabel("Payed");
        l9.setBounds(990,35,100,20);
        l9.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l9);

        selectquery = new JComboBox<>(new String[] {"Total income in a certain period", "How many Customers booked in a certain period",
                "How many Male Customers booked in a certain period", "How many Female Customers booked in a certain period"});
        selectquery.setBounds(150,0,250,25);
        selectquery.setBackground(Color.WHITE);
        selectquery.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(selectquery);

        JLabel l10 = new JLabel("Start Date: ");
        l10.setBounds(420,0,100,20);
        l10.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l10);

        startDateField.setBounds(495,0,100,20);
        add(startDateField);

        JLabel l11 = new JLabel("End Date: ");
        l11.setBounds(630,0,100,20);
        l11.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l11);

        EndDateField.setBounds(700,0,100,20);
        add(EndDateField);

        JLabel l12 = new JLabel("Result: ");
        l12.setBounds(740,0,100,20);
        l12.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l12);

        Result.setBounds(860,0,100,20);
        add(Result);

//
//        JTextField startDateField = new JTextField(20);
//        JTextField endDateField = new JTextField(20);
//
//// Add these fields to your GUI
//        yourPanel.add(new JLabel("Start Date: "));
//        yourPanel.add(startDateField);
//        yourPanel.add(new JLabel("End Date: "));
//        yourPanel.add(endDateField);


        table = new JTable();
        table.setBounds(0,65,1110,400);
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from records order by checkintime");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

//        delete = new JButton("Delete");
//        delete.setBounds(600,500,120,30);
//        delete.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
//        delete.setForeground(goldColor);
//        delete.addActionListener(this);
//        delete.setFocusPainted(false);
//        delete.setOpaque(false);  // Make it non-opaque
//        delete.setBorder(roundedBorder);
//        delete.setUI(new CustomButtonUI());
//        add(delete);

        submit = new JButton("Submit");
        submit.setBounds(450,500,120,30);
        submit.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        submit.setForeground(goldColor);
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setOpaque(false);  // Make it non-opaque
        submit.setBorder(roundedBorder);
        submit.setUI(new CustomButtonUI());
        add(submit);

        back = new JButton("Back");
        back.setBounds(300,500,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);


        setBounds(300,150,1110,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String startDate = startDateField.getText();
        String endDate = EndDateField.getText();

        if(e.getSource() == back){
            setVisible(false);
            new Reception(UserSession.isManager);
        }else if (e.getSource() == submit){
            try {

                if (selectquery.getSelectedItem() == "Total income in a certain period"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT SUM(deposit) AS TotalDeposits FROM records WHERE checkintime BETWEEN ? AND ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, startDate);
                    stmt.setString(2, endDate);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        double totalIncome = rs.getDouble("TotalDeposits");
                        Result.setText(String.valueOf(totalIncome));
                    } else {
                        Result.setText("No data found.");
                    }
                }else if (selectquery.getSelectedItem() == "How many Customers booked in a certain period"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(number) AS TotalCustomers FROM records WHERE checkintime BETWEEN ? AND ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, startDate);
                    stmt.setString(2, endDate);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        int Totalcustomers = rs.getInt("TotalCustomers");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }
                }else if (selectquery.getSelectedItem() == "How many Male Customers booked in a certain period"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(number) AS TotalCustomers FROM records WHERE checkintime BETWEEN ? AND ? AND gender = 'Male'";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, startDate);
                    stmt.setString(2, endDate);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        int Totalcustomers = rs.getInt("TotalCustomers");
                        Result.setText(String.valueOf(Totalcustomers));
                    } else {
                        Result.setText("No data found.");
                    }
                }else if (selectquery.getSelectedItem() == "How many Female Customers booked in a certain period"){

                    Connection conn = new Connector().getConnection();

                    String query = "SELECT COUNT(number) AS TotalCustomers FROM records WHERE checkintime BETWEEN ? AND ? AND gender = 'Female'";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, startDate);
                    stmt.setString(2, endDate);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()){
                        int Totalcustomers = rs.getInt("TotalCustomers");
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
        new Records();
    }
}
