package MVC.Services;

import java.util.List;

import MVC.Models.StaffModel;

public interface IStaffServices {

	void delete(int id);

	void edit(StaffModel staff);

	void insert(StaffModel staff);

	StaffModel findById(int MaNV);

	List<StaffModel> findAll();

	int countAll();
	
	List<StaffModel> pagingStaff(int index);
	
	int countByStaffNameSearch(String txtSearch);

	List<StaffModel> searchByStaffName(String txtSearch, int index, int pageSize);
}
