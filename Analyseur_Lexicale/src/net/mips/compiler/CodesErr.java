package net.mips.compiler;

public enum CodesErr {
    CAR_INC_ERR("Symbole inconnu"),
    PVIR_ERR("Symbole ; attendu"),
    PNT_ERR("Symbol . attendu"),
    EG_ERR("Symbole = attendu"),
    AFFEC_ERR("Symboles := attendu"),
    PARG_ERR("Symbole ( attendu"),
    PARD_ERR("Symbole ) attendu"),
    INF_ERR("Symbole < attendu"),
    SUP_ERR("Symbole > attendu"),
    INFEG_ERR("Symbole <= attendu"),
    SUPEG_ERR("Symbole >= attendu"),
    PLUS_ERR("Symbole + attendu"),
    MOINS_ERR("Symbole - attendu"),
    MUL_ERR("Symbole * attendu"),
    DIV_ERR("Symbole / attendu"),
    VIR_ERR("Symbole , attendu"),
    DIFF_ERR("Symbole != attendu"),    
    
    
    PROGRAM_ERR("Mot cle program attendu!"),
    WHILE_ERR("Mot while attendu"),
    WRITE_ERR("Mot write attendu"),
    READ_ERR("Mot read attendu"),
    IF_ERR("Mot if attendu"),
    DO_ERR("Mot do attendu"),
    THEN_ERR("Mot then attendu"),
    BEGIN_ERR("Mot begin attendu"),
    END_ERR("Mot end attendu"),
    VAR_ERR("Mot var attendu"),
    
    FIC_VID_ERR("Erreur d'ouverture de fichier"),
    ID_ERR("Identificateur attendu !"),
    CONST_ERR("Const attendu"),
    NUM_ERR("Numero attendu"),
    COND_ERR("Condition attendue"),
    EXPR_ERR("Expression attendue"),
	EOF_ERR("Une erreur de syntaxe");
 


    private String message;

    private CodesErr(String message)
    {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
