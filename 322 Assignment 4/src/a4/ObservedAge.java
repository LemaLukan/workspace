package a4;

import java.util.Observable;

public class ObservedAge extends Observable {
	private int age;
	public void addAge(int age) {
		this.age = age;
		this.setChanged();
		this.notifyObservers(this.age);
	}

}
