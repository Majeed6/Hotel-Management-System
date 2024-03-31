package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class DeleteEmployee extends JFrame implements ActionListener{
    JTable table;
    JButton back, delete;
    DashBoard db = new DashBoard();

    DeleteEmployee(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel heading = new JLabel("Here Are All Current Employees, Select And Delete");
        heading.setBounds(200,0,500,30);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
        add(heading);


        JLabel l1 = new JLabel("Name");
        l1.setBounds(40,50,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Age");
        l2.setBounds(170,50,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Gender");
        l3.setBounds(290,50,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Job");
        l4.setBounds(400,50,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(540,50,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);

        JLabel l6 = new JLabel("Phone");
        l6.setBounds(670,50,100,20);
        l6.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l6);

        JLabel l7 = new JLabel("Email");
        l7.setBounds(790,50,100,20);
        l7.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l7);

        JLabel l8 = new JLabel("ID");
        l8.setBounds(910,50,100,20);
        l8.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l8);

        table = new JTable();
        table.setBounds(0,80,1000,400);
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(320,500,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);  // Make it non-opaque
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        delete = new JButton("Delete");
        delete.setBounds(460,500,120,30);
        delete.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        delete.setForeground(goldColor);
        delete.addActionListener(this);
        delete.setFocusPainted(false);
        delete.setOpaque(false);
        delete.setBorder(roundedBorder);
        delete.setUI(new CustomButtonUI());
        add(delete);

        setBounds(300,150,1000,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new DashBoard(UserSession.isManager);

        } else if (e.getSource() == delete) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please Select an Employee First!");
                return;
            }

            String id = table.getValueAt(row, 7).toString();  // Assumes ID is in the 8th column

            try {
                Connector conn = new Connector();
                String query = "delete from employee where id = " + id;
                conn.s.executeUpdate(query);

                // Refresh table data
                ResultSet rs = conn.s.executeQuery("select * from employee");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        new DeleteEmployee();
    }
}
