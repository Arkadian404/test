<<<<<<< HEAD
package MVC.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import MVC.DAO.IProductWebDAO;
import MVC.DBConnection.SqlConnect.DBConnection;
import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Services.ICategoryServices;
import MVC.Services.Impl.CategoryServicesImpl;

public class ProductWebDAOImpl extends DBConnection implements IProductWebDAO {

	ICategoryServices categoryService = new CategoryServicesImpl();

	@Override
	public List<ProductModel> selectAll() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select * from SanPham\n";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> getTop4Product() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select TOP 4 * from SanPham\n" + "order by MaSP DESC";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));

				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel topProduct() {
		ProductModel product = new ProductModel();
		String sql = "select top 1 * from SanPham order by SoLuong Desc ";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<ProductModel> getAllByCateID(String cateID) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select * from SanPham where MaDanhMuc = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cateID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel getProductByID(String prodID) {
		String sql = "select * from SanPham where MaSP = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, prodID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @Override public List<ProductModel> findAllPage(int index) {
	 * List<ProductModel> productList = new ArrayList<ProductModel>(); String sql =
	 * "select Product.productId,Product.productName,Product.productCode,Product.description,Product.amount,Product.price,Product.images,Product.createDate,Product.stock,\r\n"
	 * +
	 * " Product.wishlist,Product.status, Category.categoryId,Category.categoryName\r\n"
	 * + "from Product\r\n" +
	 * "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n" +
	 * "ORDER BY productid DESC OFFSET ? rows fetch next 3 rows only"; try {
	 * Connection conn = new DBConnection().getConnection(); PreparedStatement ps =
	 * conn.prepareStatement(sql); ps.setInt(1, index); ResultSet rs =
	 * ps.executeQuery(); while (rs.next()) { CategoryModel category =
	 * categoryService.findByID(rs.getInt("categoryid")); ProductModel product = new
	 * ProductModel(); product.setProductID(rs.getInt("productid"));
	 * product.setProductCode(rs.getLong("productCode"));
	 * product.setProductName(rs.getString("productName"));
	 * product.setProductAmount(rs.getInt("amount"));
	 * product.setProductDescription(rs.getString("description"));
	 * product.setProductImage(rs.getString("images"));
	 * product.setProductPrice(rs.getDouble("price"));
	 * product.setProductStock(rs.getInt("stock"));
	 * product.setProductWhishlist(rs.getInt("wishlist"));
	 * product.setProductStatus(rs.getInt("status"));
	 * product.setCreatedDate(rs.getDate("createDate"));
	 * product.setCategory(category); productList.add(product); } } catch (Exception
	 * e) { e.printStackTrace(); } return productList;
	 * 
	 * }
	 */

	@Override
	public int countAll() {
		String sql = "select count(*) from SanPham";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ProductModel> searchByProductName(String txtSearch, int index, int pageSize) {
		String sql = "with temp as (select ROW_NUMBER() over (order by SoLuong desc) as r, * from SanPham where TenSanPham like ?)\r\n"
				+ "select * from temp where r between ?*?-2 and ?*?";
		List<ProductModel> products = new ArrayList<ProductModel>();
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
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(2));
				product.setProductName(rs.getString(3));
				product.setProductAmount(rs.getInt(4));
				product.setProductPrice(rs.getInt(5));
				product.setProductDescription(rs.getString(6));
				product.setProductImage(rs.getString(7));
				product.setProductStatus(rs.getInt(8));
				product.setCategoryID(rs.getInt(9));
				products.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public static void main(String args[]) {
		IProductWebDAO test = new ProductWebDAOImpl();
		List<ProductModel> p = test.searchByProductName("cà phê hạt", 1, 3);
		// int t = test.countByProductNameSearch("cà phê hạt");
		for (ProductModel t : p) {
			System.out.println(t);
		}
	}

	@Override
	public List<ProductModel> pagingProduct(int index) {
		List<ProductModel> productList = new ArrayList<ProductModel>();
		String sql = "select * from SanPham order by MaSP OFFSET ? ROW fetch next 3 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (index - 1) * 3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;

	}

	@Override
	public int countByCategoryID(int id) {
		String sql = "select count(*) from SanPham where MaDanhMuc = ?";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ProductModel> pagingProductByCateID(int id, int index) {
		List<ProductModel> productList = new ArrayList<ProductModel>();
		String sql = "select * from SanPham where MaDanhMuc = ? order by MaDanhMuc OFFSET ? ROW fetch next 3 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, (index - 1) * 3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<ProductModel> select3LastProduct() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select top(3) * from SanPham order by MaSP desc\n";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel findByID(int id) {
		ProductModel product = new ProductModel();
		String sql = "select * from SanPham where MaSP = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void insert(ProductModel product) {
		String sql = "INSERT INTO SanPham(TenSanPham,SoLuong,GiaTien,MoTa,Anh, TinhTrang,MaDanhMuc) VALUES (?,?,?,?,?,?,?)";
		try {
			Connection con = super.getConnection();// kết nối datavase
			PreparedStatement ps = con.prepareStatement(sql);// ném câu sql vào cho phát biểu prepared
			// gán tham số
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getProductAmount());
			ps.setInt(3, product.getProductPrice());
			ps.setString(4, product.getProductDescription());
			ps.setString(5, product.getProductImage());
			ps.setInt(6, product.getProductStatus());
			ps.setInt(7, product.getCategoryID());
			// thực thi sql
			ps.execute();// dùng để update insert delete, còn dùng select thì executeNonQuery
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(ProductModel product) {

		String sql = "UPDATE SanPham set TenSanPham =?,SoLuong=?,GiaTien=?,MoTa=?,Anh=?, TinhTrang=?,MaDanhMuc=? where MaSP = ?";
		try {
			Connection con = super.getConnection();// kết nối datavase
			PreparedStatement ps = con.prepareStatement(sql);// ném câu sql vào cho phát biểu prepared
			// gán tham số
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getProductAmount());
			ps.setInt(3, product.getProductPrice());
			ps.setString(4, product.getProductDescription());
			ps.setString(5, product.getProductImage());
			ps.setInt(6, product.getProductStatus());
			ps.setInt(7, product.getCategoryID());
			ps.setInt(8, product.getProductID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "Delete from SanPham where MaSP=?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public int countByProductNameSearch(String txt) {
		String sql = "select count(*) from SanPham where TenSanpham like ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + txt + "%");
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
=======
package MVC.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import MVC.DAO.IProductWebDAO;
import MVC.DBConnection.SqlConnect.DBConnection;
import MVC.Models.CategoryModel;
import MVC.Models.ProductModel;
import MVC.Services.ICategoryServices;
import MVC.Services.Impl.CategoryServicesImpl;

public class ProductWebDAOImpl extends DBConnection implements IProductWebDAO {

	ICategoryServices categoryService = new CategoryServicesImpl();

	@Override
	public List<ProductModel> selectAll() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select * from SanPham\n";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> getTop4Product() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select TOP 4 * from SanPham\n" + "order by MaSP DESC";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));			
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));

				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel topProduct() {
		ProductModel product = new ProductModel();
		String sql = "select top 1 * from SanPham order by SoLuong Desc ";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));			
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<ProductModel> getAllByCateID(String cateID) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select * from SanPham where MaDanhMuc = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cateID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel getProductByID(String prodID) {
		String sql = "select * from SanPham where MaSP = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, prodID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	/*@Override
	public List<ProductModel> findAllPage(int index) {
		List<ProductModel> productList = new ArrayList<ProductModel>();
		String sql = "select Product.productId,Product.productName,Product.productCode,Product.description,Product.amount,Product.price,Product.images,Product.createDate,Product.stock,\r\n"
				+ " Product.wishlist,Product.status, Category.categoryId,Category.categoryName\r\n" + "from Product\r\n"
				+ "INNER JOIN Category ON Product.categoryId = Category.categoryId\r\n"
				+ "ORDER BY productid DESC OFFSET ? rows fetch next 3 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, index);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = categoryService.findByID(rs.getInt("categoryid"));
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt("productid"));
				product.setProductCode(rs.getLong("productCode"));
				product.setProductName(rs.getString("productName"));
				product.setProductAmount(rs.getInt("amount"));
				product.setProductDescription(rs.getString("description"));
				product.setProductImage(rs.getString("images"));
				product.setProductPrice(rs.getDouble("price"));
				product.setProductStock(rs.getInt("stock"));
				product.setProductWhishlist(rs.getInt("wishlist"));
				product.setProductStatus(rs.getInt("status"));
				product.setCreatedDate(rs.getDate("createDate"));
				product.setCategory(category);
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;

	}*/

	@Override
	public int countAll() {
		String sql = "select count(*) from SanPham";
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
	public List<ProductModel> searchByProductName(String txtSearch) {
		 String sql = "select * from SanPham where TenSanPham like ?";
		 List<ProductModel> products = new ArrayList<ProductModel>();
			try {
				Connection conn = super.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "%"+txtSearch+"%");
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					ProductModel product = new ProductModel();
					product.setProductID(rs.getInt(1));
					product.setProductName(rs.getString(2));
					product.setProductAmount(rs.getInt(3));
					product.setProductPrice(rs.getInt(4));
					product.setProductDescription(rs.getString(5));			
					product.setProductImage(rs.getString(6));
					product.setProductStatus(rs.getInt(7));
					product.setCategoryID(rs.getInt(8));
					products.add(product);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return products;
	}
	 
	 public static void main(String args[]) {
		 IProductWebDAO test = new ProductWebDAOImpl();
		 List<ProductModel> p = test.searchByProductName("hongkong");
		 System.out.println(p);
	 }

	@Override
	public List<ProductModel> pagingProduct(int index) {
		List<ProductModel> productList = new ArrayList<ProductModel>();
		String sql = "select * from SanPham order by MaSP OFFSET ? ROW fetch next 3 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (index-1)*3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));			
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;

	}
	@Override
	public int countByCategoryID(int id) {
		String sql = "select count(*) from SanPham where MaDanhMuc = ?";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<ProductModel> pagingProductByCateID(int id, int index) {
		List<ProductModel> productList = new ArrayList<ProductModel>();
		String sql = "select * from SanPham order by MaSP OFFSET ? ROW fetch next 3 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(index, (index-1)*3);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));			
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	@Override
	public List<ProductModel> select3LastProduct() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		String sql = "select top(3) * from SanPham order by MaSP desc\n";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel findByID(int id) {
		ProductModel product = new ProductModel();
		String sql = "select * from SanPham where MaSP = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setProductID(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductAmount(rs.getInt(3));
				product.setProductPrice(rs.getInt(4));
				product.setProductDescription(rs.getString(5));			
				product.setProductImage(rs.getString(6));
				product.setProductStatus(rs.getInt(7));
				product.setCategoryID(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void insert(ProductModel product) {
		String sql = "INSERT INTO SanPham(TenSanPham,SoLuong,GiaTien,MoTa,Anh, TinhTrang,MaDanhMuc) VALUES (?,?,?,?,?,?,?)";
		try {
			Connection con = super.getConnection();// kết nối datavase
			PreparedStatement ps = con.prepareStatement(sql);// ném câu sql vào cho phát biểu prepared
			// gán tham số
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getProductAmount());
			ps.setInt(3, product.getProductPrice());
			ps.setString(4, product.getProductDescription());
			ps.setString(5, product.getProductImage());
			ps.setInt(6, product.getProductStatus());
			ps.setInt(7, product.getCategoryID());
			// thực thi sql
			ps.execute();// dùng để update insert delete, còn dùng select thì executeNonQuery
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void edit(ProductModel product) {
		
		String sql = "UPDATE SanPham set TenSanPham =?,SoLuong=?,GiaTien=?,MoTa=?,Anh=?, TinhTrang=?,MaDanhMuc=? where MaSP = ?";
		try {
			Connection con = super.getConnection();// kết nối datavase
			PreparedStatement ps = con.prepareStatement(sql);// ném câu sql vào cho phát biểu prepared
			// gán tham số
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getProductAmount());
			ps.setInt(3, product.getProductPrice());
			ps.setString(4, product.getProductDescription());
			ps.setString(5, product.getProductImage());
			ps.setInt(6, product.getProductStatus());
			ps.setInt(7, product.getCategoryID());
			ps.setInt(8, product.getProductID());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "Delete from SanPham where MaSP=?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
>>>>>>> upstream/master
