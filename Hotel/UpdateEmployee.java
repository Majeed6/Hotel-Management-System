package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener{
    JTable table;
    JButton back, update;
    DashBoard db = new DashBoard();

    UpdateEmployee(){


        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);


        JLabel heading = new JLabel("Here Are All Current Employees, Select And Update");
        heading.setBounds(200,0,500,30);
        heading.setForeground(Color.GREEN);
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

        populateTable();

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

        update = new JButton("Update");
        update.setBounds(460,500,120,30);
        update.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        update.setForeground(goldColor);
        update.addActionListener(this);
        update.setFocusPainted(false);
        update.setOpaque(false);
        update.setBorder(roundedBorder);
        update.setUI(new CustomButtonUI());
        add(update);

        setBounds(300,150,1000,600);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void populateTable() {
        try {
            Connector conn = new Connector();
            ResultSet rs = conn.getConnection().createStatement().executeQuery("select * from employee");

            DefaultTableModel model = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        Object newValue = model.getValueAt(row, column);

                        // ID is in the last column
                        Object id = model.getValueAt(row, 7);

                        try {
                            String query = "UPDATE employee SET " + table.getColumnName(column) + " = ? WHERE id = ?";
                            PreparedStatement ps = conn.getConnection().prepareStatement(query);
                            ps.setObject(1, newValue);
                            ps.setObject(2, id);
                            ps.executeUpdate();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new DashBoard(UserSession.isManager);

        } else if (e.getSource() == update) {
            populateTable();
        }
    }

    public static void main(String[] args){
        new UpdateEmployee();
    }
}