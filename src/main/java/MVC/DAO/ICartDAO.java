package MVC.DAO;

import java.util.List;

import MVC.Models.CartModel;

public interface ICartDAO {
	void insertNewCart(CartModel cart);

	int findCartId(int userId, String phone, String address);

	void changeStatusToCheckedOut(int cartId);

	List<CartModel> findAllCheckOutedOfUser(int userId);

	List<CartModel> findAllValidOfUser(int userId);

	List<CartModel> findAllWaitingOfUser(int userId);
}
