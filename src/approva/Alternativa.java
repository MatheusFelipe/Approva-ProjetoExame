package approva;

public class Alternativa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	String texto;
	boolean ehCorreta;
	
	Alternativa (String txt, boolean aux){
		texto = txt;
		ehCorreta = aux;
	}
	void setAnswer(boolean aux){
		ehCorreta = aux;
	}		
}