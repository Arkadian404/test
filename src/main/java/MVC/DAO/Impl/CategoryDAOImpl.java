package MVC.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import MVC.DAO.ICategoryDAO;
import MVC.DBConnection.SqlConnect.DBConnection;
import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;

public class CategoryDAOImpl extends DBConnection implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub

		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "Select * from DanhMuc";
		try {
			Connection conn = super.getConnection();// goi ket noi sql
			PreparedStatement ps = conn.prepareStatement(sql);// nem sql vao cho phat bieu preparedStatement

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setStatus(rs.getInt(3));
				categories.add(category);
			}
		} catch (Exception e) {

		}
		return categories;
	}

	@Override
	public CategoryModel get(int cateId) {
		String sql = "SELECT * FROM DanhMuc WHERE MaDanhMuc = ? ";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cateId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setStatus(rs.getInt(3));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(CategoryModel category) {
		String sql = "INSERT INTO DanhMuc(TenDanhMuc, TinhTrang) VALUES (?,1)";
		try {
			Connection con = super.getConnection();// kết nối datavase
			PreparedStatement ps = con.prepareStatement(sql);// ném câu sql vào cho phát biểu prepared
			// gán tham số
			ps.setString(1, category.getCategoryName());
			// thực thi sql
			ps.execute();// dùng để update insert delete, còn dùng select thì executeNonQuery
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(CategoryModel category) {
		String sql = "Update DanhMuc set TenDanhMuc = ?, TinhTrang=? where MaDanhMuc=?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getStatus());
			ps.setInt(3, category.getCategoryID());
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "Delete from DanhMuc where MaDanhMuc=?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String args[]) {
		ICategoryDAO dao = new CategoryDAOImpl();
		List<CategoryModel> cl = new ArrayList<CategoryModel>();
		cl = dao.searchByCategoryName("cà phê bột", 1, 3);
		//int t = dao.countByCategoryNameSearch("Cà phê bột");
		System.out.println(cl);
	}

	@Override
	public CategoryModel findByID(int id) {
		String sql = "Select * From DanhMuc where MaDanhMuc=?";
		try {
			Connection conn = new DBConnection().getConnection(); // kết nối CSDL
			PreparedStatement ps = conn.prepareStatement(sql); // ném câu lệnh sql bằng phát biểu preparestatement
			ps.setInt(1, id); // đưa tham vào dấu ?
			ResultSet rs = ps.executeQuery(); // thực thi câu query và trả về Resultset
			while (rs.next()) { // duyệt từng dòng trong ResultSet trả về danh sách đối tượng
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setStatus(rs.getInt(3));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryModel> findAllByCategoryID(String id) {
		String sql = "select * from DanhMuc where MaDanhMuc = ?";
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setStatus(rs.getInt(3));
				categories.add(category);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return categories;
	}

	@Override
	public int count() {
		String sql = "select count(*) from DanhMuc";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<CategoryModel> pagingCategory(int index) {
		List<CategoryModel> categoryList = new ArrayList<CategoryModel>();
		String sql = "select * from DanhMuc order by MaDanhMuc OFFSET ? ROW fetch next 3 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (index-1)*3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				category.setStatus(rs.getInt(3));
				categoryList.add(category );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public List<CategoryModel> searchByCategoryName(String txtSearch, int index, int pageSize){
		String sql = "with temp as (select ROW_NUMBER() over (order by MaDanhMuc desc) as r, * from DanhMuc where TenDanhMuc like ?)\r\n"
				+ "select * from temp where r between ?*?-2 and ?*?";
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + txtSearch + "%");
			ps.setInt(2, index);
			ps.setInt(3, pageSize);
			ps.setInt(4, index);
			ps.setInt(5, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt(2));
				category.setCategoryName(rs.getString(3));
				category.setStatus(rs.getInt(4));
				categories.add(category);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	@Override
	public int countByCategoryNameSearch(String txtSearch) {
		String sql = "select count(*) from DanhMuc where TenDanhMuc like ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + txtSearch + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
}
