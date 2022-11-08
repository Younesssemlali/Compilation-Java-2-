package net.mips.compiler;

public class ErreurLexicale extends ErreurCompilation {
	public ErreurLexicale(CodesErr code) {
		super(code.getMessage());
	}
}
