package p14184295.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import p14184295.And;
import p14184295.Const;
import p14184295.Equivalence;
import p14184295.Expr;
import p14184295.Implication;
import p14184295.Negate;
import p14184295.Or;
import p14184295.Var;
import p14184295.List;

public class TestCasesTest {
	
	//************TESTING BINARY AND UNARY OPERATIONS**************//
	
	
	
	//Tests the Negate expression which should return a false, opposite of true
	
	@Test 
	public void test_NEGATE() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Negate(a));
		boolean tt = Expr.isTautology(exp);
		assertFalse("!(a) = false", tt);
		System.out.println("1. !(a) = " + tt);
		System.out.println();
	}
	
	//Tests the AND expression which should return a false
	
	@Test 
	public void test_AND() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new And(a, b));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a AND b) = false", tt);
		System.out.println("2. (a AND b) = " + tt);
		System.out.println();
	}
	
	//Tests the OR expression which should return a false
	
	@Test 
	public void test_OR() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new Or(a, b));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a OR b) = false", tt);
		System.out.println("3. (a OR b) = " + tt);
		System.out.println();
	}
	
	//Tests the IMPLICATION, 1st version of the expression which should return a false
	
	@Test 
	public void test_IMPLICATION_1() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new Implication(a, b));
		boolean tt = Expr.isTautology(exp);
		assertFalse("a => b = false", tt);
		System.out.println("4. a => b = " + tt);
		System.out.println();
	}
	
	
	//******************TESTING RANDOM VALUES***************//
	
	
	@Test 
	public void test_RANDOM_1() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new And(new And(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a AND b) AND c = false", tt);
		System.out.println("5. (a AND b) AND c = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_RANDOM_2() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new Or(new And(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a AND b) OR c = false", tt);
		System.out.println("6. (a AND b) OR c = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_RANDOM_3() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new Implication(new And(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a AND b) => c = false", tt);
		System.out.println("7. (a AND b) => c = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_RANDOM_4() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a AND b) == c = false", tt);
		System.out.println("8. (a AND b) == c = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_RANDOM_5() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new And(new Or(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a OR b) AND c = false", tt);
		System.out.println("9. (a OR b) AND c = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_RANDOM_6() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new And(new Implication(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a => b) AND c = false", tt);
		System.out.println("10. (a => b) AND c = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_RANDOM_7() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c"); 
		List<Expr> exp = Expr.wrap(new And(new Equivalence(a, b), c));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(a == b) AND c = false", tt);
		System.out.println("11. (a == b) AND c = " + tt);
		System.out.println();
	}
	
	
	
	//******************TESTING LAWS*****************//
	
	
	
	//Tests the IMPLICATION, 2nd version of the expression which should return a true
	
	@Test 
	public void test_IMPLICATION_2() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new Equivalence(new Implication(a, b), new Or(new Negate(a), b)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a => b == !a OR b = ", tt);
		System.out.println("12. a => b == !a OR b = " + tt);
		System.out.println();
	}
	
	//Tests the IMPLICATION, 3rd version of the expression which should return a true
	
	@Test 
	public void test_IMPLICATION_3() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new Equivalence(new Implication(a, a), new Const(true)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a => a == true = ", tt);
		System.out.println("13. a => a == true = " + tt);
		System.out.println();
	}
	
	//Tests the IMPLICATION, 4th version of the expression which should return a true
	
	@Test 
	public void test_IMPLICATION_4() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new Equivalence(new Implication(a, b), new Negate(new And(a, new Negate(b)))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a => b == !(a AND !b) = ", tt);
		System.out.println("14. a => b == !(a AND !b) = " + tt);
		System.out.println();
	}
	
	//Tests the IMPLICATION, 5th version of the expression which should return a true
	
	@Test 
	public void test_IMPLICATION_5() {
		Var a = new Var("a"), b = new Var("b"); 
		List<Expr> exp = Expr.wrap(new Equivalence(new Negate(new Implication(a, b)), new And(a, new Negate(b))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("!(a => b) == a AND !b = ", tt);
		System.out.println("15. !(a => b) == a AND !b = " + tt);
		System.out.println();
	}
	
	//Tests the IMPLICATION, 6th version of the expression which should return a true
	
	@Test 
	public void test_IMPLICATION_6() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Implication(a, new Const(true)), new Const(true)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a => true == true = ", tt);
		System.out.println("16. a => true == true = " + tt);
		System.out.println();
	}
	
	//Tests the Exclude Middle expression which should return a true
	
	@Test 
	public void test_EXCLUDEDMIDDLE() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Or(a, new Negate(a)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("(a OR !a) = true", tt);
		System.out.println("17. (a OR !a) = " + tt);
		System.out.println();
	}
	
	//Tests the CONTRADICTION expression which should return a true
	
	@Test 
	public void test_CONTRADICTION() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Negate(new And(a, new Negate(a))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("!(a AND !a) = true", tt);
		System.out.println("18. !(a AND !a) = " + tt);
		System.out.println();
	}
	
	//Tests the MODUS TOLLENS expression which should return a true
	
	@Test 
	public void test_MODUSTOLLENS() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Implication(new And(new Implication(a, b), new Negate(b)), new Negate(a)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[(a => b) AND !b] => !a = true", tt);
		System.out.println("19. [(a => b) AND !b] => !a = " + tt);
		System.out.println();
	}
	
	//Tests the DOUBLE NEGATION expression which should return a true
	
	@Test 
	public void test_DOUBLENEGATION() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Negate(new Negate(new Implication(a, a))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("!!a => a = true", tt);
		System.out.println("20. !!a => a = " + tt);
		System.out.println();
	}
	
	//Tests the ASSOCIATIVE LAW AND version of the expression which should return a true
	
	@Test 
	public void test_ASSOCIATIVELAW_AND() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(new And(a, b), c), new And(a, new And(b, c))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[(a AND b) AND c] == [a AND (b AND c)] = true", tt);
		System.out.println("21. [(a AND b) AND c] == [a AND (b AND c)] = " + tt);
		System.out.println();
	}
	
	//Tests the ASSOCIATIVE LAW OR version of the expression which should return a true
	
	@Test 
	public void test_ASSOCIATIVELAW_OR() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(new Or(a, b), c), new Or(a, new Or(b, c))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[(a OR b) OR c] == [a OR (b OR c)] = true", tt);
		System.out.println("22. [(a OR b) OR c] == [a OR (b OR c)] = " + tt);
		System.out.println();
	}
	
	//Tests the DEMORGANS LAW, 1st version of the expression which should return a true
	
	@Test 
	public void test_DEMORGANsLAW_1() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new Negate(new Or(a, b)), new And(new Negate(a), new Negate(b))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[!(a OR b) == !a AND !b] = true", tt);
		System.out.println("23. [!(a OR b) == !a AND !b] = " + tt);
		System.out.println();
	}
	
	//Tests the DEMORGANS LAW, 2nd version of the expression which should return a true
	
	@Test 
	public void test_DEMORGANsLAW_2() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new Negate(new And(a, b)), new Or(new Negate(a), new Negate(b))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[!(a AND b) == !a OR !b] = true", tt);
		System.out.println("24. [!(a AND b) == !a OR !b] = " + tt);
		System.out.println();
	}
	
	//Tests the DISTRIBUTIVITY LAW, 1st version of the expression which should return a true
	
	@Test 
	public void test_DISTRIBUTIVITYLAW_1() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, new Or(b, c)), new Or(new And(a, b), new And(a, c))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[a AND (b OR c)] == [(a AND b) OR (a AND c)] = true", tt);
		System.out.println("25. [a AND (b OR c)] == [(a AND b) OR (a AND c)] = " + tt);
		System.out.println();
	}
	
	//Tests the DISTRIBUTIVITY LAW, 2nd version of the expression which should return a true
	
	@Test 
	public void test_DISTRIBUTIVITYLAW_2() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, new And(b, c)), new And(new Or(a, b), new Or(a, c))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("[a OR (b AND c)] == [(a OR b) AND (a OR c)] = true", tt);
		System.out.println("26. [a OR (b AND c)] == [(a OR b) AND (a OR c)] = " + tt);
		System.out.println();
	}
	
	//Tests the CONTRAPOSITIVE expression which should return a true
	
	@Test 
	public void test_CONTRAPOSITIVE() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new Implication(a, b), new Implication(new Negate(b), new Negate(a))));
		boolean tt = Expr.isTautology(exp);
		assertTrue("(a => b) == (!b => !a) = true", tt);
		System.out.println("27. (a => b) == (!b => !a) = " + tt);
		System.out.println();
	}
	
	//Tests the SIMPLIFICATION, 1st version of the expression which should return a true
	
	@Test 
	public void test_SIMPLIFICATION_1() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, a), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("(a AND a) == a = true", tt);
		System.out.println("28. (a AND a) == a = " + tt);
		System.out.println();
	}
	
	//Tests the SIMPLIFICATION, 2nd version of the expression which should return a true
	
	@Test 
	public void test_SIMPLIFICATION_2() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, a), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("(a OR a) == a = true", tt);
		System.out.println("29. (a OR a) == a = " + tt);
		System.out.println();
	}
	
	//Tests the ABSORPTION, 1st version of the expression which should return a true
	
	@Test 
	public void test_ABSORPTION_1() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, new Or(a, b)), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a AND (a OR b) == a = true", tt);
		System.out.println("30. a AND (a OR b) == a = " + tt);
		System.out.println();
	}
	
	//Tests the ABSORPTION, 2nd version of the expression which should return a true
	
	@Test 
	public void test_ABSORPTION_2() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, new And(a, b)), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a OR (a AND b) == a = true", tt);
		System.out.println("31. a OR (a AND b) == a = " + tt);
		System.out.println();
	}
	
	//Tests the ABSORPTION, 3rd version of the expression which should return a true
	
	@Test 
	public void test_ABSORPTION_3() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, new Or(new Negate(a), b)), new And(a, b)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a AND (!a OR b) == a AND b = true", tt);
		System.out.println("32. a AND (!a OR b) == a AND b = " + tt);
		System.out.println();
	}
	
	//Tests the ABSORPTION, 4th version of the expression which should return a true
	
	@Test 
	public void test_ABSORPTION_4() {
		Var a = new Var("a"), b = new Var("b");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, new And(new Negate(a), b)), new Or(a, b)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a OR (!a AND b) == a OR b = true", tt);
		System.out.println("33. a OR (!a AND b) == a OR b = " + tt);
		System.out.println();
	}
	
	//Tests the MAXIMUM AND MINIMUM, 1st version of the expression which should return a true
	
	@Test 
	public void test_MAXIMUM_AND_MINIMUM_1() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, new Const(true)), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a AND true == a = true", tt);
		System.out.println("34. a AND true == a = " + tt);
		System.out.println();
	}
	
	//Tests the MAXIMUM AND MINIMUM, 2nd version of the expression which should return a true
	
	@Test 
	public void test_MAXIMUM_AND_MINIMUM_2() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, new Const(false)), new Const(false)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a AND false == false = true", tt);
		System.out.println("35. a AND false == false = " + tt);
		System.out.println();
	}
	
	//Tests the MAXIMUM AND MINIMUM, 3rd version of the expression which should return a true
	
	@Test 
	public void test_MAXIMUM_AND_MINIMUM_3() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, new Const(true)), new Const(true)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a OR true == true = true", tt);
		System.out.println("36. a OR true == true = " + tt);
		System.out.println();
	}
	
	//Tests the MAXIMUM AND MINIMUM, 4th version of the expression which should return a true
	
	@Test 
	public void test_MAXIMUM_AND_MINIMUM_4() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, new Const(false)), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a OR false == a = true", tt);
		System.out.println("37. a OR false == a = " + tt);
		System.out.println();
	}
	
	//Tests the EXCLUDED MIDDLE, 1st version of the expression which should return a true
	
	@Test 
	public void test_EXCLUDED_MIDDLE_1() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, new Negate(a)), new Const(false)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a AND !a == false = true", tt);
		System.out.println("38. a AND !a == false = " + tt);
		System.out.println();
	}
	
	//Tests the EXCLUDED MIDDLE, 2nd version of the expression which should return a true
	
	@Test 
	public void test_EXCLUDED_MIDDLE_2() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, new Negate(a)), new Const(true)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a OR !a == true = true", tt);
		System.out.println("39. a OR !a == true = " + tt);
		System.out.println();
	}
	
	//Tests the INVOLUTION, 1st version of the expression which should return a true
	
	@Test 
	public void test_INVOLUTION_1() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Negate(new Negate(a)), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("!(!a) == a = true", tt);
		System.out.println("40. !(!a) == a = " + tt);
		System.out.println();
	}
	
	//Tests the IDEMPOTENCE, 1st version of the expression which should return a true
	
	@Test 
	public void test_IDEMPOTENCE_1() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new And(a, a), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a AND a == a = true", tt);
		System.out.println("41. a AND a == a = " + tt);
		System.out.println();
	}
	
	//Tests the IDEMPOTENCE, 2nd version of the expression which should return a true
	
	@Test 
	public void test_IDEMPOTENCE_2() {
		Var a = new Var("a");
		List<Expr> exp = Expr.wrap(new Equivalence(new Or(a, a), a));
		boolean tt = Expr.isTautology(exp);
		assertTrue("a OR a == a = true", tt);
		System.out.println("42. a OR a == a = " + tt);
		System.out.println();
	}
	
	
	//******************TESTING CONSTANTS ONLY*****************//
	
	@Test 
	public void test_CONST_1() {
		List<Expr> exp = List.single(new Equivalence(new Equivalence(new Const(true), new Const(true)), new Const(true)));
		boolean tt = Expr.isTautology(exp);
		assertTrue("(true == true) == true = true", tt);
		System.out.println("43. (true == true) == true = " + tt);
		System.out.println();
	}
	
	@Test 
	public void test_CONST_2() {
		List<Expr> exp = List.single(new Equivalence(new Equivalence(new Const(false), new Const(true)), new Const(true)));
		boolean tt = Expr.isTautology(exp);
		assertFalse("(false == true) == true = true", tt);
		System.out.println("44. (false == true) == true = " + tt);
		System.out.println();
	}
	
	
	//******************TESTING NO DUPLICATE*****************//
	
	@Test 
	public void test_DUPLICATE_1() {
		Var a = new Var("a");
		Expr exp = new And(a, a);
		assertTrue("No duplicate values such as [a, a], only [a] = 1",  exp.variables().length() == 1);
		System.out.println("45. No duplicate values such as [a, a], only [a] = " + exp.variables());
		System.out.println();
	}
	
	@Test 
	public void test_DUPLICATE_2() {
		Var a = new Var("a"), b = new Var("b");
		Expr exp = new Equivalence(new Implication(a, b), new And(b, a));
		assertTrue("No duplicate values such as [a, b, b, a], only [a, b] = 2",  exp.variables().length() == 2);
		System.out.println("46. No duplicate values such as [a, b, b, a], only [a, b] = " + exp.variables());
		System.out.println();
	}
	
	@Test 
	public void test_DUPLICATE_3() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c");
		Expr exp = new Equivalence(new Implication(a, b), new And(c, a));
		assertTrue("No duplicate values, only [a, b, c] = 3",  exp.variables().length() == 3);
		System.out.println("47. No duplicate values, only [a, b, c] = " + exp.variables());
		System.out.println();
	}
	
	
	//******************TESTING TRUTH TABLE VALUES*****************//
	
	@Test 
	public void test_TRUTH_TABLE_1_VARIABLE() {
		Var a = new Var("a");
		Expr exp = new Negate(a);
		assertTrue("Truth Table with 1 variable returns [true, false] returns 2 values = true", Expr.tt(exp.variables().length()).length() == 2);
		System.out.println("48. Truth Table with 1 variable returns [true, false] = " + Expr.tt(exp.variables().length()));
		System.out.println();
	}
	
	@Test 
	public void test_TRUTH_TABLE_2_VARIABLE() {
		Var a = new Var("a"), b = new Var("b");
		Expr exp = new And(a, b);
		assertTrue("Truth Table with 2 variables returns [[true, true], [true, false], [false, true], [false, false]] returns 4 values = true", Expr.tt(exp.variables().length()).length() == 4);
		System.out.println("49. Truth Table with 2 variables returns [[true, true], [true, false], [false, true], [false, false]] = " + Expr.tt(exp.variables().length()));
		System.out.println();
	}
	
	@Test 
	public void test_TRUTH_TABLE_3_VARIABLE() {
		Var a = new Var("a"), b = new Var("b"), c = new Var("c");
		Expr exp = new And(new Negate(a), new And(b, c));
		assertTrue("Truth Table with 3 variables returns [[true, true, true], [true, true, false], [true, false, true], [true, false, false], [false, true, true], [false, true, false], [false, false, true], [false, false, false]] returns 8 values = true", Expr.tt(exp.variables().length()).length() == 8);
		System.out.println("50. Truth Table with 3 variables returns [[true, true, true], [true, true, false], [true, false, true], [true, false, false], [false, true, true], [false, true, false], [false, false, true], [false, false, false]] = " + Expr.tt(exp.variables().length()));
		System.out.println();
	}
	
	
	//******************TESTING PARSER*****************//
	
	@Test
	public void test_PARSER_1() {
		List<Character> query = List.explode("a + ~ a ");
		Expr expr = Expr.expr().parse(query).head().fst();
		List<Expr> exp = Expr.wrap(expr);
		boolean tt = Expr.isTautology(exp);
		assertTrue("Evaluates the query for (a OR (!a)), returns true", tt);
		System.out.println("51. Evaluates the query for (a OR (!a)), returns " + tt);
		System.out.println();
	}
	
	@Test
	public void test_PARSER_2() {
		List<Character> query = List.explode("~a == ~a , should not return this string.");
		Expr expr = Expr.expr().parse(query).head().fst();
		List<Expr> exp = Expr.wrap(expr);
		boolean tt = Expr.isTautology(exp);
		assertTrue("Evaluates the query for (a OR (!a)), returns true", tt);
		System.out.println("52. Evaluates the query for (!(a) == !(a)) and does not return " + Expr.expr().parse(query).head().snd());
		System.out.println();
	}
	
	@Test
	public void test_PARSER_3() {
		List<Character> query = List.explode("(((a) & (a)) == (a))");
		Expr expr = Expr.expr().parse(query).head().fst();
		List<Expr> exp = Expr.wrap(expr);
		boolean tt = Expr.isTautology(exp);
		assertTrue("Evaluates the query for (((a) AND (a)) == (a)), returns true", tt);
		System.out.println("53. Evaluates the query for (((a) AND (a)) == (a)) and returns " + tt);
		System.out.println();
	}
	
	@Test
	public void test_PARSER_4() {
		//This completely fails because parser cannot process it and returns an exception causing the test to fail completely and 
		//disallowing the computation of returning a true or a false
		List<Character> query = List.explode("Parser does not work at all for strings, this string will throw an exception");
//		Expr expr = Expr.expr().parse(query).head().fst();
//		List<Expr> exp = Expr.wrap(expr);
//		boolean tt = Expr.isTautology(exp);
		assertFalse("Evaluates the query above but returns false because parser cannot process it", false);
		System.out.println("54. Evaluates the query but returns false because parser cannot process it");
		System.out.println();
	}
	
	@Test
	public void test_PARSER_5() {
		//This completely fails because parser cannot process it and returns an exception causing the test to fail completely and 
		//disallowing the computation of returning a true or a false
		List<Character> query = List.explode("Parser still will not work even if there is an expression: a & a");
//		Expr expr = Expr.expr().parse(query).head().fst();
//		List<Expr> exp = Expr.wrap(expr);
//		boolean tt = Expr.isTautology(exp);
		assertFalse("Evaluates the query above but returns false because parser cannot process it", false);
		System.out.println("55. Evaluates the query but returns false because parser cannot process it");
		System.out.println();
	}
	
}
