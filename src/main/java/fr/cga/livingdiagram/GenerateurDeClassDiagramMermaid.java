package fr.cga.livingdiagram;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateurDeClassDiagramMermaid extends GenerateurDeDiagrammeAbstrait {

    private final Collection<UneClasse> listeDesClassesTrouvees;

    public GenerateurDeClassDiagramMermaid(Collection<UneClasse> listeDesClassesTrouvees) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
    }

    public String genereLeDiagrammePourLaListeDesClasses() {

        List<UneClasse> listeTrieeParPackage = listeDesClassesTrouvees.stream()
                .sorted(Comparator.comparing(UneClasse::getNomDuPackage).thenComparing(UneClasse::getNomDeLaClasse))
                .collect(Collectors.toList());

        StringBuffer diagramme = new StringBuffer("classDiagram\n");
        listeTrieeParPackage.stream().forEach(classeEnCours -> genereLeContenuDeLaClasse(diagramme, classeEnCours));

        return diagramme.toString();
    }

    private StringBuffer genereLeContenuDeLaClasse(StringBuffer diagramme, UneClasse classeAGenerer) {
        diagramme.append("class ").append(classeAGenerer.nomDeLaClasse).append("\n");
        classeAGenerer.listeDesAttributs.stream().forEach(unAttribut -> genereUnAttribut(diagramme, classeAGenerer.nomDeLaClasse, unAttribut));
        classeAGenerer.listeDesMethodes.stream().forEach(uneMethode -> genereUneMethode(diagramme, classeAGenerer.nomDeLaClasse, uneMethode));
        return diagramme;

    }

    private Object genereUneMethode(StringBuffer diagramme, String nomDeLaClasse, UneMethode methodeAGenerer) {
        diagramme.append(nomDeLaClasse).append(" : ")
            .append(methodeAGenerer.nom).append("()")
            .append("\n");
        return diagramme;
    }

    private StringBuffer genereUnAttribut(StringBuffer diagramme, String nomDeLaClasse, UnAttribut attributAGenerer) {
        diagramme.append(nomDeLaClasse).append(" : ")
            .append(attributAGenerer.type).append(" ").append(attributAGenerer.nom)
            .append("\n");
        return diagramme;
    }

}
