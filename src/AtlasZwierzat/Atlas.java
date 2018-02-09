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
 * @author virzen
 */
public class Atlas implements Serializable {
    private List<Typ> typy = new ArrayList<>();
    private List<Rodzina> rodziny = new ArrayList<>();
    private List<Gatunek> gatunki = new ArrayList<>();

    public Atlas() {
    }

    public List<Typ> getTypy() {
        return typy;
    }

    /**
     * Znajduje typ w atlasie wedlug nazwy
     *
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
     *
     * @param id
     * @return
     */
    public Typ znajdzTyp(int id) {
        Predicate<Typ> predykat = t -> t.getId() == id;
        Typ typ = this.getTypy().stream().filter(predykat).findFirst().orElse(null);

        return typ;
    }

    public List<Rodzina> getRodziny() {
        return rodziny;
    }

    Rodzina znajdzRodzine(String nazwa) {
        Predicate<Rodzina> predykat = r -> r.getNazwa().equals(nazwa);
        Rodzina rodzina = this.getRodziny().stream().filter(predykat).findFirst().orElse(null);

        return rodzina;
    }

    Rodzina znajdzRodzine(int id) {
        Predicate<Rodzina> predykat = (Rodzina r) -> r.getId() == id;
        Rodzina rodzina = this.getRodziny().stream().filter(predykat).findFirst().orElse(null);

        return rodzina;
    }

    public List<Gatunek> getGatunki() {
        return gatunki;
    }


    Gatunek znajdzGatunek(String nazwa) {
        Predicate<Gatunek> predykat = g -> g.getNazwa().equals(nazwa);

        return this.getGatunki().stream().filter(predykat).findFirst().orElse(null);
    }

    Gatunek znajdzGatunek(int id) {
        Predicate<Gatunek> predykat = g -> g.getId() == id;

        return this.getGatunki().stream().filter(predykat).findFirst().orElse(null);
    }

    public Krzyzowka znajdzKrzyzowke(String nazwa) {
        Predicate<Gatunek> predykat = g -> g.getNazwa().equals(nazwa);
        Gatunek gatunek = this.getGatunki().stream().filter(predykat).findFirst().orElse(null);

        System.out.println(gatunek);

        if (gatunek instanceof Krzyzowka) {
            return (Krzyzowka) gatunek;
        }

        return null;
    }

    List<Gatunek> znajdzGatunkiRodziny(Rodzina rodzina) {
        Predicate<Gatunek> predykat = g -> g.getRodzina().equals(rodzina);

        return getGatunki().stream().filter(predykat).collect(Collectors.toList());
    }

    public void dodaj(Typ typ) {
        this.typy.add(typ);
    }

    public void dodaj(Rodzina rodzina) {
        this.rodziny.add(rodzina);
    }

    public void dodaj(Gatunek gatunek) {
        this.gatunki.add(gatunek);
    }

    public void usun(Typ typ) {
        int index = this.typy.indexOf(typ);

        if (index != -1) {
            typy.remove(index);
        }
    }

    public void usun(Rodzina rodzina) {
        int index = this.rodziny.indexOf(rodzina);

        if (index != -1) {
            rodziny.remove(index);
        }
    }

    public void usun(Gatunek gatunek) {
        int index = this.gatunki.indexOf(gatunek);

        if (index != -1) {
            gatunki.remove(index);
        }
    }

    /**
     * @param zapytanie
     * @return
     */
    public List<Gatunek> filtrujGatunki(String zapytanie) {
        Predicate<Gatunek> czyZawieraZapytanie = g -> g.getNazwa().toLowerCase().contains(zapytanie.toLowerCase());

        List<Gatunek> przefiltrowaneGatunki = this.getGatunki().stream().filter(czyZawieraZapytanie).collect(Collectors.toList());

        return przefiltrowaneGatunki;
    }
}
