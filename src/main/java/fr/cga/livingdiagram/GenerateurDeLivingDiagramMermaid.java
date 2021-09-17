package fr.cga.livingdiagram;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class GenerateurDeLivingDiagramMermaid extends GenerateurDeDiagrammeAbstrait {

    private final Collection<UneClasse> listeDesClassesTrouvees;
    private boolean avecLesPackages = false;

    public GenerateurDeLivingDiagramMermaid(Collection<UneClasse> listeDesClassesTrouvees) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
    }

    public GenerateurDeLivingDiagramMermaid(Collection<UneClasse> listeDesClassesTrouvees, boolean avecLesPackages) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
        this.avecLesPackages = avecLesPackages;
    }

    public String genereLeDiagrammePourLaListeDesClasses() {

        List<UneClasse> listeTrieeParPackage = listeDesClassesTrouvees.stream()
            .sorted(Comparator.comparing(UneClasse::getNomDuPackage).thenComparing(UneClasse::getNomDeLaClasse))
            .collect(Collectors.toList());

        StringBuffer diagramme = new StringBuffer("graph LR\n");

        boolean premiereClasseTraitee = true;
        String packagePrecedent = null;
        for (UneClasse classeEnCours : listeTrieeParPackage) {
            if (avecLesPackages) {
                if (!classeEnCours.nomDuPackage.equals(packagePrecedent)) {
                    packagePrecedent = classeEnCours.nomDuPackage;
                    if (!premiereClasseTraitee) {
                        diagramme.append("end\n");
                    }
                    else {
                        premiereClasseTraitee = false;
                    }
                    String nomCourtDuPackage = calculeLeNomCourt(classeEnCours.nomDuPackage);
                    diagramme.append("subgraph ").append(nomCourtDuPackage).append("\n");
                }
            }
            diagramme.append(classeEnCours.nomDeLaClasse).append("\n");
            classeEnCours.listeDesAttributs
                .stream()
                .filter(attribut -> estUnAttributAPrendreEnCompte(attribut))
                .forEach(attribut -> genereUneRelationPourCetAttribut(diagramme, classeEnCours, attribut));
        }
        if (avecLesPackages) {
            diagramme.append("end\n");
        }
        return diagramme.toString();
    }

    private void genereUneRelationPourCetAttribut(StringBuffer diagramme, UneClasse classeEnCours, UnAttribut attribut) {
        if (attribut.type.contains("<")) {
            diagramme.append(classeEnCours.nomDeLaClasse).append(" -- liste --> ").append(calculeLeNomCourt(StringUtils.substringBetween(attribut.type, "<", ">"))).append("\n");
        }
        else {
            diagramme.append(classeEnCours.nomDeLaClasse).append(" --> ").append(calculeLeNomCourt(attribut.type)).append("\n");
        }
    }

    private String calculeLeNomCourt(String nomLong) {
        String[] nomLongDecoupe = nomLong.split("\\.");
        return nomLongDecoupe[nomLongDecoupe.length-1];
    }

}
