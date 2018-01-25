/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.util.Observable;

/**
 *
 * @author virzen
 */
public class EventBus extends Observable {
    private static EventBus instance = null;
    
    private EventBus() {
        
    }
    
    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        
        return instance;
    }
    
    static public void event(Zdarzenie arg) {
        System.out.println(arg.getTyp().toString());
        System.out.println(arg.getDane().toString());
        
        getInstance().setChanged();
        getInstance().notifyObservers(arg);
    }
}
