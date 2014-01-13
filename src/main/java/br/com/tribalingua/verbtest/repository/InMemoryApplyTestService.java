package br.com.tribalingua.verbtest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.tribalingua.verbtest.model.FinalTraining;
import br.com.tribalingua.verbtest.model.VerbContainer;
import br.com.tribalingua.verbtest.model.VerbContainerAnswer;
import br.com.tribalingua.verbtest.model.VerbTest;

public class InMemoryApplyTestService implements IApplyTestService {
	
	public static String KEY = "ApplyService";
	
	private List<VerbTest> tests;
	
	private Map<String, VerbContainerAnswer> currentTestAnswers;
	
	private int count = 0;
	
	private int currentTest = -1;
	
	private FinalTraining finalTraining;
	
	public InMemoryApplyTestService(){
		
		tests = new ArrayList<VerbTest>();
		VerbTest test1 = new VerbTest(1);
		List<VerbContainer> verbs1 = new ArrayList<VerbContainer>();
		
		VerbContainer container = new VerbContainer("Broadcast", "Broadcast", "Broadcast");
		verbs1.add(container);
		
		container = new VerbContainer("See", "Saw", "Seen");
		verbs1.add(container);
		
		container = new VerbContainer("Cut", "Cut", "Cut");
		verbs1.add(container);
		
		container = new VerbContainer("Be", "Was/Were", "Been");
		verbs1.add(container);
		
		container = new VerbContainer("Become", "Became", "Become");
		verbs1.add(container);
		
		container = new VerbContainer("Come", "Came", "Come");
		verbs1.add(container);
		
		container = new VerbContainer("Bend", "Bent", "Bent");
		verbs1.add(container);
		
		container = new VerbContainer("Seek", "Sought", "Sought");
		verbs1.add(container);
		
		container = new VerbContainer("Send", "Sent", "Sent");
		verbs1.add(container);
		
		container = new VerbContainer("Spit", "Spat", "Spat");
		verbs1.add(container);
		
		VerbContainer dummy = new VerbContainer("Dummy", "", "");
		verbs1.add(dummy);
		
		test1.addVerbs(verbs1);
		
		VerbTest test2 = new VerbTest(2);
		List<VerbContainer> verbs2 = new ArrayList<VerbContainer>();
		
		container = new VerbContainer("Seek", "Sought", "Sought");
		verbs2.add(container);
		
		container = new VerbContainer("Send", "Sent", "Sent");
		verbs2.add(container);
		
		container = new VerbContainer("Spit", "Spat", "Spat");
		verbs2.add(container);
		
		container = new VerbContainer("Broadcast", "Broadcast", "Broadcast");
		verbs2.add(container);
		
		container = new VerbContainer("See", "Saw", "Seen");
		verbs2.add(container);
		
		container = new VerbContainer("Cut", "Cut", "Cut");
		verbs2.add(container);
		
		container = new VerbContainer("Be", "Was/Were", "Been");
		verbs2.add(container);
		
		container = new VerbContainer("Become", "Became", "Become");
		verbs2.add(container);
		
		container = new VerbContainer("Come", "Came", "Come");
		verbs2.add(container);
		
		container = new VerbContainer("Bend", "Bent", "Bent");
		verbs2.add(container);
		
		verbs2.add(dummy);
		
		test1.addVerbs(verbs1);
		test2.addVerbs(verbs2);
		
		tests.add(test1);
		tests.add(test2);
	}

	public VerbTest randomVerbTest() {
		if(count == 0){
			count++;
			currentTest = 0;
			return tests.get(0);
		}else{
			count--;
			currentTest = 1;
			return tests.get(1);
		}
	}

	public void addAnswerToCurrentTest(int position, String infinitive, String pastSimple, String pastParticiple) {
		VerbContainerAnswer verbContainer = null;
		if(currentTestAnswers.containsKey(infinitive)){
			verbContainer = currentTestAnswers.get(infinitive);
			verbContainer = null;
		}

		verbContainer = new VerbContainerAnswer(infinitive, pastSimple, pastParticiple);
		
		verbContainer.checkAnswer(tests.get(currentTest).getVerb(position));
		
		currentTestAnswers.put(infinitive, verbContainer);
	}

	public void startNewTrainingTest() {
		if(this.currentTestAnswers != null)
			this.currentTestAnswers.clear();
		this.currentTestAnswers = new HashMap<String, VerbContainerAnswer>();
	}

	public void finishTest(){
		finalTraining = null;
		finalTraining = new FinalTraining();
		for (VerbContainerAnswer answer : currentTestAnswers.values()) {
			if(answer.isWrong()){
				finalTraining.addWrongAnswer(answer);
			}
		}
	}
	
	public FinalTraining getFinalTrainingResult(){
		return finalTraining;
	}

}
