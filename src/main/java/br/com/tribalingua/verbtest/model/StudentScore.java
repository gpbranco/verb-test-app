package br.com.tribalingua.verbtest.model;

public class StudentScore {
	
	private String name;
	private String thumbUrl;
	private Integer score;
	private Integer classId;
	
	public StudentScore(String name, String thumbUrl, Integer score, Integer classId) {
		this.name = name;
		this.thumbUrl = thumbUrl;
		this.score = score;
		this.classId = classId;
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
	
	public Integer getClassId(){
		return this.classId;
	}
	
}
