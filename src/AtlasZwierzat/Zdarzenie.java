/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

/**
 *
 * @author virzen
 */
public class Zdarzenie {
    private final TypZdarzenia typ;
    private final Object dane;
    
    public Object getDane() {
        return dane;
    }
    
    public TypZdarzenia getTyp() {
        return typ;
    }
    
    public Zdarzenie(TypZdarzenia typ, Object dane) {
        this.typ = typ;
        this.dane = dane;
    }
}
