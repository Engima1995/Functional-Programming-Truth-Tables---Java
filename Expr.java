package p14184295;

import static p14184295.Parser.*;
import static p14184295.List.concat;
import static p14184295.List.emptyList;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import p14184295.LazyParser;
import p14184295.Parser;

public abstract class Expr {
	
	/**
	 * List<Boolean> bind(List<Boolean> m,Function<Boolean,List<Boolean>> f) 
	 * 
	 * This method provides a powerful way to combine a List of Booleans with a Function,
	 * mapping each individual item within the List with the Function provided.
	 * 
	 * @param m = Represents the List of type Boolean 
	 * @param f = Represents the Function that will be applied to the List of type Boolean
	 * @return returns either a empty list depending on the passed parameter or returns a list that has mapped the Function f to all items
	 * within the list
	 * */
	
	public static List<Boolean> bind(List<Boolean> m, Function<Boolean,List<Boolean>> f) {
		if (m.isEmpty())
			return emptyList();			//returns empty List if the passed List is empty
		else
			return concat(m.map(f));	//otherwise concatenates the passed function to the all items within the passed List
	}
	
	/**
	 * List<Boolean> eval(Store sto)
	 * 
	 * This method is an abstract method that provides extending Classes with the ability to
	 * define their own expression for evaluating the Expressions.
	 * 
	 * @param sto = Represents the type of storage that will be passed for constants to be stored
	 * */

	public abstract List<Boolean> eval(Store sto);
	
	/**
	 * List<Var> variables()
	 * 
	 * This method is an abstract method that provides extending Classes with the ability to
	 * define their own expression for returning the List of variables that are present deep within
	 * the Expression Classes.
	 * */
	
	public abstract List<Var> variables();
	
	/**
	 * List<List<Const>> tt(int n)
	 * 
	 * This method creates the truth table by passing the number of variables to this method.
	 * 1 variable means it will return the values [[T], [F]].
	 * 2 variables means it will return the value: [[T,T], [T,F], [F,T], [F,F]]
	 * ... and so on.
	 * 
	 * @param n = Represents the number of variables passed to this method
	 * @return returns a List of List of type Const, this represents the truth table, created to its entirety and recursively just by passing a few numbers 
	 * */
	
	public static List<List<Const>>	tt(int n) {
		
		//1. Constructs a truth table by passing the number of variables to this method
		//2. If the number is one then the List constructs a value of T and F and returns the List of Lists of type Consts
		//3. Otherwise recursively create the table by appending the truth and false values as many times as there are the number of values, taken away by 1
		
		if(n == 1) 
			return List.cons(List.single(new Const(false)), List.emptyList()).addFront(List.single(new Const(true)));
		else 
			return tt(n-1).map(x -> x.addFront(new Const(true))).append(tt(n-1).map(y -> y.addFront(new Const(false))));
		
	}
	
	/**
	 * Expr createLet(Pair<Var, Const> pairs, Expr e)
	 * 
	 * This method helps create a template for a recursive call, which requires the input of Pairs and an Expression,
	 * which will create further calls and returns the whole as an Expression.
	 * 
	 * @param pairs = Represents the Var/Const Pairs that needs to be passed to this method in order to create the expression
	 * @param e = Represents the expression to further call
	 * 
	 * @return returns a new Expression that has been constructed using a recursive nature
	 * */
	
	public static Expr createLet(Pair<Var, Const> pairs, Expr e) {
		//pairs.fst() is the Var within the Pair Class, pairs.snd() is the Const within the Const Class and e is the expression
		return new Let(pairs.fst(), pairs.snd(), e);
	}
	
	/**
	 * List<Expr> wrap(Expr e)
	 * 
	 * This method helps create new Let Expressions. It involves the use of first retrieving the number of variables
	 * that will be passed to the method tt(), which then in return creates a set of Lists of type Const, containing
	 * the truth table. Once creation of the table is completed, it is best to pass an Expression such as e = new And(new Var(a), new Var(b))
	 * to this function which represents how the evaluation of the truth table should be done.
	 * 
	 * In order to start evaluating, it is of best interest to Pair up the Lists of type Consts to the individual Vars which basically means
	 * the number of variables there are. Once the mapping of these Const values are done to their corresponding Vars, they can now
	 * be passed as a Let expression.
	 * 
	 * The Let expression creates a new Expression holding the values from the truth table as well as being defined by the variables, and
	 * returns that for further evaluation using the isTautology() method.
	 * 
	 * @param e - Represents the expression that is passed to the method, which will be the expression for providing the bases to create new Let expressions
	 * @return - returns the List expressions with Let, ready for evaluation.
	 * */
	
	public static List<Expr> wrap(Expr e) {
		
		//1. First creates a list of pairs by passing the number of variables within the passed argument,
		//   that creates a List of Lists of type Const, which are mapped to give Lists of type Const 
		//2. They are then zipped with the Variables returned by the passed argument to create a Pair of (Var, Const)
		//3. They are then stored in a variable called rows
		//4. Now the rows gets mapped to return Lists of pair which are then used for creating the Let expression
		//5. The Expressions are then returned as a List of expressions
		
		List<List<Pair<Var, Const>>> rows = tt(e.variables().length()).map(x -> e.variables().zipWith(x, Pair<Var, Const>::new));
		return rows.map(x -> x.foldr((y, z) -> Expr.createLet(y, z), e));
	}
	
	/**
	 * isTautology(List<Expr> ls)
	 * 
	 * This method helps evaluate the List of Expressions that are passed to this method, to help determine
	 * whether the list that was wrapped, is either a tautology or not.
	 * 
	 * @param ls = Represents the List of Expressions that will be passed for evaluation  
	 * @return returns a true or a false depending on whether it is a tautology
	 * */
	
	public static boolean isTautology(List<Expr> ls) {
		
		//1. The List of expressions is passed to this method
		//2. They are then mapped to return the individual expressions which are evaluated and stored
		//3. Once the mapping is done, Lists of Boolean is returned, which is stored in a variable of type List of Lists of Boolean
		//4. Now a if statement is used to check the result from the Lists of type Boolean, if there is a false that means a tautology was not made
		//5. otherwise return true, to indicate the tautology was made
		
		List<List<Boolean>> xs = ls.map(x -> x.eval(new IStore()));
		
		if(xs.any(x -> x.head() == false)) {
			return false;
		}else {
			return true;
		}
		
	}
	
	//This sets the variables for the characters that are needed for the parser to compute
	
	public static Parser<Character>	lparen = chomp(character('(')), rparen = chomp(character(')')), andop = chomp(character('&')), orop = chomp(character('+')), negateop = chomp(character('~')), trueconst = chomp(character('T')), falseconst = chomp(character('F'));
	
	//This is no different to the above Parser<Character> but the reason this is a String is because it requires more characters which forms a string
	//however it does the same job
	
	static Parser<String> equivop = chomp(string("==")), impop = chomp(string("=>"));

	/**
	 * Parser<Expr> constant()
	 * 
	 * This method returns a True or False constant
	 * 
	 * @return - returns a constant: T/F
	 * */
	
	public static Parser<Expr> constant() {
		return trueconst.plus(falseconst).bind(x -> returning(new Const(x.equals('T'))));
	}

	/**
	 * Parser<Expr> variable()
	 * 
	 * This method looks for lower characters and returns a new Var Expression in a String format
	 * 
	 * @return - returns a String formatted through lowercase characters
	 * */
	public static Parser<Expr> variable() {
		return chomp(lower()).bind(x -> returning(new Var(x.toString())));
	}
	
	/**
	 * Parser<BinaryOperator<Expr>> addop()
	 * 
	 * This method combines various different Binary operations together. During the execution
	 * it will check for which operation is provided and executes the operation between two variables.
	 * 
	 * @return - returns the matching operation to do on an expression
	 * */

	public static Parser<BinaryOperator<Expr>> addop() {
		
		BinaryOperator<Expr> and  = (t1,t2) -> (Expr) new And(t1,t2);
		BinaryOperator<Expr> or = (t1,t2) -> (Expr) new Or(t1,t2);
		BinaryOperator<Expr> imp = (t1,t2) -> (Expr) new Implication(t1,t2);
		BinaryOperator<Expr> equiv = (t1,t2) -> (Expr) new Equivalence(t1,t2);

		return andop.seq(returning(and)).plus(orop.seq(returning(or))).plus(impop.seq(returning(imp))).plus(equivop.seq(returning(equiv)));
	}
	
	//This gets the factor() parser from the LazyParser, which is used for factoring the queries

	public static LazyParser<Expr> factor = new LazyParser<Expr>(() -> factor());
	
	/**
	 * Parser<Expr> factor()
	 * 
	 * This method is a way for factoring the expression that has been passed to the Parser. It uses individual 
	 * factoring options for the Expression to execute otherwise fails if nothing matches.
	 * 
	 * @return - returns the options for factoring against what the factor method provides.
	 * */

	public static Parser<Expr> factor() {
		return constant().plus(variable()).plus(negateop.seq(factor.bind(f -> returning((Expr) new Negate(f))))).plus(expr().bracket(lparen, rparen));
	}
	
	//This gets the expr() parser from the LazyParser, which is used for chaining expressions

	public static LazyParser<Expr> expr = new LazyParser<Expr>(() -> expr());
	
	/**
	 * Parser<Expr> expr()
	 * 
	 * This method combines several methods from this Class to give a powerful way to factor and match operations
	 * against an expression. Allowing the building of Expressions which can be further evaluated
	 * 
	 * @return - returns a way to evaluate expressions
	 * */
	
	public static Parser<Expr> expr() {
		return factor.chainl1(addop());
	}

}
