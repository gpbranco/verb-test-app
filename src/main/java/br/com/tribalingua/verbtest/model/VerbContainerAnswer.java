package br.com.tribalingua.verbtest.model;

public class VerbContainerAnswer extends VerbContainer {

	private boolean wrong[] = {true, true};
	private String corrects[] = new String[2];
	
	public VerbContainerAnswer(String infinite, String pastSimple, String pastParticiple) {
		super(infinite, pastSimple, pastParticiple);
	}
	
	public void checkAnswer(VerbContainer correctAnswer){
		if(this.getPastSimple().equalsIgnoreCase(correctAnswer.getPastSimple())){
			wrong[0] = false;
		}
		if(this.getPastParticiple().equalsIgnoreCase(correctAnswer.getPastParticiple())){
			wrong[1] = false;
		}
		corrects[0] = correctAnswer.getPastSimple();
		corrects[1] = correctAnswer.getPastParticiple();
	}
	
	public boolean isWrong(){
		return this.wrong[0] || this.wrong[1];
	}
	
	public boolean isPastSimpleWrong(){
		return this.wrong[0];
	}
	
	public boolean isPastParticipleWrong(){
		return this.wrong[1];
	}
	
	public String getCorrectPastSimple(){
		return corrects[0];
	}
	
	public String getCorrectPastParticiple(){
		return corrects[1];
	}

}
