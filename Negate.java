package p14184295;

import java.util.function.Function;

import p14184295.List;

public class Negate extends UnaryOp {


		
	/**
	 * This Class constructs the method by requiring a single Expressions which are passed to the Super Class
	 * UnaryOp, for operating on this single expression
	 * 
	 * @param x1 = Represents the 1st Expression
	 * */

	public Negate(Expr x1) {
		super(x1);								//Calls parent constructor to construct the single expressions
	}
	
	/**
	 * Function<Boolean, List<Boolean>> operator()
	 * 
	 * This method is redefined with its own functionality, it is extended from the parent Class UnaryOp.
	 * It provides the Evaluating of a single expression in the form of Boolean and gets returned as a List of Boolean
	 * 
	 * @return returns a List of type Boolean which contains T or F values
	 * */

	public Function<Boolean, List<Boolean>> operator() {
		return x -> List.single(!x);		//Evaluates the values as NEGATE and stores it in to List of type Boolean and returns it
	}

}
