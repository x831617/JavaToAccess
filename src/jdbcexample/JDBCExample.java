/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.ucanaccess.jdbc.JackcessOpenerInterface;

/**
 *
 * @author KEN
 */
public class JDBCExample 
{
    public static void main(String[] args) throws SQLException 
    {
        Connection connDB = null;
        try
        {
            //建立驅動程式，連結odbc至Microsoft Access
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //下列字串的://之後要加上access檔案存放的地方
            String dataSource = "jdbc:ucanaccess://d:/Database1.accdb";
            //連結資料庫
            connDB = DriverManager.getConnection(dataSource);
            
            //SQL共有 INSERT、SELECT、UPDATE、DELETE，以下分別列舉
            Statement st = connDB.createStatement();
            //之後新增資料
            st.executeUpdate("INSERT INTO member (account, pwd) VALUES ('A','Taipei')");
            st.executeUpdate("INSERT INTO member (account, pwd) VALUES ('B','Taipei')");
            st.executeUpdate("INSERT INTO member (account, pwd) VALUES ('C','Taipei')");
            //撈出剛剛新增的資料
            st.execute("SELECT * FROM member");
            ResultSet rs = st.getResultSet();
            while(rs.next())
            {
                System.out.println(rs.getString("account")+" "+rs.getString("pwd"));
            }
            //刪除 account=c的資料
            st.executeUpdate("DELETE FROM member where account='A'");
            st.execute("SELECT * FROM member");
            ResultSet rs1 = st.getResultSet();
            while(rs1.next())
            {
                System.out.println(rs1.getString("account")+" "+rs1.getString("pwd"));
            }
            
            st.close();
            connDB.close();
        }catch(ClassNotFoundException e)
        {
            System.out.println("Driver loading failed!");
        }catch(SQLException e)
        {
            System.out.println("DB linking failed!");
        }     
    }
}
