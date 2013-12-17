package br.com.tribalingua.verbtest.model;

public class VerbContainer {

	private String infinite;
	private String pastSimple;
	private String pastParticiple;
	
	public VerbContainer(String infinite, String pastSimple, String pastParticiple) {
		this.infinite = infinite;
		this.pastSimple = pastSimple;
		this.pastParticiple = pastParticiple;
	}

	public String getInfinite() {
		return infinite;
	}

	public String getPastSimple() {
		return pastSimple;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}
}
