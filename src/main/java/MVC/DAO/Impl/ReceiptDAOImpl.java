package MVC.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import MVC.DAO.IReceiptDAO;
import MVC.DBConnection.SqlConnect.DBConnection;
import MVC.Models.CartModel;
import MVC.Models.ReceiptModel;


public class ReceiptDAOImpl extends DBConnection implements IReceiptDAO {

	@Override
	public void insert(ReceiptModel receipt) {
		String sql = "Insert Into HoaDon(MaGH,NgayXuatHD) Values(?,?)";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, receipt.getCartID());
			ps.setDate(2, receipt.getReceiptDate());
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ReceiptModel> findAllOfCheckOutedCarts(List<CartModel> checkOutedCartsOfUser) {
		List<ReceiptModel> receipts = new ArrayList<ReceiptModel>();
		String sql = "Select * From HoaDon Where MaGH = ?";
		try {
			Connection conn = super.getConnection();
			for(CartModel validCartOfUser : checkOutedCartsOfUser)
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				int validCartOfUserId = validCartOfUser.getCartID();
				ps.setInt(1, validCartOfUserId);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					ReceiptModel receipt= new ReceiptModel();
					receipt.setReceiptID(rs.getInt("MaHoaDon"));
					receipt.setCartID(rs.getInt("MaGH"));
					receipt.setReceiptDate(rs.getDate("NgayXuatHD"));
					receipts.add(receipt);
					break;
				}				
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return receipts;
	}

}
