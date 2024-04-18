package com.madonahsyombua.bank;

import java.util.ArrayList;

public class Account {

	private static Account instance; // Static instance variable

	private String name;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> transaction;

	// Private constructor to prevent instantiation from outside
	private Account(String name, User holder, Bank theBank) {
		/**set the account name and holder**/
		this.name = name;
		this.holder = holder;
		/** get account uuid**/
		this.uuid = theBank.getNewAccontUUID();

		/**initialize transaction**/
		this.transaction = new ArrayList<Transaction>();
	}

	// Static method to get the instance of Account
	public static Account getInstance(String name, User holder, Bank theBank) {
		if (instance == null) {
			instance = new Account(name, holder, theBank);
		}
		return instance;
	}

	// Other methods remain the same...

	// getUUID(), getSummaryLine(), getBalance(), printTransHistory(), addTransaction()
}
