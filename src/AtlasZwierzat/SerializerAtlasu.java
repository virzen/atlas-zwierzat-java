/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author virzen
 */
public class SerializerAtlasu {
    private static final String nazwaPliku = "/tmp/atlas.ser";
    
    public static void zapisz(Main atlas) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                                        new BufferedOutputStream(
                                          new FileOutputStream(SerializerAtlasu.nazwaPliku)))) {
            out.writeObject("Atlas zwierzat");
            out.writeObject(atlas);
        }
    }
    
    public static Main wczytaj() throws IOException, ClassNotFoundException {
        Main atlas;
        
        try (ObjectInputStream in = new ObjectInputStream(
                                      new BufferedInputStream(
                                        new FileInputStream(SerializerAtlasu.nazwaPliku)))) {
            atlas = (Main) in.readObject();
        }
        
        return atlas;
    }
}