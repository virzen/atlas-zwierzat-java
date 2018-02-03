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
public class EdytowanyTyp extends Typ {
    private final int idTypu;
    
    public int getIdTypu() {
        return idTypu;
    }
    
    public EdytowanyTyp(String nazwa, int szacowanaLiczba, String typowaBudowaCiala, int idTypu) {
        super(nazwa, szacowanaLiczba, typowaBudowaCiala);
        this.idTypu = idTypu;
    }
}
