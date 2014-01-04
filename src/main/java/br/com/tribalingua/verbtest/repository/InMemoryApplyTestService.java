package br.com.tribalingua.verbtest.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.tribalingua.verbtest.model.VerbContainer;
import br.com.tribalingua.verbtest.model.VerbTest;

public class InMemoryApplyTestService implements IApplyTestService {
	
	public static String KEY = "ApplyService";
	
	List<VerbTest> tests;
	
	private int count = 0;
	
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
		
		VerbContainer dummy = new VerbContainer("Dummy", "", "");
		verbs1.add(dummy);
		
		test1.addVerbs(verbs1);
		
		VerbTest test2 = new VerbTest(2);
		List<VerbContainer> verbs2 = new ArrayList<VerbContainer>();
		
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
		
		container = new VerbContainer("Seek", "Sought", "Sought");
		verbs2.add(container);
		
		container = new VerbContainer("Send", "Sent", "Sent");
		verbs2.add(container);
		
		test1.addVerbs(verbs1);
		test2.addVerbs(verbs2);
		
		tests.add(test1);
		tests.add(test2);
	}

	public VerbTest randomVerbTest() {
		if(count == 0){
			count++;
			return tests.get(0);
		}else{
			count--;
			return tests.get(1);
		}
	}

}
