package MVC.DAO;

import java.util.List;

import MVC.Models.CartModel;
import MVC.Models.ReceiptModel;

public interface IReceiptDAO {
	void insert(ReceiptModel receipt);

	List<ReceiptModel> findAllOfCheckOutedCarts(List<CartModel> checkOutedCartsOfUser);
}
