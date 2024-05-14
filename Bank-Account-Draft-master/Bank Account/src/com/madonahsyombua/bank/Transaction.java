package com.madonahsyombua.bank;

import java.util.Date;

// Component interface
interface TransactionComponent {
	double getAmount();
	String getSummaryLine();
}

// Concrete component
class SimpleTransaction implements TransactionComponent {
	private double amount;
	private Date timestamp;
	private String memo;

	public SimpleTransaction(double amount, String memo) {
		this.amount = amount;
		this.timestamp = new Date();
		this.memo = memo;
	}

	@Override
	public double getAmount() {
		return this.amount;
	}

	@Override
	public String getSummaryLine() {
		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s,", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s,", this.timestamp.toString(), this.amount, this.memo);
		}
	}
}

// Decorator
abstract class TransactionDecorator implements TransactionComponent {
	protected TransactionComponent decoratedTransaction;

	public TransactionDecorator(TransactionComponent decoratedTransaction) {
		this.decoratedTransaction = decoratedTransaction;
	}

	@Override
	public double getAmount() {
		return decoratedTransaction.getAmount();
	}

	@Override
	public String getSummaryLine() {
		return decoratedTransaction.getSummaryLine();
	}
}

// Concrete decorators
class EncryptedTransaction extends TransactionDecorator {
	public EncryptedTransaction(TransactionComponent decoratedTransaction) {
		super(decoratedTransaction);
	}

	@Override
	public String getSummaryLine() {
		// Encrypt the summary line
		String summary = super.getSummaryLine();
		// Perform encryption here
		return "Encrypted: " + summary;
	}
}

class LoggedTransaction extends TransactionDecorator {
	public LoggedTransaction(TransactionComponent decoratedTransaction) {
		super(decoratedTransaction);
	}

	@Override
	public String getSummaryLine() {
		// Log the summary line
		String summary = super.getSummaryLine();
		// Log the summary here
		return "Logged: " + summary;
	}
}
