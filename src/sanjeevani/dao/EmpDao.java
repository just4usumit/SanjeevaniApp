/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevani.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.EmpPojo;

/**
 *
 * @author Sumit
 */
public class EmpDao {
    public static String getNewId()throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select max(empid) from employees");
        int id=1;
        if(rs.next())
        {
            String s=rs.getString(1).substring(1);
            int eno=Integer.parseInt(s);
            id=id+eno;
        }
        return("E"+id);
    }
    public static boolean addEmp(EmpPojo e) throws SQLException
{
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into employees values (?,?,?,?)");
    ps.setString(1,e.getEmpid());
    ps.setString(2,e.getEmpname());
    ps.setString(3,e.getJob());
    ps.setDouble(4,e.getSal());
    return(ps.executeUpdate()==1);
    
}
    public static ArrayList<EmpPojo> getAllEmployees() throws SQLException
    {
        ArrayList<EmpPojo>allEmp=new ArrayList<>();
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select * from employees");
        while(rs.next())
        {
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            allEmp.add(e);
        }
        return allEmp;
    }
    public static EmpPojo getEmpById(String id) throws SQLException
    {
        EmpPojo e=null;
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from employees where empid=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            
        }
        return e;
    }
    
    public static int updateEmp(EmpPojo e)throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update employees set empname=? role=? sal=? where empid=?");
        ps.setString(1,e.getEmpname());
        ps.setString(2,e.getJob());
        ps.setDouble(3,e.getSal());
        ps.setString(4,e.getEmpid());
        return(ps.executeUpdate());
        
    }
    public static int deleteEmp(String empid)throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from employees where empid=?");
        ps.setString(1,empid);
        return ps.executeUpdate();
    }
}


