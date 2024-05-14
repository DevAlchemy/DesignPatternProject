public interface LegacyAccountInterface {
	void legacyGetUUID();
	void legacyGetSummaryLine();
	// Define other legacy methods here
}

public class LegacyAccountAdapter implements LegacyAccountInterface {
	private Account account;

	public LegacyAccountAdapter(Account account) {
		this.account = account;
	}

	@Override
	public void legacyGetUUID() {
		System.out.println("Legacy UUID: " + account.getUUID());
	}

	@Override
	public void legacyGetSummaryLine() {
		System.out.println("Legacy Summary Line: " + account.getSummaryLine());
	}

	// Implement other legacy methods by delegating to corresponding Account methods
}
