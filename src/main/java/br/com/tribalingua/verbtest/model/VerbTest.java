package br.com.tribalingua.verbtest.model;

import java.util.ArrayList;
import java.util.List;

public class VerbTest {

	private List<VerbContainer> verbs = new ArrayList<VerbContainer>();
	private Integer id;
	
	public VerbTest(){
		
	}
	
	public VerbTest(Integer id){
		this.id= id;
	}
	
	public int count(){
		return verbs.size();
	}
	
	public void addVerbs(List<VerbContainer> verbs){
		this.verbs = verbs;
		//Collections.copy(this.verbs, verbs);
	}
	
	public VerbContainer getVerb(int position){
		return verbs.get(position);
	}
	
	public Integer getId(){
		return this.id;
	}
}
