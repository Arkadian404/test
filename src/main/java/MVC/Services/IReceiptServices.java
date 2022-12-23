package MVC.Services;

import java.util.List;

import MVC.Models.CartModel;
import MVC.Models.ReceiptModel;

public interface IReceiptServices {
	void insert(ReceiptModel receipt);
	public List<ReceiptModel> findAllOfCheckOutedCarts(List<CartModel> checkOutedCartsOfUser);
}	
