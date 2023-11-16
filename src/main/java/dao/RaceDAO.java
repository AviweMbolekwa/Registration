/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author aviwembolekwa
 */
public class RaceDAO {
    
    private final Connection con;
    
    public RaceDAO() throws SQLException{
        this.con = DBConnection.derbyConnection();
    }

    
    public Boolean save(int race_code, String first_name, String last_name, String race_type, Boolean belongs_to_club) throws SQLException{
        String insertSQL = "INSERT INTO RaceTable(race_code, first_name, last_name, race_type, belongs_to_club) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(insertSQL);
        ps.setInt(1, race_code);
        ps.setString(2, first_name);
        ps.setString(3, last_name);
        ps.setString(4, race_type);
        ps.setBoolean(5, belongs_to_club);
        ps.executeUpdate();
        ps.close();
        
        return true;
    }
    
    public void populateRaceTypes(JComboBox<String> typeComboBox) {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM RaceTypeTable");
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String race_type = resultSet.getString("race_type");
                int distance = resultSet.getInt("distance");
                String comboBoxItem = race_type + " (" + distance + "km)";
                typeComboBox.addItem(comboBoxItem);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
