package net.mips.compiler;

public class ErreurSyntaxique extends ErreurCompilation{
	public ErreurSyntaxique(CodesErr code) {
		super(code.getMessage());
	}
	

}
