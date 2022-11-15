import java.awt.Container;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
 
public class MySql 
{
	WindowUI wui;
    public void showData()
    {
    	 
        // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
        //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
     
        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    	int i = 0;
    	String dateStr;
    	String timeStr;


        // 数据库信息
        XmlLoader xmlLoader = new XmlLoader();
        String USER = xmlLoader.USER;
        String PASS = xmlLoader.PASS;
        String IPADDR = xmlLoader.IPADDR;
        String DB = xmlLoader.DB;
        String DB_TABLE = xmlLoader.DB_TABLE;
    	
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://" + IPADDR + ":3306/" + DB + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    	
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT cat_id,cat_year,cat_month,cat_day,cat_hour,cat_minute,cat_popo,cat_bubu FROM " + DB_TABLE;
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            i = 0;
            while(rs.next())
            {
                // 通过字段检索
                String catId = rs.getString("cat_id");
                String catYear = rs.getString("cat_year");
                String catMonth = rs.getString("cat_month");
                String catDay = rs.getString("cat_day");
                String catHour = rs.getString("cat_hour");
                String catMinute = rs.getString("cat_minute");
                String catPopo = rs.getString("cat_popo");
                String catBubu = rs.getString("cat_bubu");
                
    
                // 输出数据
                dateStr = catYear + '年' + catMonth + '月' + catDay + '日';
                timeStr = catHour + ':' + catMinute;
                /*
                wui.table.getModel().setValueAt(catId,0,i);
                wui.table.getModel().setValueAt(dateStr,1,i);
                wui.table.getModel().setValueAt(timeStr,2,i);
                wui.table.getModel().setValueAt(catPopo,3,i);
                wui.table.getModel().setValueAt(catBubu,4,i);*/
                if(i < wui.table.getRowCount())
                {	
	                wui.table.getModel().setValueAt(catId,i,0);
	                wui.table.getModel().setValueAt(dateStr,i,1);
	                wui.table.getModel().setValueAt(timeStr,i,2);
	                wui.table.getModel().setValueAt(catPopo,i,3);
	                wui.table.getModel().setValueAt(catBubu,i,4);
                }
                else
                {
                	Vector row = new Vector();
                	row.add(0,catId);
                	row.add(1,dateStr);
                	row.add(2,timeStr);
                	row.add(3,catPopo);
                	row.add(4,catBubu);
                	((DefaultTableModel) wui.table.getModel()).addRow(row);
				}
                i ++;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}