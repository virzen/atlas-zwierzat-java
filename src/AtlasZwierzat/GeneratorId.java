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
public class GeneratorId {
    private static int lastId = 0;
    
    public static int getNewId() {
        return ++lastId;
    }
}
