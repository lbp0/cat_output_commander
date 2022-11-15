
import java.math.BigInteger;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Statement;

public class MWrite
{
	MySql mysql;

	public static void sqlWrite(String year,String month,String day,String hour,String minute,String second,String popo,String bubu)//将列出的参数写入数据库 
	{
		String catIdStr = "" + year + month  + day + hour + minute  + second;
		long catId;
		catId = Long.parseLong(catIdStr);
		System.out.println(catId);
		Connection con = null ;
		PreparedStatement pstm = null ;

		// 数据库信息
		XmlLoader xmlLoader = new XmlLoader();
		String USER = xmlLoader.USER;
		String PASS = xmlLoader.PASS;
		String IPADDR = xmlLoader.IPADDR;
		String DB = xmlLoader.DB;
		String DB_TABLE = xmlLoader.DB_TABLE;

		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");   //mysql为例 不一样的数据库所需的驱动包不一样 连接语句略有不同 
			
			con = DriverManager.getConnection("jdbc:mysql://" + IPADDR + "/" + DB + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", USER, PASS);
			
			String sql = "INSERT INTO " + DB_TABLE + " (cat_id,cat_year,cat_month,cat_day,cat_hour,cat_minute,cat_popo,cat_bubu) VALUES (?,?,?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, catId);
			pstm.setString(2, year);
			pstm.setString(3, month);
			pstm.setString(4, day);
			pstm.setString(5, hour);
			pstm.setString(6, minute);
			pstm.setString(7, popo);
			pstm.setString(8, bubu);
			pstm.executeUpdate();
		
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		} finally {
		
			try {
			
				if(pstm != null) {
				
				pstm.close();
				
				pstm = null;
			
		}
		
			if (con != null) 
			{
				
				con.close();
				
				con = null;
				
			}
		
		} 
		catch (SQLException e)
		{
		
			e.printStackTrace();
		
		}
		
		}
	
	}
	
	public void sqlDel(String delIdStr)//将列出的参数写入数据库 
	{
		long delId;
		delId = Long.parseLong(delIdStr);
		Connection con = null ;
		PreparedStatement pstm = null ;

		// 数据库信息
		XmlLoader xmlLoader = new XmlLoader();
		String USER = xmlLoader.USER;
		String PASS = xmlLoader.PASS;
		String IPADDR = xmlLoader.IPADDR;
		String DB = xmlLoader.DB;
		String DB_TABLE = xmlLoader.DB_TABLE;
		
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");   //mysql为例 不一样的数据库所需的驱动包不一样 连接语句略有不同 
			
			con = DriverManager.getConnection("jdbc:mysql://" + IPADDR + "/" + DB + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", USER, PASS);

			String sql = "DELETE FROM " + DB_TABLE + " WHERE cat_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1,delId);
			pstm.executeUpdate();
		
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		} finally {
		
			try {
			
				if(pstm != null) {
				
				pstm.close();
				
				pstm = null;
			
		}
		
			if (con != null) 
			{
				
				con.close();
				
				con = null;
				
			}
		
		} 
		catch (SQLException e)
		{
		
			e.printStackTrace();
		
		}
		
		}
	
	}
	public void sqlChange(String changeIdStr,String quest)//将列出的参数写入数据库 
	{
		long changeId;
		changeId = Long.parseLong(changeIdStr);
		Connection con = null ;
		PreparedStatement pstm = null ;

		// 数据库信息
		XmlLoader xmlLoader = new XmlLoader();
		String USER = xmlLoader.USER;
		String PASS = xmlLoader.PASS;
		String IPADDR = xmlLoader.IPADDR;
		String DB = xmlLoader.DB;
		String DB_TABLE = xmlLoader.DB_TABLE;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");   //mysql为例 不一样的数据库所需的驱动包不一样 连接语句略有不同 

			con = DriverManager.getConnection("jdbc:mysql://" + IPADDR + "/" + DB + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", USER, PASS);
			if(quest.equals("尿尿"))//修改为尿尿
			{	
				String sql = "UPDATE " + DB_TABLE + " SET cat_popo=1,cat_bubu=0 WHERE cat_id=?";
				pstm = con.prepareStatement(sql);
				pstm.setLong(1,changeId);
				pstm.executeUpdate();
				mysql.showData();
			}
			else if(quest.equals("拉粑粑"))//修改为拉粑粑
			{	
				String sql = "UPDATE " + DB_TABLE + " SET cat_popo=0,cat_bubu=1 WHERE cat_id=?";
				pstm = con.prepareStatement(sql);
				pstm.setLong(1,changeId);
				pstm.executeUpdate();
				mysql.showData();
			}
			if(quest.equals("都有"))//修改为都有
			{	
				String sql = "UPDATE " + DB_TABLE + " SET cat_popo=1,cat_bubu=1 WHERE cat_id=?";
				pstm = con.prepareStatement(sql);
				pstm.setLong(1,changeId);
				pstm.executeUpdate();
				mysql.showData();
			}
			if(quest.equals("都没有"))//修改为都没有
			{
				String sql = "UPDATE " + DB_TABLE + " SET cat_popo=0,cat_bubu=0 WHERE cat_id=?";
				pstm = con.prepareStatement(sql);
				pstm.setLong(1,changeId);
				pstm.executeUpdate();
				mysql.showData();
			}
		
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		} finally {
		
			try {
			
				if(pstm != null) {
				
				pstm.close();
				
				pstm = null;
			
		}
		
			if (con != null) 
			{
				
				con.close();
				
				con = null;
				
			}
		
		} 
		catch (SQLException e)
		{
		
			e.printStackTrace();
		
		}
		
		}
	
	}


}