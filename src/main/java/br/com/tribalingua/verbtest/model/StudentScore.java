package br.com.tribalingua.verbtest.model;

public class StudentScore {
	
	private String name;
	private String thumbUrl;
	private Integer score;
	private Integer verbTestId;
	
	public StudentScore(String name, String thumbUrl, Integer score, Integer verbTestId) {
		this.name = name;
		this.thumbUrl = thumbUrl;
		this.score = score;
		this.verbTestId = verbTestId;
	}

	public String getName() {
		return name;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score){
		this.score = score;
	}
	
	public Integer getVerbTestId(){
		return this.verbTestId;
	}
	
}
