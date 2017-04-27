package p14184295;

import p14184295.List;

public class Const extends Expr {

	private final Boolean val;				//stores the value of type Boolean

	/**
	 * Const(Boolean d)
	 * 
	 * This constructor constructs the Class by passing a value of type Boolean and storing it locally
	 * 
	 * @param d = Represents the value to stored locally
	 * */
	
	public Const(Boolean d) {
		val = d;							//stores the value passed from argument, locally
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
		return List.single(this.val);		//Adds value to the front of the List
	}
	
	/**
	 * List<Var> variables()
	 * 
	 * This method extends variables() from Expr and provides its own definition for returning a empty List of Vars(),
	 * to indicate that this Class does not include any variables that can be passed.
	 * 
	 * @return returns an empty List of type Var
	 * */
	
	public List<Var> variables() {
		return List.emptyList();			//returns an empty list of vars, representing no vars is stored within this class
	}

	/**
	 * toString()
	 * 
	 * This method provides a description of the Boolean value
	 * 
	 * @return returns a Boolean value description
	 * */
	
	public String toString() {
			return val.toString();			//provides a description of the Boolean value
	}

}
