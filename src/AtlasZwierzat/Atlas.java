/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtlasZwierzat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Przechowuje i pozwala na manipulacje typami, gatunkami i rodzinami
 * @author virzen
 */
public class Atlas implements Serializable {
    private List<Typ> typy = new ArrayList<>();
    private List<Rodzina> rodziny = new ArrayList<>();
    private List<Gatunek> gatunki = new ArrayList<>();

    public Atlas() {
    }

    /**
     * Zwarca wszystkie typy w atlasie
     * @return
     */
    public List<Typ> getTypy() {
        return typy;
    }

    /**
     * Znajduje typ w atlasie wedlug nazwy
     * @param nazwa
     * @return
     */
    public Typ znajdzTyp(String nazwa) {
        Predicate<Typ> predykat = t -> t.getNazwa().equals(nazwa);
        Typ typ = this.getTypy().stream().filter(predykat).findFirst().orElse(null);

        return typ;
    }

    /**
     * Znajduje typ w atlasie wedlug id
     * @param id
     * @return
     */
    public Typ znajdzTyp(int id) {
        Predicate<Typ> predykat = t -> t.getId() == id;
        Typ typ = this.getTypy().stream().filter(predykat).findFirst().orElse(null);

        return typ;
    }

    /**
     * Zwraca wszystkie rodziny w atlasie
     * @return
     */
    public List<Rodzina> getRodziny() {
        return rodziny;
    }

    /**
     * Znajduje rodzine w atlasie wedlug nazwy
     * @param nazwa
     * @return
     */
    Rodzina znajdzRodzine(String nazwa) {
        Predicate<Rodzina> predykat = r -> r.getNazwa().equals(nazwa);
        Rodzina rodzina = this.getRodziny().stream().filter(predykat).findFirst().orElse(null);

        return rodzina;
    }

    /**
     * Znajduje rodzine w atlasie wedlug id
     * @param id
     * @return
     */
    Rodzina znajdzRodzine(int id) {
        Predicate<Rodzina> predykat = (Rodzina r) -> r.getId() == id;
        Rodzina rodzina = this.getRodziny().stream().filter(predykat).findFirst().orElse(null);

        return rodzina;
    }

    /**
     * Zwraca wszystkie gatunki w atlasie
     * @return
     */
    public List<Gatunek> getGatunki() {
        return gatunki;
    }


    /**
     * Znajduje gatunek w atlasie wedlug nazwy
     * @param nazwa
     * @return
     */
    Gatunek znajdzGatunek(String nazwa) {
        Predicate<Gatunek> predykat = g -> g.getNazwa().equals(nazwa);

        return this.getGatunki().stream().filter(predykat).findFirst().orElse(null);
    }

    /**
     * Znajduje gatunek w atlasie wedlug id
     * @param id
     * @return
     */
    Gatunek znajdzGatunek(int id) {
        Predicate<Gatunek> predykat = g -> g.getId() == id;

        return this.getGatunki().stream().filter(predykat).findFirst().orElse(null);
    }

    /**
     * Znajduje krzyzowke w atlasie wedlug nazwy
     * @param nazwa
     * @return
     */
    public Krzyzowka znajdzKrzyzowke(String nazwa) {
        Predicate<Gatunek> predykat = g -> g.getNazwa().equals(nazwa);
        Gatunek gatunek = this.getGatunki().stream().filter(predykat).findFirst().orElse(null);

        System.out.println(gatunek);

        if (gatunek instanceof Krzyzowka) {
            return (Krzyzowka) gatunek;
        }

        return null;
    }

    /**
     * Zwraca gatunki nalezace do podanej rodziny
     * @param rodzina
     * @return
     */
    public List<Gatunek> znajdzGatunkiRodziny(Rodzina rodzina) {
        Predicate<Gatunek> predykat = g -> g.getRodzina().equals(rodzina);

        return getGatunki().stream().filter(predykat).collect(Collectors.toList());
    }

    /**
     * Dodaje typ do atlasu
     * @param typ
     */
    public void dodaj(Typ typ) {
        this.typy.add(typ);
    }

    /**
     * Dodaje rodzine do atlasu
     * @param rodzina
     */
    public void dodaj(Rodzina rodzina) {
        this.rodziny.add(rodzina);
    }

    /**
     * Dodaje gatunek do atlasu
     * @param gatunek
     */
    public void dodaj(Gatunek gatunek) {
        this.gatunki.add(gatunek);
    }

    /**
     * Usuwa typ z atlasu
     * @param typ
     */
    public void usun(Typ typ) {
        int index = this.typy.indexOf(typ);

        if (index != -1) {
            typy.remove(index);
        }
    }

    /**
     * Usuwa rodzine z atlasu
     * @param rodzina
     */
    public void usun(Rodzina rodzina) {
        int index = this.rodziny.indexOf(rodzina);

        if (index != -1) {
            rodziny.remove(index);
        }
    }

    /**
     * Usuwa gatunek z atlasu
     * @param gatunek
     */
    public void usun(Gatunek gatunek) {
        int index = this.gatunki.indexOf(gatunek);

        if (index != -1) {
            gatunki.remove(index);
        }
    }

    /**
     * Zwraca liste gatunkow, ktore zawieraja w nazwie {@code zapytanie}
     * @param zapytanie
     * @return
     */
    public List<Gatunek> filtrujGatunki(String zapytanie) {
        Predicate<Gatunek> czyZawieraZapytanie = g -> g.getNazwa().toLowerCase().contains(zapytanie.toLowerCase());

        List<Gatunek> przefiltrowaneGatunki = this.getGatunki().stream().filter(czyZawieraZapytanie).collect(Collectors.toList());

        return przefiltrowaneGatunki;
    }
}
