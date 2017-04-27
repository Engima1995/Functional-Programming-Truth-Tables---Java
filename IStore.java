package p14184295;

import p14184295.List;

public class IStore implements Store {

	/**
	 * List<Boolean> value(Var var)
	 * 
	 * Returns a empty List of type Boolean first because initially the Lists start off empty
	 * 
	 * @param var = Represents the variable expression that is to be stored
	 * @return returns empty List of type Boolean
	 * */
	
	public List<Boolean> value(Var var) {
		return List.emptyList();
	}
	
	/**
	 * This method assigns the variable expression with a value Boolean and returns a new Class of IStore
	 * which has updated the value() method functionality to allow storing of further values.
	 * 
	 * @param var = Represents the variable expression that is to be stored
	 * @param val = Represents the Boolean value that is to be stored
	 * @return returns a new IStore class, that represents a new List of values instead of empty
	 * */

	public Store assign(Var var, Boolean val) {
		return new IStore() {
			public List<Boolean> value(Var maybeVar) {				//If the variable being passed to assign() is the same as variable being 
				if (maybeVar.toString().equals(var.toString()))		//passed to value(), then add a value to the List else assign the variable from
					return List.single(val);						//value() to the new returning class - IStore
				else
					return IStore.this.value(maybeVar);
			}
		};
	}
}
