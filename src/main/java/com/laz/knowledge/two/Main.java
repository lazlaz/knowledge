package com.laz.knowledge.two;

public class Main {
	public static void main(String[] args) {
//		ProductOwner po = new ProductOwner("Ross");
//
//		ProductOwner poProxy = (ProductOwner) new EnginnerProxy().bind(po);
//
//		poProxy.defineBackLog();
		
		Tester t = new Tester("boss");
		ITester r = (ITester) new EnginnerProxy().bind(t);
		r.doTesting();
		
	}
}
