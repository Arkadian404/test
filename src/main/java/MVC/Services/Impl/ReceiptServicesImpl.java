package MVC.Services.Impl;

import java.util.List;

import MVC.DAO.IReceiptDAO;
import MVC.DAO.Impl.ReceiptDAOImpl;
import MVC.Models.CartModel;
import MVC.Models.ReceiptModel;
import MVC.Services.IReceiptServices;

public class ReceiptServicesImpl implements IReceiptServices {

	IReceiptDAO receiptDAO = new ReceiptDAOImpl();
	
	@Override
	public void insert(ReceiptModel receipt) {
		receiptDAO.insert(receipt);
		
		
	}

	@Override
	public List<ReceiptModel> findAllOfCheckOutedCarts(List<CartModel> checkOutedCartsOfUser) {
		// TODO Auto-generated method stub
		return receiptDAO.findAllOfCheckOutedCarts(checkOutedCartsOfUser);
	}

}
