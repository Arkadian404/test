package MVC.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import MVC.DAO.ICartDetailDAO;
import MVC.DBConnection.SqlConnect.DBConnection;
import MVC.Models.CartDetailModel;

public class CartDetailDAOImpl extends DBConnection implements ICartDetailDAO {

	@Override
	public void insert(CartDetailModel cartDetail) {
		String sql = "Insert Into GioHang_ChiTiet(MaSP,SoLuong,GiaTien,MaGH) Values(?,?,?,?)";
		try {
			Connection conn = super.getConnection();//getConnetion ket noi db
			PreparedStatement ps = conn.prepareStatement(sql);//ném câu sql vào để thực thi
			ps.setInt(1, cartDetail.getProductID());
			ps.setInt(2, cartDetail.getAmount());
			ps.setInt(3, cartDetail.getPrice());
			ps.setInt(4, cartDetail.getCartID());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CartDetailModel> findAllByID() {
		// TODO Auto-generated method stub
		return null;
	}

}
