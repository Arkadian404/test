package MVC.Services.Impl;

import java.util.List;

import MVC.DAO.ICartDAO;
import MVC.DAO.Impl.CartDAOImpl;
import MVC.Models.CartModel;
import MVC.Services.ICartServices;

public class CartServicesImpl implements ICartServices {

	ICartDAO cartDAO = new CartDAOImpl();
	
	@Override
	public void insertNewCart(CartModel cart) {
		cartDAO.insertNewCart(cart);
		
	}

	@Override
	public int findCartId(int userId, String phone, String address) {
		// TODO Auto-generated method stub
		return cartDAO.findCartId(userId, phone, address);
	}

	@Override
	public void changeStatusToCheckedOut(int cartId) {
		cartDAO.changeStatusToCheckedOut(cartId);
		
	}

	@Override
	public List<CartModel> findAllCheckOutedOfUser(int userId) {
		// TODO Auto-generated method stub
		return cartDAO.findAllCheckOutedOfUser(userId);
	}

	@Override
	public List<CartModel> findAllValidOfUser(int userId) {
		// TODO Auto-generated method stub
		return cartDAO.findAllValidOfUser(userId);
	}

	@Override
	public List<CartModel> findAllWaitingOfUser(int userId) {
		// TODO Auto-generated method stub
		return cartDAO.findAllWaitingOfUser(userId);
	}

}
