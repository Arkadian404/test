package MVC.DAO;

import java.util.List;

import MVC.Models.CartDetailModel;

public interface ICartDetailDAO {
	void insert(CartDetailModel cartDetail);
	List<CartDetailModel> findAllByID();
}
