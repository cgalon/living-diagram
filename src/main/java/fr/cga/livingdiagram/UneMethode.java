package fr.cga.livingdiagram;

import java.util.ArrayList;
import java.util.Collection;

public class UneMethode {

    public final String nom;
    public final String typeDeRetour;

    public final Collection<UneAnnotation> listeDesAnnotations = new ArrayList<>();

    public UneMethode(String nom, String typeDeRetour) {
        this.nom = nom;
        this.typeDeRetour = typeDeRetour;
    }

}
