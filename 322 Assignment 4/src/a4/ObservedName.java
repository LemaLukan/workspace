package a4;

import java.util.Observable;

public class ObservedName extends Observable {
	private String name;

	public void addName(String name) {
		this.name = name;
		this.setChanged();
		this.notifyObservers(this.name);
	}
}
