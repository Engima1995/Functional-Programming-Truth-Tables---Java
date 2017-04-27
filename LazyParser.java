package p14184295;

import java.util.function.Supplier;

import p14184295.List;

public class LazyParser<A> extends Parser<A> {

	private final Supplier<Parser<A>> lp;

	public LazyParser(Supplier<Parser<A>> lp) {
		this.lp = lp;
	}

	@Override
	public List<Pair<A, List<Character>>> parse(List<Character> inp) {
		return lp.get().parse(inp);
	}
}