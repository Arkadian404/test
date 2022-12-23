package MVC.Services;

import java.util.List;

import MVC.Models.CartDetailModel;

public interface ICartDetailServices {
	void insert(CartDetailModel cartDetail);
	List<CartDetailModel> findAllByID();
}
