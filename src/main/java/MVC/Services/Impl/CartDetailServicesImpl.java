package MVC.Services.Impl;

import java.util.List;

import MVC.DAO.ICartDetailDAO;
import MVC.DAO.Impl.CartDetailDAOImpl;
import MVC.Models.CartDetailModel;
import MVC.Services.ICartDetailServices;

public class CartDetailServicesImpl implements ICartDetailServices {

	ICartDetailDAO cdDAO = new CartDetailDAOImpl();
	@Override
	public void insert(CartDetailModel cartDetail) {
		cdDAO.insert(cartDetail);
		
	}

	@Override
	public List<CartDetailModel> findAllByID() {
		// TODO Auto-generated method stub
		return cdDAO.findAllByID();
	}

}
