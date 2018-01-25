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
    private static final String DOMYSLNA_NAZWA_PLIKU = "/tmp/atlas.ser";
    
    private static String getNazwaPliku(String podanaNazwa) {
        return podanaNazwa == null ? DOMYSLNA_NAZWA_PLIKU : podanaNazwa;
    }
    
    public static String getDomyslnaNazwaPliku() {
        return DOMYSLNA_NAZWA_PLIKU;
    }
    
    public static void zapisz(Atlas atlas, String nazwaPliku) throws IOException {
        String faktycznaNazwaPliku = getNazwaPliku(nazwaPliku);
        
        try (ObjectOutputStream out = new ObjectOutputStream(
                                        new BufferedOutputStream(
                                          new FileOutputStream(faktycznaNazwaPliku)))) {
            out.writeObject(atlas);
        }
    }
    
    public static void zapisz(Atlas atlas) throws IOException {
        zapisz(atlas, null);
    }
    
    public static Atlas wczytaj(String nazwaPliku) throws IOException, ClassNotFoundException {
        String faktycznaNazwaPliku = getNazwaPliku(nazwaPliku);
        Atlas atlas;
        
        try (ObjectInputStream in = new ObjectInputStream(
                                      new BufferedInputStream(
                                        new FileInputStream(faktycznaNazwaPliku)))) {
            atlas = (Atlas) in.readObject();
        }
        
        return atlas;
    }
    
    public static Atlas wczytaj() throws IOException, ClassNotFoundException {
        return wczytaj(null);
    }
}
