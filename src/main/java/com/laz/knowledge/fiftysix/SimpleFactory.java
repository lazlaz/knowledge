package com.laz.knowledge.fiftysix;

//简单工厂
public class SimpleFactory {

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
	public BMW createBMW(int type) {
		switch (type) {
		
		case 320:
			return new BMW320();
 
		case 523:
			return new BMW523();
 
		default:
			break;
		}
		return null;
	}
	public class Factory {
		public BMW createBMW(int type) {
			switch (type) {
			
			case 320:
				return new BMW320();
	 
			case 523:
				return new BMW523();
	 
			default:
				break;
			}
			return null;
		}
	}
	public static void main(String[] args) {
		Factory factory = new SimpleFactory().new Factory();
		BMW bmw320 = factory.createBMW(320);
		BMW bmw523 = factory.createBMW(523);
	}

}
