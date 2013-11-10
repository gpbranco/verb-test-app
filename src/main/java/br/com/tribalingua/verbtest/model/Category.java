package br.com.tribalingua.verbtest.model;

public class Category {
	
	private String name;
	private String thumbUrl;
	private String description;
	
	public Category(String name, String thumbUrl, String description) {
		this.name = name;
		this.thumbUrl = thumbUrl;
		this.description = description;
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
