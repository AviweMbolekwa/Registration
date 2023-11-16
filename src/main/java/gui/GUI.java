/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.RaceDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author aviwembolekwa
 */
public class GUI extends JFrame implements ActionListener{

    private JLabel title, code, fname, lname, type, question;
    private JTextField codeTXT, fnameTXT, lnameTXT;
    private JComboBox typeComboBox;
    private JRadioButton yes, no;
    private ButtonGroup bg;
    private JButton save, reset, exit;
    private RaceDAO raceDAO;
    
    GUI() throws SQLException
    {
        getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        
        title = new JLabel("Marathon Event Registration");
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
        title.setBounds(150, 20, 500, 50);
        add(title);
        
        code = new JLabel("Race Code:");
        code.setForeground(Color.BLACK);
        code.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
        code.setBounds(199, 80, 100, 30);
        add(code);
        
        codeTXT = new JTextField();
        codeTXT.setForeground(Color.BLACK);
        codeTXT.setFont(new Font("Mongolian Baiti", Font.PLAIN, 13));
        codeTXT.setBounds(300, 80, 200, 35);
        add(codeTXT);
        
        fname = new JLabel("First Name:");
        fname.setForeground(Color.BLACK);
        fname.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        fname.setBounds(201, 110, 100, 30);
        add(fname);
        
        fnameTXT = new JTextField();
        fnameTXT.setForeground(Color.BLACK);
        fnameTXT.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        fnameTXT.setBounds(300, 110, 200, 35);
        add(fnameTXT);
        
        lname = new JLabel("Last Name:");
        lname.setForeground(Color.BLACK);
        lname.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        lname.setBounds(201, 140, 100, 30);
        add(lname);
        
        lnameTXT = new JTextField();
        lnameTXT.setForeground(Color.BLACK);
        lnameTXT.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        lnameTXT.setBounds(300, 140, 200, 35);
        add(lnameTXT);
        
        type = new JLabel("Race Type:");
        type.setForeground(Color.BLACK);
        type.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        type.setBounds(204, 170, 100, 30);
        add(type);
        
        typeComboBox = new JComboBox();
        typeComboBox.setForeground(Color.BLACK);
        raceDAO = new RaceDAO();
        raceDAO.populateRaceTypes(typeComboBox);
        typeComboBox.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        typeComboBox.setBounds(300, 170, 200, 30);
        add(typeComboBox);
        
        question = new JLabel("Do you belong to a club:");
        question.setForeground(Color.BLACK);
        question.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        question.setBounds(103, 200, 200, 30);
        add(question);
        
        yes = new JRadioButton("Yes");
        yes.setForeground(Color.BLACK);
        yes.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        yes.setBounds(300, 200, 100, 30);
        yes.addActionListener(this);
        add(yes);
        
        no = new JRadioButton("No");
        no.setForeground(Color.BLACK);
        no.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        no.setBounds(400, 200, 100, 30);
        no.addActionListener(this);
        add(no);
        
        bg = new ButtonGroup();
        bg.add(yes);
        bg.add(no);
        
        save = new JButton("Save");
        save.setForeground(Color.BLACK);
        save.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        save.setBounds(0, 240, 200, 30);
        save.addActionListener(this);
        add(save);
        
        reset = new JButton("Reset");
        reset.setForeground(Color.BLACK);
        reset.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        reset.setBounds(200, 240, 200, 30);
        reset.addActionListener(this);
        add(reset);
        
        exit = new JButton("Exit");
        exit.setForeground(Color.BLACK);
        exit.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        exit.setBounds(400, 240, 200, 30);
        exit.addActionListener(this);
        add(exit);
      
        setSize(600, 300);
        setResizable(true);
        setLocation(300,300);
        setVisible(true);
        
        raceDAO = new RaceDAO();
    }
    
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == save) {
           //Saving user inputs into the database 
            int codeValue = Integer.parseInt(codeTXT.getText());
            String fnameValue = fnameTXT.getText();
            String lnameValue = lnameTXT.getText();
            String typeValue = (String) typeComboBox.getSelectedItem();
            boolean questionValue = yes.isSelected();

            try {
                boolean saved = raceDAO.save(codeValue, fnameValue, lnameValue, typeValue, questionValue);
                if (saved) {
                    JOptionPane.showMessageDialog(this, "Data saved successfully!");
                    // Perform any additional actions after saving the data
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save data.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        } else if (ae.getSource() == reset) {
            // Reset the input fields
            codeTXT.setText("");
            fnameTXT.setText("");
            lnameTXT.setText("");
            typeComboBox.setSelectedIndex(0);
            bg.clearSelection();
           
        } else if (ae.getSource() == exit) {
            // Exit the application
            System.exit(0);
        }
    }

    
    public static void main(String[] args) throws SQLException {
        new GUI();
    }
    
}
