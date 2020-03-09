package com.laz.knowledge.three;

import com.laz.knowledge.tow.ProductOwner;

public class Main {
	public static void main(String[] args) {
		ProductOwner ross = new ProductOwner("Ross");

		ProductOwner rossProxy = (ProductOwner) new EnginnerCGLibProxy().bind(ross);

		rossProxy.defineBackLog();
	}
}
