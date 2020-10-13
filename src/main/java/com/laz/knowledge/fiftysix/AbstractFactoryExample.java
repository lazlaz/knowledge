package com.laz.knowledge.fiftysix;

//抽象工厂
public class AbstractFactoryExample {
	//发动机以及型号  
	public interface Engine {  
	 
	}  
	public class EngineA implements Engine{  
	    public EngineA(){  
	        System.out.println("制造-->EngineA");  
	    }  
	}  
	public class EngineB implements Engine{  
	    public EngineB(){  
	        System.out.println("制造-->EngineB");  
	    }  
	}  
	 
	//空调以及型号  
	public interface Aircondition {  
	 
	}  
	public class AirconditionA implements Aircondition{  
	    public AirconditionA(){  
	        System.out.println("制造-->AirconditionA");  
	    }  
	}  
	public class AirconditionB implements Aircondition{  
	    public AirconditionB(){  
	        System.out.println("制造-->AirconditionB");  
	    }  
	} 
	

	//创建工厂的接口  
	public interface AbstractFactory {  
	    //制造发动机
	    public Engine createEngine();
	    //制造空调 
	    public Aircondition createAircondition(); 
	}  
	 
	 
	//为宝马320系列生产配件  
	public class FactoryBMW320 implements AbstractFactory{  
	      
	    @Override  
	    public Engine createEngine() {    
	        return new EngineA();  
	    }  
	    @Override  
	    public Aircondition createAircondition() {  
	        return new AirconditionA();  
	    }  
	}  
	//宝马523系列
	public class FactoryBMW523 implements AbstractFactory {  
	  
	     @Override  
	    public Engine createEngine() {    
	        return new EngineB();  
	    }  
	    @Override  
	    public Aircondition createAircondition() {  
	        return new AirconditionB();  
	    }  
	 
	 
	} 

	public static void main(String[] args) {
		AbstractFactoryExample f = new AbstractFactoryExample();
		  //生产宝马320系列配件
		AbstractFactory factoryBMW320 = f.new FactoryBMW320();  
        factoryBMW320.createEngine();
        factoryBMW320.createAircondition();
          
        //生产宝马523系列配件  
        AbstractFactory factoryBMW523 = f.new FactoryBMW523();  
        factoryBMW320.createEngine();
        factoryBMW320.createAircondition();

	}
}
