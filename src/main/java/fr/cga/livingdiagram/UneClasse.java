package fr.cga.livingdiagram;

import java.util.ArrayList;
import java.util.Collection;

public class UneClasse {

    public final String nomDuPackage;
    public final String nomDeLaClasse;

    public final Collection<UneMethode> listeDesMethodes = new ArrayList<>();
    public final Collection<UnAttribut> listeDesAttributs = new ArrayList<>();
    public final Collection<String> listeDesImports = new ArrayList<>();

    public UneClasse(String nomDuPackage, String nomDeLaClasse) {
        this.nomDuPackage = nomDuPackage;
        this.nomDeLaClasse = nomDeLaClasse;
    }

    // Uniquement utilisée pour la comparaison dans un stream
    public String getNomDuPackage() {
        return nomDuPackage;
    }

    // Uniquement utilisée pour la comparaison dans un stream
    public String getNomDeLaClasse() {
        return nomDeLaClasse;
    }

}
