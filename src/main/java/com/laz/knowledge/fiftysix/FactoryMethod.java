package com.laz.knowledge.fiftysix;

//工厂方法
public class FactoryMethod {
	abstract class BMW {
		public BMW(){
			
		}
	}
	public class BMW320 extends BMW {
		public BMW320() {
			System.out.println("制造-->BMW320");
		}
	}
	public class BMW523 extends BMW{
		public BMW523(){
			System.out.println("制造-->BMW523");
		}
	}
	interface FactoryBMW {
		BMW createBMW();
	}
	 
	public class FactoryBMW320 implements FactoryBMW{
	 
		@Override
		public BMW320 createBMW() {
	 
			return new BMW320();
		}
	 
	}
	public class FactoryBMW523 implements FactoryBMW {
		@Override
		public BMW523 createBMW() {
	 
			return new BMW523();
		}
	}
	public static void main(String[] args) {
		FactoryMethod f = new FactoryMethod();
		FactoryBMW320 factoryBMW320 = f.new FactoryBMW320();
		BMW320 bmw320 = factoryBMW320.createBMW();
 
		FactoryBMW523 factoryBMW523 = f.new FactoryBMW523();
		BMW523 bmw523 = factoryBMW523.createBMW();
	}

}
