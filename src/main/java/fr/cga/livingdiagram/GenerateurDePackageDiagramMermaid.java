package fr.cga.livingdiagram;

import java.util.Collection;
import java.util.Comparator;

public class GenerateurDePackageDiagramMermaid implements GenerateurDeDiagramme {

    private final Collection<UneClasse> listeDesClassesTrouvees;

    public GenerateurDePackageDiagramMermaid(Collection<UneClasse> listeDesClassesTrouvees) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
    }

    public String genereLeDiagrammePourLaListeDesClasses() {
        StringBuffer diagramme = new StringBuffer("graph LR\n");
        listeDesClassesTrouvees.stream()
            .sorted(Comparator.comparing(UneClasse::getNomDuPackage).thenComparing(UneClasse::getNomDeLaClasse))
            .forEach(classeEnCours->diagramme.append(classeEnCours.nomDuPackage).append("\n"));

        return diagramme.toString();
    }

}
