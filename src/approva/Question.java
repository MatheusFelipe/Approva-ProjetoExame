package approva;
import java.util.ArrayList;

public class Question implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2340333400085190951L;
	String comandoQuestao;
	ArrayList <Alternativa> alternativas = new ArrayList <Alternativa> ();
	private String categoria; 
	String identificador;
	int userAnswer = -100;
	
	Question (String txt, String cat, String ident){
		comandoQuestao = txt;
		identificador = ident;
		categoria = cat; 
		
	}
	void setAlternativas (ArrayList <Alternativa> aux){
		alternativas = aux;
	}
	String getAlternativa(int aux){
		return alternativas.get(aux).texto;
	}
	
	void AddAlternativa (String aux, boolean booleana){
		Alternativa novaAlternativa = new Alternativa(aux, booleana);
		alternativas.add(novaAlternativa);
	}
	
	void setGroup(String aux){
		categoria = aux;
	}	
	
	String getGroup (){
		return categoria;
	}
	
	void setIdentificador(String aux){
		identificador = aux;
	}	
	
	String getIdentificador (){
		return identificador;
	}
	void setComando(String aux){
		comandoQuestao = aux;
	}	
	int getUserAnswer(){
		return userAnswer;
	}
	String getComando (){
		return comandoQuestao;
	}
	void setUserAnswer(int aux){
		userAnswer = aux;
	}
	boolean userAnswerIsCorrect( ){
		return alternativas.get(userAnswer).ehCorreta;
	}
}
