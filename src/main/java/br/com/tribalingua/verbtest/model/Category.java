package br.com.tribalingua.verbtest.model;

public class Category {
	
	private int id;
	private String name;
	private String thumbUrl;
	private String description;
	
	public Category(int id, String name, String thumbUrl, String description) {
		this.id = id;
		this.name = name;
		this.thumbUrl = thumbUrl;
		this.description = description;
	}
	
	public int getId(){
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public String getDescription() {
		return description;
	}

}
