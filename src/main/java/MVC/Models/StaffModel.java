package MVC.Models;

import java.sql.Date;

public class StaffModel {
	private int staffID;
	private String staffName;
	private Date DOB;
	private String sex;
	private String staffPhone;
	private String salaryID;
	private int accountID;
	private int status;
	public StaffModel(int staffID, String staffName, Date dOB, String sex, String staffPhone, String salaryID,
			int accountID, int status) {
		super();
		this.staffID = staffID;
		this.staffName = staffName;
		DOB = dOB;
		this.sex = sex;
		this.staffPhone = staffPhone;
		this.salaryID = salaryID;
		this.accountID = accountID;
		this.status = status;
	}
	public StaffModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the staffID
	 */
	public int getStaffID() {
		return staffID;
	}
	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	/**
	 * @return the dOB
	 */
	public Date getDOB() {
		return DOB;
	}
	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the staffPhone
	 */
	public String getStaffPhone() {
		return staffPhone;
	}
	/**
	 * @param staffPhone the staffPhone to set
	 */
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	/**
	 * @return the salaryID
	 */
	public String getSalaryID() {
		return salaryID;
	}
	/**
	 * @param salaryID the salaryID to set
	 */
	public void setSalaryID(String salaryID) {
		this.salaryID = salaryID;
	}
	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}