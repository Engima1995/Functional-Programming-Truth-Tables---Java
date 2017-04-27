package p14184295;

public class Pair<A, B> {
	private final A a;		//These variables represent two types of Classes which are stored here
	private final B b;

	/**
	 * This Class constructs the value from two different classes and storing it into one class
	 * 
	 * @param a = Represents a value of type A
	 * @param b = Represents a value of type B
	 * */
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	/**
	 * fst() 
	 * 
	 * Gets the Class of type A
	 * 
	 * @return returns the value for Class A
	 * */

	public A fst() {
		return a;
	}
	
	/**
	 * snd() 
	 * 
	 * Gets the Class of type B
	 * 
	 * @return returns the value for Class B
	 * */

	public B snd() {
		return b;
	}
	
	/**
	 * toString()
	 * 
	 * This method provides a description of the pair values
	 * 
	 * @return returns a pair value description
	 * */

	@Override
	public String toString() {
		return "(" + a + "," + b + ")";
	}
}