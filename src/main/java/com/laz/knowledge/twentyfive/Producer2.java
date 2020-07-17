package com.laz.knowledge.twentyfive;

public class Producer2 implements Runnable {
 
    private Resource2 resource;
 
    public Producer2(Resource2 resource){
        this.resource=resource;
    }
 
    @Override
    public void run() {
        while (true){
            resource.put();
        }
    }

}
