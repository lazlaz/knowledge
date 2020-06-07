package com.laz.knowledge.twentyfive;

public class Consumer2 implements Runnable {
 
    private Resource2 resource;
 
    public Consumer2(Resource2 resource) {
        this.resource = resource;
    }
 
    @Override
    public void run() {
        while (true){
            resource.remove();
        }
 
    }
}
