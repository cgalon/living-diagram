package fr.cga.livingdiagram;

import java.util.Collection;

public class GenerateurDeClassDiagramMermaid implements GenerateurDeDiagramme {

    private final Collection<UneClasse> listeDesClassesTrouvees;

    public GenerateurDeClassDiagramMermaid(Collection<UneClasse> listeDesClassesTrouvees) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
    }

    public String genereLeDiagrammePourLaListeDesClasses() {

        StringBuffer diagramme = new StringBuffer("\n");

        return diagramme.toString();
    }

}
