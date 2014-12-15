/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anh
 */
public class MyConnect {
    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/calculator";
    private final String userName = "root";
    private final String password = "blue18894";
    
    public Connection connection;
    
    public void connect(){
        try {
            Class.forName(className);
            connection=DriverManager.getConnection(url, userName, password);
            System.out.println("Connect success");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!!!");
        } catch (SQLException ex) {
            System.out.println("Error connection!!!");
        }
    }
    
    public void InsertOpen(int n){
        String sqlCommand="insert into opentime(STT,open) value ("+n+",curtime())";
        PreparedStatement pst=null;
        try {
            pst=connection.prepareStatement(sqlCommand);
            if (pst.executeUpdate() > 0) {
                System.out.println("insert success!!!");
            } else {
                System.out.println("insert error!!!");
            }
        } catch (SQLException ex) {
             System.out.println("insert error!!!");
        }
    }
    
    public void InsertClose(int n){
        String sqlCommand="insert into closetime(STT,close) value ("+n+",curtime())";
        PreparedStatement pst=null;
        try {
            pst=connection.prepareStatement(sqlCommand);
            if (pst.executeUpdate() > 0) {
                System.out.println("insert success!!!");
            } else {
                System.out.println("insert error!!!");
            }
        } catch (SQLException ex) {
             System.out.println("insert error!!!");
        }
    }
    
    public int MaxSTT(){
        int n = 0;
        ResultSet rs = null;
        String sqlCommand="select max(STT) from opentime";
        Statement st;
        try {
            st=connection.createStatement();
            rs=st.executeQuery(sqlCommand);
            while(rs.next())
                n=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Error!!!");
        }
        return n+1; 
    }
    
    public void InsertOperation(int n,String s,String value){
        String sqlCommand="insert into operations(Stt,operation,result) value(?,?,?)";
        PreparedStatement pst=null;
        try {
            pst=connection.prepareStatement(sqlCommand);
            pst.setInt(1, n);
            pst.setString(2, s);
            pst.setString(3, value);
            if (pst.executeUpdate() > 0) {
                System.out.println("insert success!!!");
            } else {
                System.out.println("insert error!!!");
            }
        } catch (SQLException ex) {
             System.out.println("insert error!!!");
        } 
    }
    
    public int MaxStt(){
        int n=0;
        ResultSet rs=null;
        String sqlCommand="select max(Stt) from operations"; 
        PreparedStatement pst=null;
        try {
            pst=connection.prepareStatement(sqlCommand);
            rs=pst.executeQuery();
            while(rs.next())
                n=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Error!!!");
        }
        return n+1;
    }
    
    public ResultSet getDataTime(){
        ResultSet rs=null;
        String sqlCommand="select opentime.STT,open,close from opentime,closetime where opentime.STT=closetime.STT";
        Statement st=null;
        try {
            st=connection.createStatement();
            rs=st.executeQuery(sqlCommand);
        } catch (SQLException ex) {
            System.out.println("Error!!!");
        }
        return rs;
    }
    
    public ResultSet getDataOperation(){
        ResultSet rs=null;
        String sqlCommand="select *  from operations";
        Statement st=null;
        try {
            st=connection.createStatement();
            rs=st.executeQuery(sqlCommand);
        } catch (SQLException ex) {
            System.out.println("Error!!!");
        }
        return rs;
    }
}
