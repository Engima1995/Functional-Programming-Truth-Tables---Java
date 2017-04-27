package p14184295;

import p14184295.List;

public class Let extends Expr {

	private final Expr val, exp;		//This Class provides a value and expression variable for storing the Const: T, F and further expression
	private Var var;					//such as new And(new Var(a), new Var(b) and also provides var variable for variables to be stored
	
	/**
	 * This constructor allows the assigning of a variable, a Const value as an expression and further expression that can be
	 * defined for constructing a complete expression.
	 * 
	 * @param var = Represents the variable to be stored
	 * @param val = Represents the Const expression to be stored
	 * @param exp = Represents a new expression that can be passed for constructing a complicated expression
	 * */
	
	public Let(Var var, Expr val, Expr exp) {
		this.var = var;
		this.val = val;
		this.exp = exp;
	}

	/**
	 * List<Boolean> eval(Store sto)
	 * 
	 * This method extends eval() from Expr and provides its own definition for storing the value
	 * 
	 * In this class, the values are evaluated and stored within Lists as well as being assigned against a variable,
	 * which is stored within the new expression, and the procedure for future expressions follow the same path, allowing
	 * good recursion and easy finding of values.
	 * 
	 * @param sto = Represents the type of storage that will be passed for constants to be stored
	 * @return returns a List of type Boolean which contains the Boolean values
	 * */
	
	public List<Boolean> eval(Store sto) {
		return	bind(val.eval(sto), x ->				//Stores the value of the val and assigns a variable name within the exp
				     exp.eval(sto.assign(var, x)));		//this way, it will allow to go over the lists in the future for finiding any values
	}
	
	/**
	 * List<Var> variables()
	 * 
	 * This method extends variables() from Expr and provides its own definition for recursively returning the variable
	 * results from the expression.
	 * 
	 * @return returns recursively the results of type Var
	 * */

	public List<Var> variables() {
		return exp.variables();
	}
	
	/**
	 * toString()
	 * 
	 * This method provides a description of the expression values
	 * 
	 * @return returns a expression value description
	 * */
	
	public String toString() {
		return "(let " + var + " = " + val + " in " + exp + ")";
	}
}
