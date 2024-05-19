// Context class
class TransactionContext {
	private TransactionState state;

	public TransactionContext() {
		this.state = new PendingState();
	}

	public void setState(TransactionState state) {
		this.state = state;
	}

	public void process() {
		state.process(this);
	}
}

// State interface
interface TransactionState {
	void process(TransactionContext context);
}

// Concrete states
class PendingState implements TransactionState {
	@Override
	public void process(TransactionContext context) {
		System.out.println("Processing pending transaction...");
		// Perform pending transaction processing logic
		// Transition to the next state if needed
		context.setState(new ProcessedState());
	}
}

class ProcessedState implements TransactionState {
	@Override
	public void process(TransactionContext context) {
		System.out.println("Transaction has been processed.");
		// Perform processed transaction logic
		// Transition to other states if needed
	}
}

public class StatePatternExample {
	public static void main(String[] args) {
		TransactionContext context = new TransactionContext();

		// Process the transaction in the initial state
		context.process();

		// Change the state and process the transaction again
		context.setState(new ProcessedState());
		context.process();
	}
}
