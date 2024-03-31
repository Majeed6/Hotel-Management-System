package HotalManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class DeleteRoom extends JFrame implements ActionListener{
    JTable table;
    JButton back, delete;

    DeleteRoom(){

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bed.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,0,600,600);
        add(image);

        JLabel heading = new JLabel("Here Are All Current Rooms, Select And Delete");
        heading.setBounds(10,0,500,25);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
        add(heading);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(10,35,100,20);
        l1.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l1);

        JLabel l2 = new JLabel("Availibility");
        l2.setBounds(120,35,100,20);
        l2.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l2);

        JLabel l3 = new JLabel("Status");
        l3.setBounds(230,35,100,20);
        l3.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(330,35,100,20);
        l4.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l4);

        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(410,35,100,20);
        l5.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
        add(l5);

        table = new JTable();
        table.setBounds(10,65,500,400);
//        table.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
        add(table);

        try {
            Connector conn = new Connector();
            ResultSet rs = conn.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("Back");
        back.setBounds(100,500,120,30);
        back.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        back.setForeground(goldColor);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setBorder(roundedBorder);
        back.setUI(new CustomButtonUI());
        add(back);

        delete = new JButton("Delete");
        delete.setBounds(260,500,120,30);
        delete.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        delete.setForeground(goldColor);
        delete.addActionListener(this);
        delete.setFocusPainted(false);
        delete.setOpaque(false);
        delete.setBorder(roundedBorder);
        delete.setUI(new CustomButtonUI());
        add(delete);

        setBounds(270,100,1050,600);
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
                JOptionPane.showMessageDialog(null, "Please Select a Room First!");
                return;
            }

            String roomnum = table.getValueAt(row, 0).toString();  // Assumes ID is in the 8th column

            try {
                Connector conn = new Connector();
                String query = "delete from room where roomnumber = " + roomnum;
                conn.s.executeUpdate(query);

                // Refresh table data
                ResultSet rs = conn.s.executeQuery("select * from room");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }
    public static void main(String[] args){
        new DeleteRoom();
    }
}
