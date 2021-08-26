package fr.cga.livingdoc.qdox;

import java.util.ArrayList;
import java.util.Collection;

public class UnAttribut {

    public final String nom;
    public final String type;

    public final Collection<UneAnnotation> listeDesAnnotations = new ArrayList<>();

    public UnAttribut(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }
}
