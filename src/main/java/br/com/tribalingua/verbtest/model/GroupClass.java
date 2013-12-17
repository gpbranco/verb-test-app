package br.com.tribalingua.verbtest.model;

public class GroupClass {
	
	private Integer id;
	private String name;
	private String thumbUrl;
	
	public GroupClass(Integer id, String name, String thumbUrl) {
		this.id = id;
		this.name = name;
		this.thumbUrl = thumbUrl;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getThumbUrl(){
		return this.thumbUrl;
	}
	

}
