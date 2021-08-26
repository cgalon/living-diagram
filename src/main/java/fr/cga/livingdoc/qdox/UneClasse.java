package fr.cga.livingdoc.qdox;

import java.util.ArrayList;
import java.util.Collection;

public class UneClasse {

    public final String nomDuPackage;
    public final String nomDeLaClasse;

    public final java.util.Collection<UneMethode> listeDesMethodes = new ArrayList<>();
    public final java.util.Collection<UnAttribut> listeDesAttributs = new ArrayList<>();
    public final Collection<UneAnnotation> listeDesAnnotations = new ArrayList<>();

    public UneClasse(String nomDuPackage, String nomDeLaClasse) {
        this.nomDuPackage = nomDuPackage;
        this.nomDeLaClasse = nomDeLaClasse;
    }
}
