package p14184295;

import static p14184295.List.concat;
import static p14184295.List.cons;
import static p14184295.List.emptyList;
import static p14184295.List.implode;
import static p14184295.List.single;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import p14184295.List;
import p14184295.Pair;

public class Parser<A> {

	private final Function<List<Character>, List<Pair<A, List<Character>>>> p;

	public Parser(){p=null;}

	public Parser(Function<List<Character>, List<Pair<A, List<Character>>>> p) {
		this.p = p;
	}

	public List<Pair<A, List<Character>>> parse(List<Character> inp) {
		return p.apply(inp);
	}

	public static <A> Parser<A> zero() {
		return new Parser<A>
			(inp -> emptyList());
	}

	public static <A> Parser<A> returning(A v) {
		return new Parser<A>
			(inp  -> single(new Pair<>(v, inp)));
	}

	public static Parser<Character> item() {
		return new Parser<Character>
			(inp -> (inp.isEmpty()) ? emptyList() : single(new Pair<>(inp.head(), inp.tail())));
	}

	public <B> Parser<B> bind(Function<A, Parser<B>> f) {
		Function<List<Character>, List<Pair<B, List<Character>>>> b =
				inp -> concat(this.parse(inp).map(r1 -> f.apply(r1.fst()).parse(r1.snd())));
		return new Parser<B>(b);
	}

	public <B> Parser<B> seq(Parser<B> that) {
		return this.bind(ignored -> that);
	}

	public Parser<A> plus(Parser<A> that) {
		return new Parser<A>(inp -> this.parse(inp).append(that.parse(inp)));
	}

	public static Parser<Character> satisfy(Predicate<Character> p) {
		return item().bind(x -> p.test(x) ? returning(x) : zero());
	}


	public static Parser<Character> character(Character x) {
		return satisfy(y -> x == y);
	}

	public static Parser<Character> digit() {
		return satisfy(Character::isDigit);
	}

	public static Parser<Character> lower() {
		return satisfy(Character::isLowerCase);
	}

	public static Parser<Character> upper() {
		return satisfy(Character::isUpperCase);
	}

	public static Parser<Character> whitespace() {
		return satisfy(Character::isWhitespace);
	}

	public static Parser<Character> letter() {
		return lower().plus(upper());
	}

	public static Parser<Character> alphanum() {
		return letter().plus(digit());
	}

	public static Parser<String> word() {
		return letter().bind(x -> word().bind(xs -> returning(x + xs))).plus(returning(""));
	}

	public static Parser<String> string(String s) {
		if (s.isEmpty())
			return returning("");
		else
			return character(s.charAt(0)).bind(x -> string(s.substring(1)).bind(xs -> returning(x + xs)));
	}

	public static <A> Parser<List<A>> many(Parser<A> p) {
		return p.bind(x -> many(p).bind(xs -> returning(cons(x, xs)))).plus(returning(emptyList()));
	}

	public static Parser<String> identifier() {
		return lower().bind(x -> many(alphanum()).bind(xs -> returning(x + implode(xs))));
	}

	public static <A> Parser<List<A>> many1(Parser<A> p) {
		return p.bind(x -> many(p).bind(xs -> returning(cons(x, xs))));
	}

	public static Parser<Integer> natural() {
		return many1(digit()).bind(xs -> returning(xs.map(d -> (int) (d - '0')).foldl1((m, n) -> m * 10 + n)));
	}

	public static Parser<Integer> integer() {
		return character('-').bind(x -> natural().bind(n -> returning(-n))).plus(natural());
	}

	public <B> Parser<List<A>> sepby1(Parser<B> sep) {
		return this.bind(x -> many(sep.seq(this)).bind(xs -> returning(cons(x, xs))));
	}

	public <B, C> Parser<A> bracket(Parser<B> left, Parser<C> right) {
		return left.seq(this.bind(x -> right.seq(returning(x))));
	}

	public <B> Parser<List<A>> sepby(Parser<B> sep) {
		return this.sepby1(sep).plus(returning(emptyList()));
	}

	public static <A> Parser<A> oneof(Parser<A> p, Parser<A> q) {
		return p.plus(q);
	}

	public static <A> Parser<A> oneof(List<Parser<A>> ps) {
		return ps.foldr1(Parser::oneof);
	}

	public static <A> Parser<A> chomp(Parser<A> p) {
		return many(whitespace()).bind(sp -> p.bind(x -> returning(x)));
	}

	/* would like to make rest a private method as its purpose is only to be a
	 * helper for chainl1.  But interfaces do not allow the private modifier.
	 */
	static <A> Parser<A> rest(Parser<A> p, Parser<BinaryOperator<A>> op, A x) {
		return op.bind(f -> p.bind(y -> rest(p, op, f.apply(x, y)))).plus(returning(x));
	}
	public Parser<A> chainl1(Parser<BinaryOperator<A>> op) {
		return this.bind(x -> rest(this,op,x));
	}


}
