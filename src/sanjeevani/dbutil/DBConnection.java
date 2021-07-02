/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevani.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sumit
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//Ayushi-PC/xe","myhms","students");
            JOptionPane.showMessageDialog(null,"Connection done","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException c)
                {
                    JOptionPane.showMessageDialog(null,"Can't load driver","Error",JOptionPane.ERROR_MESSAGE);
                    c.printStackTrace();
                }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"SQL Error"+e,"Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            if(conn!=null){
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection closed successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"SQL Error"+e,"Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
}
