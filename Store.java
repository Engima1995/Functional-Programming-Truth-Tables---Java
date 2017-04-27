package p14184295;

import p14184295.List;

public interface Store {
	
	/**
	 * List<Boolean> value(Var var)
	 * 
	 * Provides a method for storing the variable results
	 * 
	 * @param var = Represents the var expression to be stored
	 * */
	public List<Boolean> value(Var var);
	
	/**
	 * assign(Var var, Boolean val)
	 * 
	 * Provides a method for assigning a value Boolean to the List retrieved from the Var expression, assigning a value to var e.g. a = T
	 * 
	 * @param var = Represents the var expression to be stored
	 * @param val = Represents the val to be stored
	 * */
	public Store assign(Var var, Boolean val);
}
