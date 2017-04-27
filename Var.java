package p14184295;

import p14184295.List;

public class Var extends Expr {

	private final String name;		//stores the value of type String
	
	/**
	 * Var(String name)
	 * 
	 * This constructor constructs the Class by passing a value of type String and storing it locally
	 * 
	 * @param name = Represents the value to store locally
	 * */

	public Var(String name) {
		this.name = name;			//stores the value passed from argument, locally
	}

	/**
	 * List<Boolean> eval(Store sto)
	 * 
	 * This method extends eval() from Expr and provides its own definition for storing the value
	 * 
	 * @param sto = Represents the type of storage that will be passed for constants to be stored
	 * @return returns a List of type Boolean which contains the Boolean values
	 * */
	
	public List<Boolean> eval(Store sto) {
		return sto.value(this);		//Adds value to the passed argument
	}
	
	/**
	 * List<Var> variables()
	 * 
	 * This method extends variables() from Expr and provides its own definition for returning a List of type Var
	 * containing the value, representing the Variable
	 * 
	 * @return returns a List of type Var
	 * */
	
	public List<Var> variables() {
		return List.single(this);	//returns this List of type Var containing the current Var value
	}
	
	/**
	 * boolean equals()
	 * 
	 * This method overrides existing equals() method to provide functionality for comparing objects that are 
	 * of type Var. This helps identify for values that are the same, which is useful for removing duplicate values.
	 * 
	 * @param o - Represents the object that will be passed for comparing the values
	 * @return returns a true or false depending on the match of the object
	 * */
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass() || o == null) {
			return false;
		}else {
			Var obj = (Var) o;
			return this.name.equals(obj.name);
		}
	}
	
	/**
	 * public int hashCode()
	 * 
	 * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
	 * 
	 * @return a hash code value for this object.
	 * */
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * toString()
	 * 
	 * This method provides a description of the Boolean value
	 * 
	 * @return returns a Boolean value description
	 * */

	public String toString() {
		return name;				//provides a description of the String value
	}

}
