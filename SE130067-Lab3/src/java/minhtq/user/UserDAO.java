/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import minhtq.utils.DBHealper;

/**
 *
 * @author admin
 */
public class UserDAO implements Serializable {

    List<UserDTO> list;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<UserDTO> getList() {
        return list;
    }

    private void closeDB() throws NamingException, SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean checkLogin(String email, String password) throws NamingException, SQLException {

        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "Select email "
                        + "From [User] "
                        + "Where email = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }

            }
        } finally {
            closeDB();
        }
        return false;
    }

    public String getFullName(String email) throws NamingException, SQLException {
        String fullname = "";
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "Select name "
                        + "From [User] "
                        + "Where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();
                while (rs.next()) {
                    fullname = rs.getString("name");
                }
            }
        } finally {
            closeDB();
        }
        return fullname;

    }

    public boolean insertAccount(String email, String password, String name, String status)
            throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "Insert into [User](email, password, name, status) "
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, name);
                stm.setString(4, status);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeDB();
        }
        return false;

    }
    
    public void insertDefaultRole(String email) throws NamingException, SQLException{
        int defaultRole = 2;
        String defaultRoleName = "Member";
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "INSERT into User_role (role_ID, email, role_Name) values(?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, defaultRole);
                stm.setString(2, email);
                stm.setString(3, defaultRoleName);
                stm.executeUpdate();
            }
        }finally{
            closeDB();
        }
    } 

    public UserDTO getAccount(String email, String password) throws NamingException, SQLException {

        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT email, password, name, status "
                        + "FROM [User] "
                        + "WHERE email = ? "
                        + "AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String status = rs.getString("status");
                    UserDTO dto = new UserDTO(email, password, name, status);
                    return dto;
                }
            }
        } finally {
            closeDB();
        }
        return null;
    }

    public int getUserRole(String email) throws NamingException, SQLException {
        int role = 0;
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT role_ID "
                        + "FROM User_role "
                        + "WHERE email = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while(rs.next()){
                     role = rs.getInt("role_ID");
                }
            }

        } finally {
            closeDB();
        }
        return role;
    }
    
    
}
