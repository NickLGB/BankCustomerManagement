package com.intern.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBeanProcess {

	private Statement sm = null;
	private ResultSet rs = null;
	private Connection ct = null;
	private int pageSize = 3;
	private int rowCount = 0; // get the value from database query
	private int pageCount = 0;

	// Delete user:
	public boolean delUserById(String id) {
		boolean b = false;

		try {
			// get connection
			ct = new ConnectDB().getConn();

			sm = ct.createStatement();

			int a = sm.executeUpdate("delete from users where userId='" + id
					+ "'");

			if (a == 1) {
				// delete success
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}

	// Add user
	public boolean addUser(String userName, String passwd, String email, String phoneNumber, String address) {
		boolean b = false;

		try {
			// get connection
			ct = new ConnectDB().getConn();

			sm = ct.createStatement();

			int a = sm.executeUpdate("insert into users values('"+userName+"','"+passwd+"','"+email+"','"+phoneNumber+"','"+address+"')");

			if (a == 1) {
				// add success
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}
	
	// Update User:
	public boolean updateUser(String userId, String userName, String passwd, String email, String phoneNumber, String address){
		boolean b = false;	 

		try {
			// get connection
			String sql = "update users set username=?, passwd=?, email=?, phoneNumber=?, address=? where userId=?";
			ct = new ConnectDB().getConn();

			PreparedStatement psm = ct.prepareStatement(sql);
			psm.setString(1, userName);
			psm.setString(2, passwd);
			psm.setString(3, email);
			psm.setString(4, phoneNumber);
			psm.setString(5, address);
			psm.setInt(6, Integer.parseInt(userId));
			b = psm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		
		return !b;
	}

	// return the result of pageCount
	public int getPageCount() {
		try {
			// Get connection
			ct = new ConnectDB().getConn();

			sm = ct.createStatement();
			// Calculate page count
			rs = sm.executeQuery("select count(*) from users");

			if (rs.next()) {
				rowCount = rs.getInt(1);
			}

			// calculate pageCount
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return pageCount;
	}

	// Paging
	public List<UserBean> getUsersByPage(int pageNow) {

		List<UserBean> al = new ArrayList<UserBean>();
		try {

			ct = new ConnectDB().getConn();

			sm = ct.createStatement();

			// search the record in shown page
			rs = sm.executeQuery("select top " + pageSize
					+ " * from users where userId not in (select top "
					+ pageSize * (pageNow - 1) + " userId from users) ");

			// encapsulate rs to arraylist
			while (rs.next()) {
				UserBean ub = new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setPhoneNumber(rs.getString(5));
				ub.setAddress(rs.getString(6));

				al.add(ub);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return al;
	}

	public UserBean getUserByUserName(String userName) {
		UserBean ub = new UserBean();
		try {

			String sql = "select * from users where userName=?";
			ct = new ConnectDB().getConn();

			PreparedStatement psm = ct.prepareStatement(sql);
			psm.setString(1, userName);
			rs = psm.executeQuery();		
					
			while (rs.next()) {
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setPhoneNumber(rs.getString(5));
				ub.setAddress(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return ub;
	}
	
	// close database connection resource
	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkUser(String userName, String password) {

		boolean b = false;

		try {
			ct = new ConnectDB().getConn();

			sm = ct.createStatement();
			rs = sm.executeQuery("select passwd from users where userName='"
					+ userName + "'");

			if (rs.next()) {
				if (rs.getString(1).equals(password)) {
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return b;
	}
	
	public List<TranData> getUserTransactions(String userId){
		List<TranData> txnList = new ArrayList<TranData>();
		try {
			ct = new ConnectDB().getConn();
			sm = ct.createStatement();		
			rs = sm.executeQuery("select * from usertransaction where userId='"+userId+"'");

			while (rs.next()) {
				TranData txnData = new TranData();
				txnData.setTranId(rs.getInt("tranId"));
				txnData.setUserId(rs.getInt("userId"));
				txnData.setDate(rs.getString("date"));
				txnData.setTranType(rs.getString("tranType"));
				txnData.setAmt(rs.getDouble("amt"));
				txnData.setBalance(rs.getDouble("balance"));
				txnList.add(txnData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return txnList;
	}
}
