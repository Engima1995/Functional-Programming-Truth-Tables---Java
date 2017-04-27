package p14184295;

import java.util.function.Function;

import p14184295.List;

public class Or extends BinaryOp {
	
	/**
	 * This Class constructs the method by requiring two Expressions which are passed to the Super Class
	 * BinaryOp, for operating on these two expressions
	 * 
	 * @param x1 = Represents the 1st Expression
	 * @param x2 = Represents the 2nd Expression
	 * */

	public Or(Expr x1, Expr x2) {
		super(x1, x2);								//Calls parent constructor to construct the two expressions
	}
	
	/**
	 * Function<Boolean, Function<Boolean, List<Boolean>>> operator()
	 * 
	 * This method is redefined with its own functionality, it is extended from the parent Class BinaryOp.
	 * It provides the Evaluating of two expressions in the form of Boolean and gets returned as a List of Boolean
	 * 
	 * @return returns a List of type Boolean which contains T or F values
	 * */

	public Function<Boolean, Function<Boolean, List<Boolean>>> operator() {
		return x -> y -> List.single(x || y);		//Evaluates the values as OR and stores it in to List of type Boolean and returns it
	}
}
