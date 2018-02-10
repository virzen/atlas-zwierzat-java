/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

/**
 * Generuje unikalne id
 * @author virzen
 */
public class GeneratorId {
    private static int lastId = 0;
    
    public static int wygenerujId() {
        return ++lastId;
    }
}
