package net.mips.compiler;

import java.io.IOException;

public class Parser {
	private Scanner scanner;

	
	
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}


public Parser (String file) throws IOException , ErreurCompilation{
	this.scanner = new Scanner(file);
	
}


public void testAccept (Tokens t , CodesErr c) throws IOException , ErreurCompilation{
	if(this.scanner.getSymbCour().getToken()== t) {
		this.scanner.symbSuiv();
		System.out.println(scanner.getSymbCour().getToken());
	}
	else {
		throw new ErreurSyntaxique(c);
	}
}



public void consts()throws IOException, ErreurCompilation {
	testAccept (Tokens.CONST_TOKEN, CodesErr.CONST_ERR);
	testAccept (Tokens.ID_TOKEN, CodesErr.ID_ERR);
	testAccept (Tokens.EG_TOKEN, CodesErr.EG_ERR);
	testAccept (Tokens.NUM_TOKEN, CodesErr.NUM_ERR);
	testAccept (Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);
	while(scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
		this.scanner.symbSuiv();
		testAccept (Tokens.EG_TOKEN, CodesErr.EG_ERR);
		testAccept (Tokens.NUM_TOKEN, CodesErr.NUM_ERR);
		testAccept (Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);	
	}
}

public void Program()throws IOException , ErreurCompilation{
	testAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
	testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
	Block();
	testAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
	
}

private void Block()throws IOException,ErreurCompilation {
		if(scanner.getSymbCour().getToken()==Tokens.CONST_TOKEN) {
			consts();
		}			
		if(scanner.getSymbCour().getToken()==Tokens.VAR_TOKEN) {
			vars();
		}
	    insts();
	
}
private void insts() throws IOException,ErreurCompilation{
	testAccept(Tokens.BEGIN_TOKEN,CodesErr.BEGIN_ERR);
	INST();
	while(scanner.getSymbCour().getToken()==Tokens.PVIR_TOKEN) {
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		INST();
	}
	testAccept(Tokens.END_TOKEN,CodesErr.END_ERR);
}
public void LIRE() throws IOException, ErreurCompilation {
	testAccept(Tokens.READ_TOKEN,CodesErr.READ_ERR);
	testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
	testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
		testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
	}
	testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
	
}

private void INST() throws IOException,ErreurCompilation{
	
	if(scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
		AFFEC();
	}
	else if(scanner.getSymbCour().getToken()==Tokens.IF_TOKEN) {
		SI();
	}
	else if(scanner.getSymbCour().getToken()==Tokens.WHILE_TOKEN) {
		TANQUE();
	}
	else if(scanner.getSymbCour().getToken()==Tokens.WRITE_TOKEN) {
		ECRIRE();
	}
	else if(scanner.getSymbCour().getToken()==Tokens.READ_TOKEN) {
		LIRE();
	}
	else if(scanner.getSymbCour().getToken()==Tokens.BEGIN_TOKEN) {
		insts();
	}
	
	
}
public void SI() throws IOException, ErreurCompilation {
		testAccept(Tokens.IF_TOKEN,CodesErr.IF_ERR);
		COND();
		testAccept(Tokens.THEN_TOKEN,CodesErr.THEN_ERR);
		INST();
	}



	public void AFFEC() throws IOException, ErreurCompilation {
		testAccept(Tokens.ID_TOKEN, CodesErr.ID_ERR);
		testAccept(Tokens.AFFEC_TOKEN, CodesErr.AFFEC_ERR);
		EXPR();
	}
	
	public void EXPR() throws IOException, ErreurCompilation {
		TERM();
		while(scanner.getSymbCour().getToken()==Tokens.PLUS_TOKEN || scanner.getSymbCour().getToken()==Tokens.MOINS_TOKEN) {
			ADDOP();
			TERM();	
		}
	}
	public void RELOP() throws IOException, ErreurCompilation {	
		switch(scanner.getSymbCour().getToken()) {
		case EG_TOKEN : testAccept(Tokens.EG_TOKEN, CodesErr.EG_ERR);break;
		case DIFF_TOKEN : testAccept(Tokens.DIFF_TOKEN,CodesErr.DIFF_ERR);break;
		case INF_TOKEN : testAccept(Tokens.INF_TOKEN, CodesErr.INF_ERR);break;
		case SUP_TOKEN : testAccept(Tokens.SUP_TOKEN,CodesErr.SUP_ERR);break;
		case INFEG_TOKEN :testAccept(Tokens.INFEG_TOKEN,CodesErr.INFEG_ERR);break;
		case SUPEG_TOKEN : testAccept(Tokens.SUPEG_TOKEN,CodesErr.SUPEG_ERR);break;
		default:
			throw new ErreurCompilation("ERREUR");
		}
		}
	public void FACT() throws IOException, ErreurCompilation {
		if(scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		}
		else if(scanner.getSymbCour().getToken()==Tokens.NUM_TOKEN) {
			testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
		}
		else if(scanner.getSymbCour().getToken()==Tokens.PARG_TOKEN) {
			testAccept(Tokens.PARG_TOKEN, CodesErr.PARG_ERR);
			EXPR();
			testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
		}
	}
	public void TERM() throws IOException, ErreurCompilation {
		FACT();
		while(scanner.getSymbCour().getToken()==Tokens.MUL_TOKEN || scanner.getSymbCour().getToken()==Tokens.DIV_TOKEN) {
			MULOP();
			FACT();
		}
	}
	public void MULOP() throws IOException, ErreurCompilation{
		if(scanner.getSymbCour().getToken()==Tokens.MUL_TOKEN) {
		testAccept(Tokens.MUL_TOKEN,CodesErr.MUL_ERR);
		}else if(scanner.getSymbCour().getToken()==Tokens.DIV_TOKEN) {
			testAccept(Tokens.DIV_TOKEN,CodesErr.DIV_ERR);
		}
	}
	public void ADDOP() throws IOException, ErreurCompilation {
		if(scanner.getSymbCour().getToken()==Tokens.PLUS_TOKEN) {
			testAccept(Tokens.PLUS_TOKEN,CodesErr.PLUS_ERR);
			}else if(scanner.getSymbCour().getToken()==Tokens.MOINS_TOKEN) {
				testAccept(Tokens.MOINS_TOKEN,CodesErr.MOINS_ERR);
			}
	}
	public void COND() throws IOException, ErreurCompilation {
		EXPR();
		RELOP();
		EXPR();
		
	}
	public void TANQUE() throws IOException, ErreurCompilation {
		testAccept(Tokens.WHILE_TOKEN,CodesErr.WHILE_ERR);
		COND();
		testAccept(Tokens.DO_TOKEN,CodesErr.DO_ERR);
		INST();
	}
	public void ECRIRE() throws IOException, ErreurCompilation {
		testAccept(Tokens.WRITE_TOKEN,CodesErr.WRITE_ERR);
		testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
		EXPR();
		while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			EXPR();
		}
		testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR );
	}

private void vars()throws IOException,ErreurCompilation{
	testAccept (Tokens.VAR_TOKEN, CodesErr.VAR_ERR);
	testAccept (Tokens.ID_TOKEN, CodesErr.ID_ERR);
	while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
		this.scanner.symbSuiv();
		testAccept (Tokens.ID_TOKEN, CodesErr.ID_ERR);
	}
	testAccept (Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);
	
}


public static void main(String[] args) throws IOException, ErreurCompilation {
	Parser p =new Parser("C:\\\\Users\\\\a\\\\Desktop\\\\javatp\\\\said.txt");
	p.getScanner().initMotsCles();
	p.getScanner().lireCar();
	p.getScanner().symbSuiv();
	p.Program();
	if(p.scanner.getSymbCour().getToken()==Tokens.EOF_TOKEN) {
		System.out.println("l'analyse syntaxique reussie");
	}else {
		throw new ErreurSyntaxique(CodesErr.EOF_ERR);
	}
}
	
}
	

