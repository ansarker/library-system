
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    
    public Connection connect(){
        String url = "jdbc:sqlite://home/void/database/LibSys.db";
                
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
//        finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        }
        return conn;        
    }
    
    public void create(String query, String[] dataList) {        
        try {
            conn = this.connect();
            pstmt = conn.prepareStatement(query);
            for(int i = 0; i < dataList.length; i++) {
//                System.out.println(dataList[i]);
                pstmt.setString(i+1, dataList[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
    public void read(String query) {
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                System.out.println(rs.getArray(1));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void update() {
        
    }
    
    public void delete() {
        
    }
    
    
}
