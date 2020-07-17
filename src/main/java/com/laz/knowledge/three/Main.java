package com.laz.knowledge.three;

import com.laz.knowledge.two.ProductOwner;

public class Main {
	public static void main(String[] args) {
		ProductOwner ross = new ProductOwner("Ross");

		ProductOwner rossProxy = (ProductOwner) new EnginnerCGLibProxy().bind(ross);

		rossProxy.defineBackLog();
	}
}
