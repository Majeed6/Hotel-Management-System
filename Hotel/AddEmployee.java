package HotalManagementSystem;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField forName, forAge, forEmail, forPhone, forSalary, forID;
    JRadioButton rbmale, rbfemale;
    JButton submit, cancel;
    JComboBox<String> cbJob;

    DashBoard db = new DashBoard();

    AddEmployee() {

        Color goldColor = new Color(218, 165, 32);
        Border roundedBorder = new RoundedBorder(goldColor, 3);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        setBounds(350, 200, 850, 540);


        JLabel name = new JLabel("NAME ");
        name.setBounds(60,30,120,30);
        name.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(name);

        forName = new JTextField();
        forName.setBounds(200, 30, 150, 30);
        add(forName);

        JLabel age = new JLabel("AGE ");
        age.setBounds(60,80,120,30);
        age.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(age);

        forAge = new JTextField();
        forAge.setBounds(200, 80, 150, 30);
        add(forAge);

        JLabel gender = new JLabel("GENDER ");
        gender.setBounds(60,130,120,30);
        gender.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(gender);

        rbmale = new JRadioButton("MALE");
        rbmale.setBounds(200, 130, 70,30);
        rbmale.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("FEMALE");
        rbfemale.setBounds(270, 130, 100,30);
        rbfemale.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);


        JLabel job = new JLabel("JOB ");
        job.setBounds(60,170,120,30);
        job.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(job);

        String str[] = {"Front Desk Clerk", "Porters", "Guard", "Kitchen Staff", "Room Service", "Waiter/Waitress" , "Accountant"};
        cbJob = new JComboBox<>(str);
        cbJob.setBounds(200,180,150,30);
        cbJob.setBackground(Color.WHITE);
        add(cbJob);

        JLabel salary = new JLabel("SALARY ");
        salary.setBounds(60,220,120,30);
        salary.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(salary);

        forSalary = new JTextField();
        forSalary.setBounds(200, 220, 150, 30);
        add(forSalary);

        JLabel phone = new JLabel("PHONE ");
        phone.setBounds(60,270,120,30);
        phone.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(phone);

        forPhone = new JTextField();
        forPhone.setBounds(200, 270, 150, 30);
        add(forPhone);


        JLabel email = new JLabel("EMAIL ");
        email.setBounds(60,320,120,30);
        email.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(email);

        forEmail = new JTextField();
        forEmail.setBounds(200, 320, 150, 30);
        add(forEmail);

        JLabel id1 = new JLabel("ID ");
        id1.setBounds(60,370,120,30);
        id1.setFont(new Font("Century Schoolbook", Font.PLAIN, 17));
        add(id1);

        forID = new JTextField();
        forID.setBounds(200, 370, 150, 30);
        add(forID);


        submit = new JButton("Add");
        submit.setBounds(70, 430, 150, 30);
        submit.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        submit.setForeground(goldColor);
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        submit.setOpaque(false);  // Make it non-opaque
        submit.setBorder(roundedBorder);
        submit.setUI(new CustomButtonUI());
        add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(250, 430, 150, 30);
        cancel.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
        cancel.setForeground(goldColor);
        cancel.addActionListener(this);
        cancel.setFocusPainted(false);
        cancel.setOpaque(false);  // Make it non-opaque
        cancel.setBorder(roundedBorder);
        cancel.setUI(new CustomButtonUI());
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/employ.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 370);
        add(image);

        setLayout(null);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = forName.getText();
        String age = forAge.getText();
        String salary = forSalary.getText();
        String email = forEmail.getText();
        String phone = forPhone.getText();
        String ID = forID.getText();

        String gender = null;

        if(rbmale.isSelected()){
            gender = "Male";
        }
        else if(rbfemale.isSelected()){
            gender = "Female";
        }

        if (e.getSource() == cancel){
            setVisible(false);
            new DashBoard(true);
        }

        String job = (String) cbJob.getSelectedItem();

        if (e.getSource() == submit){

            try {
                if(!ID.isEmpty()) {  // check if the ID is not empty

                    Connector conn = new Connector();

                    String query = "insert into employee values( '" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "', '" + ID + "' )";

                    conn.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Employee added successfully");

                    setVisible(false);
                    new AddEmployee();
                }
                else{
                    JOptionPane.showMessageDialog(null, "The ID field is empty. Employee not added.");

                }
            } catch (Exception ex){
                ex.printStackTrace();

                JOptionPane.showMessageDialog(null, "Employee not added, please try again");
            }
        }


    }

    public static void main(String[] args) {
        new AddEmployee();
    }
    
}
