/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevani.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.UserPojo;

/**
 *
 * @author Sumit
 */
public class UserDao {
    public static String validateUser(UserPojo user)throws SQLException
    {
        System.out.println(user);
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select username from users where userid=? and usertype=? and password=?");
        ps.setString(2, user.getUsertype());
        ps.setString(1,user.getUserId());
        ps.setString(3, user.getPassword());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
        }
        return username;
    }
    
    
}
