package MVC.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import MVC.DAO.ICartDAO;
import MVC.DBConnection.SqlConnect.DBConnection;
import MVC.Models.CartModel;

public class CartDAOImpl extends DBConnection implements ICartDAO {

	@Override
	public void insertNewCart(CartModel cart) {
		String sql = "Insert Into GioHang(MaTK,ThoiGianMua,TinhTrang,SDT,DiaChi) Values(?,?,0,?,?)";
		try {
			Connection conn = super.getConnection();// getConnetion ket noi db
			PreparedStatement ps = conn.prepareStatement(sql);// ném câu sql vào để thực thi
			ps.setInt(1, cart.getAccountID());
			ps.setDate(2, cart.getPurchaseDate());
			ps.setString(3, cart.getPhone());
			ps.setString(4, cart.getAddress());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int findCartId(int userId, String phone, String address) {
		try {
			// 2 là user mặc định sau khi đăng ký có role là user
			// phải là cart gần đây nhất được tạo
			// để khi thêm các cartItem cartId trỏ tới đúng là cart vừa tạo nếu 1 user nhập
			// trùng sđt và địa chỉ
			String sql = "Select MaGH From GioHang Where MaTK = ? And SDT = ? And DiaChi = ? Order By MaGH Desc";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, phone);
			ps.setString(3, address);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("MaGH");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void changeStatusToCheckedOut(int cartId) {
		String sql = "Update GioHang Set TinhTrang = 2 Where MaGH = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cartId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CartModel> findAllWaitingOfUser(int userId) {
		List<CartModel> carts = new ArrayList<CartModel>();
		String sql = "Select * From GioHang Where MaTK = ? And TinhTrang = 0";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CartModel cart = new CartModel();
				cart.setCartID(rs.getInt("MaGH"));
				cart.setAccountID(userId);
				cart.setPurchaseDate(rs.getDate("ThoiGianMua"));
				cart.setStatus(rs.getInt("TinhTrang"));
				cart.setPhone(rs.getString("SDT"));
				cart.setAddress(rs.getNString("DiaChi"));
				carts.add(cart);
			}
			return carts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CartModel> findAllValidOfUser(int userId) {
		List<CartModel> carts = new ArrayList<CartModel>();
		String sql = "Select * From GioHang Where MaTK = ? And TinhTrang = 1";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CartModel cart = new CartModel();
				cart.setCartID(rs.getInt("MaGH"));
				cart.setAccountID(userId);
				cart.setPurchaseDate(rs.getDate("ThoiGianMua"));
				cart.setStatus(rs.getInt("TinhTrang"));
				cart.setPhone(rs.getString("SDT"));
				cart.setAddress(rs.getNString("DiaChi"));
				carts.add(cart);
			}
			return carts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<CartModel> findAllCheckOutedOfUser(int userId) {
		List<CartModel> carts = new ArrayList<CartModel>(); 
		String sql = "Select * From GioHang Where TinhTrang = 2 And MaTK = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CartModel cart = new CartModel();
				cart.setCartID(rs.getInt("MaGH"));
				cart.setAccountID(userId);
				cart.setPurchaseDate(rs.getDate("ThoiGianMua"));
				cart.setStatus(rs.getInt("TinhTrang"));
				cart.setPhone(rs.getString("SDT"));
				cart.setAddress(rs.getNString("DiaChi"));
				carts.add(cart);
			}
			return carts;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
