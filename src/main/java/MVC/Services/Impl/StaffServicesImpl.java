package MVC.Services.Impl;

import java.util.List;

import MVC.DAO.IStaffDAO;
import MVC.DAO.Impl.StaffDAOImpl;
import MVC.Models.StaffModel;
import MVC.Services.IStaffServices;

public class StaffServicesImpl implements IStaffServices{

	IStaffDAO staffDAO = new StaffDAOImpl();
	
	@Override
	public void delete(int id) {
		staffDAO.delete(id);
		
	}

	@Override
	public void edit(StaffModel staff) {
		staffDAO.edit(staff);
		
	}

	@Override
	public void insert(StaffModel staff) {
		staffDAO.insert(staff);
		
	}

	@Override
	public StaffModel findById(int MaNV) {
		// TODO Auto-generated method stub
		return staffDAO.findById(MaNV);
	}

	@Override
	public List<StaffModel> findAll() {
		// TODO Auto-generated method stub
		return staffDAO.findAll();
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return staffDAO.countAll();
	}

	@Override
	public List<StaffModel> pagingStaff(int index) {
		// TODO Auto-generated method stub
		return staffDAO.pagingStaff(index);
	}

	@Override
	public int countByStaffNameSearch(String txtSearch) {
		// TODO Auto-generated method stub
		return staffDAO.countByStaffNameSearch(txtSearch);
	}

	@Override
	public List<StaffModel> searchByStaffName(String txtSearch, int index, int pageSize) {
		// TODO Auto-generated method stub
		return staffDAO.searchByStaffName(txtSearch, index, pageSize);
	}

}
