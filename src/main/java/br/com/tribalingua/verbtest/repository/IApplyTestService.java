package br.com.tribalingua.verbtest.repository;

import br.com.tribalingua.verbtest.model.FinalTraining;
import br.com.tribalingua.verbtest.model.VerbTest;

public interface IApplyTestService {
	
	VerbTest randomVerbTest();
	
	void addAnswerToCurrentTest(int position, String infinitive, String pastSimple, String pastParticiple);
	
	void startNewTrainingTest();
	
	FinalTraining getFinalTrainingResult();
	
	public void finishTest();
	
}
