package p14184295;

import p14184295.List;
import java.util.function.Function;

public abstract class BinaryOp extends Expr {

	private final Expr exp1, exp2;				//Creates two private Expressions which holds the expressions for evaluating

	/**
	 * BinaryOp(Expr x1, Expr x2)
	 * 
	 * This constructor provides the ability to pass two expression arguments which are stored locally,
	 * for later computation
	 * 
	 * @param x1 = Represents Expression 1
	 * @param x2 = Represents Expression 2
	 * */
	
	protected BinaryOp(Expr x1, Expr x2) {
		this.exp1 = x1;
		this.exp2 = x2;
	}
	
	/**
	 * Function<Boolean, Function<Boolean, List<Boolean>>> operator()
	 * 
	 * Provides an abstract function for extending classes to have their own definition of how to operate
	 * on the two expressions that have been passed. Each extending Class represents a Binary Operation
	 * */

	public abstract Function<Boolean, Function<Boolean, List<Boolean>>> operator();

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
		return	bind( exp1.eval(sto), x ->				//Binds the two expressions by mapping values to Store
				bind( exp2.eval(sto), y ->				//Then applying the values to the operator and storing the boolean result to List of type Boolean
				this.operator().apply(x).apply(y)));
	}
	
	/**
	 * List<Var> variables()
	 * 
	 * This method extends variables() from Expr and provides its own definition for recursively returning the variable
	 * results from the two expression values that were passed, it returns distinct values with no duplicates.
	 * 
	 * @return returns recursively the results of type Var with no duplicates
	 * */
	
	public List<Var> variables() {
		return List.streamToList(exp1.variables().append(exp2.variables()).listToStream().distinct());
	}
	
	/**
	 * toString()
	 * 
	 * This method provides a description of the expression values
	 * 
	 * @return returns a expression value description
	 * */

	public String toString() {
		return "(" + exp1 + " " + this.getClass().getSimpleName() + " " + exp2 + ")";
	}

}
