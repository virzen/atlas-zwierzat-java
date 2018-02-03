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
public class EdytowanaRodzina extends Rodzina {
    private final int idRodziny;

    public int getIdRodziny() {
        return idRodziny;
    }
 
    public EdytowanaRodzina(String nazwa, String cechaCharakterystyczna, float sredniaLiczbaKonczyn, Typ typ, int idRodziny) {
        super(nazwa, cechaCharakterystyczna, sredniaLiczbaKonczyn, typ);
        this.idRodziny = idRodziny;
    }
}
