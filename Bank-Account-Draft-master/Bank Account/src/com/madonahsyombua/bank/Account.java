import java.util.ArrayList;
import java.util.List;

// Subject interface that defines methods for registering, removing, and notifying observers
interface Subject {
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
}

// Concrete subject class that implements the Subject interface
class ConcreteSubject implements Subject {
	private List<Observer> observers = new ArrayList<>();
	private String subjectState;

	public void setState(String state) {
		this.subjectState = state;
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(subjectState);
		}
	}
}

// Observer interface that defines the update method
interface Observer {
	void update(String state);
}

// Concrete observer class that implements the Observer interface
class ConcreteObserver implements Observer {
	private String observerState;

	@Override
	public void update(String state) {
		observerState = state;
		display();
	}

	public void display() {
		System.out.println("Observer state: " + observerState);
	}
}

public class ObserverPatternExample {
	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();

		ConcreteObserver observer1 = new ConcreteObserver();
		ConcreteObserver observer2 = new ConcreteObserver();

		// Register observers to the subject
		subject.registerObserver(observer1);
		subject.registerObserver(observer2);

		// Change the state of the subject
		subject.setState("New State");

		// Remove observer1 from the subject
		subject.removeObserver(observer1);

		// Change the state of the subject again
		subject.setState("Another State");
	}
}
