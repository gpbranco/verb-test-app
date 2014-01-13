package br.com.tribalingua.verbtest.model;

import java.util.ArrayList;
import java.util.List;

public class FinalTraining {
	
	private List<VerbContainerAnswer> wrongAnswers;
	
	public void addWrongAnswer(VerbContainerAnswer answer){
		if(wrongAnswers == null)
			wrongAnswers = new ArrayList<VerbContainerAnswer>();
		
		wrongAnswers.add(answer);
	}
	
	public int getScore(){
		return this.wrongAnswers != null ? 10 - this.wrongAnswers.size() : 10;
	}
	
	public int getCount(){
		return this.wrongAnswers.size();
	}
	
	public VerbContainerAnswer getWrongAnswer(int index){
		return this.wrongAnswers.get(index);
	}
	
}
