package com.laz.knowledge.two;

public class ProductOwner {
	private String name;
	
	public ProductOwner() {
		
	}
	public ProductOwner(String name){
		this.name = name;
	}
	public void defineBackLog(){
		System.out.println("PO: " + name + " defines Backlog.");
	}
}
