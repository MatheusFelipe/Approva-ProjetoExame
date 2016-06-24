package approva;

import java.util.ArrayList;

public class Exam implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList <Question> exam =  new ArrayList <Question> ();
	String Description;
	String Title;
	int rightAnswer = 0;
	
	void addQuestion (Question aux){
		exam.add(aux);
	} 
	
	void setDescription (String txt){
		Description = txt;
	}
	
	String getDescription (){
		return Description;
	}
	
	void setTitle (String txt){
		Title = txt;
	}
	
	String getTitle(){
		return Title;
	}
	void plusQuestionCorrected(){
		rightAnswer++;
	}
	

}
