package com.audamob.doit.service;

import java.util.List;

import com.audamob.doit.model.Account;

public class UserService {

	/**
	 * After Authentication, we verify the presence of the current User in the
	 * Server Database, if NOT, we add A New Profile
	 * 
	 * @param user
	 */
	public void addNewUser(Account user) {

	}

	/**
	 * Get the user'information / Stream activities
	 * 
	 * @return Account
	 */
	public Account getUserInformation() {
		return null;

	}

	/**
	 * Get the list of all users
	 * 
	 * @return List<Account>
	 */
	public List<Account> getAllUsers() {
		return null;

	}

	/**
	 * Get Users by creteria 
	 * @param filter
	 * @return List<Account>
	 */
	public List<Account> getUsersByCreteria(String filter) {
		return null;

	}
}
