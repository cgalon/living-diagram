package fr.pe.incub.mescomics.collection.domaine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La liste des comics détenus par l'utilisateur.
 */
public class Collection {

    private List<Comics> tousLesComics = new ArrayList<>();
    private LocalDate dateDuDernierAjout;

    public void ajouteUnNouveauComics(Comics unNouveauComics) {
        tousLesComics.add(unNouveauComics);
        dateDuDernierAjout = LocalDate.now();
    }

    public int recupereLeNombreDeComics() {
        return tousLesComics.size();
    }

    public void supprimeUnComics(String nomDeLaRevue, int numero) {
        Comics comicsASupprimer = new Comics(nomDeLaRevue, numero);
        tousLesComics.remove(comicsASupprimer);
    }

    public LocalDate recupereLaDateDeDernierAjout() {
        return dateDuDernierAjout;
    }

    public List<Comics> recupereLaListeDesComicsDeLaSerie(String titre) {
        return tousLesComics.stream().filter(unComics -> unComics.titreDeLaRevue.equals(titre)).collect(Collectors.toList());
    }
}