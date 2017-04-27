package p14184295;

import p14184295.List;
import java.util.function.Function;

public abstract class UnaryOp extends Expr {

	private final Expr exp;				//Creates one private Expressions which holds the expression for evaluating

	/**
	 * UnaryOp(Expr x1)
	 * 
	 * This constructor provides the ability to pass one expression arguments which is stored locally,
	 * for later computation
	 * 
	 * @param x1 = Represents Expression 1
	 * */
	
	protected UnaryOp(Expr x) {
		this.exp = x;
	}
	
	/**
	 * Function<Boolean, List<Boolean>> operator()
	 * 
	 * Provides an abstract function for extending classes to have their own definition of how to operate
	 * on the one expressions that has been passed. Each extending Class represents a Unary Operation
	 * */

	public abstract Function<Boolean,List<Boolean>> operator();

	/**
	 * List<Boolean> eval(Store sto)
	 * 
	 * This method extends eval() from Expr and provides its own definition for storing the value
	 * 
	 * In this class, the expression values are evaluated and operated on, which allows the storing of the values
	 * as well as providing a boolean answer to the expressions
	 * 
	 * @param sto = Represents the type of storage that will be passed for constants to be stored
	 * @return returns a List of type Boolean which contains the Boolean values
	 * */
	
	public List<Boolean> eval(Store sto) {
		return List.concat(exp.eval(sto).map(this.operator()));		//Stores the values from the expression and does the operation for calculating the boolean result
	}																//and returning a List of type Boolean
	
	/**
	 * List<Var> variables()
	 * 
	 * This method extends variables() from Expr and provides its own definition for recursively returning the variable
	 * results from the one expression value that was passed.
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
		return this.getClass().getSimpleName() + "(" + exp + ")";
	}

}
