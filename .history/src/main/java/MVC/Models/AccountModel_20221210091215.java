<<<<<<< HEAD
package MVC.Models;

import java.sql.Date;

public class AccountModel {
	private int accountId;
	private String userName;
	private String passWord;
	private String email;
	private Date createdDate;
	private int roleId;
	private int status;
	public AccountModel(int accountId, String userName, String passWord, String email, Date createdDate, int roleId, int status) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.createdDate = createdDate;
		this.roleId = roleId;
		this.status = status;
	}
	public AccountModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getAccountId() {
		return accountId;
	}
	/**
	 * @param id the id to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
=======
package MVC.Models;

import java.sql.Date;

public class AccountModel {
	private int accountId;
	private String userName;
	private String passWord;
	private String email;
	private Date createdDate;
	private int roleId;
	public AccountModel(int accountId, String userName, String passWord, String email, Date createdDate, int roleId) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.createdDate = createdDate;
		this.roleId = roleId;
	}
	public AccountModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getAccountId() {
		return accountId;
	}
	/**
	 * @param id the id to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	
	
}
>>>>>>> upstream/master
