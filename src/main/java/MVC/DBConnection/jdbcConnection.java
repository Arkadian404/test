package MVC.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class jdbcConnection {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = new SqlConnect.DBConnection().getConnection();
		Statement statement = connection.createStatement();
		String sql = "Select * from Category";
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			int cateId = rs.getInt(1);
			String cateName = rs.getString(2);
			String cateImages= rs.getString("images");
			int cateStatus=rs.getInt("status");
			
			System.out.println("---------------------------");
			System.out.println("CateID: "+cateId);
			System.out.println("CateName: "+cateName);
			System.out.println("CateImg: "+cateImages);
			System.out.println("CateStatus: "+cateStatus);
		}
		connection.close();
	}
}
