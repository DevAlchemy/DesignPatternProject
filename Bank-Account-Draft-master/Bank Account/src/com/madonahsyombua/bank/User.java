// UserFactory interface
public interface UserFactory {
	User createUser(String firstName, String lastName, String pin, Bank theBank);
}

// Concrete implementation of UserFactory
public class SimpleUserFactory implements UserFactory {
	@Override
	public User createUser(String firstName, String lastName, String pin, Bank theBank) {
		return new User(firstName, lastName, pin, theBank);
	}
}

// User class with Factory Method pattern applied
public class User {

	private String firstName;
	private String lastName;
	private String uuid;
	private byte pinHash[];
	private ArrayList<Account> accounts;

	// Constructor is now private, so users must use a UserFactory to create User objects
	private User(String firstName, String lastName, String pin, Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error, Caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		this.uuid = theBank.getNewUserUUID();
		this.accounts = new ArrayList<>();
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
	}

	// Public static method to create User objects using the factory
	public static User createUser(String firstName, String lastName, String pin, Bank theBank) {
		UserFactory userFactory = new SimpleUserFactory();
		return userFactory.createUser(firstName, lastName, pin, theBank);
	}

	public void addAccount(Account onAcct) {
		this.accounts.add(onAcct);
	}

	public String getUUID() {
		return this.uuid;
	}

	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error, Caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void printAccountsSummary() {
		System.out.printf("\n\n%s Accounts summary\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d. %s\n Accounts summary\n", a + 1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}

	public int numAccounts() {
		return this.accounts.size();
	}

	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}

	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
}
