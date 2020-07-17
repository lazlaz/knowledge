package com.laz.knowledge.twentyfive;

public class Producer implements Runnable {
 
    private Resource resource;
 
    public Producer(Resource resource){
        this.resource=resource;
    }
 
    @Override
    public void run() {
        while (true){
            resource.put();
        }
    }

}
