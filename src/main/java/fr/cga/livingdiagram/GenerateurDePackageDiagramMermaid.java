package fr.cga.livingdiagram;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateurDePackageDiagramMermaid extends GenerateurDeDiagrammeAbstrait {

    private final Collection<UneClasse> listeDesClassesTrouvees;

    public GenerateurDePackageDiagramMermaid(Collection<UneClasse> listeDesClassesTrouvees) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
    }

    public String genereLeDiagrammePourLaListeDesClasses() {
        StringBuffer diagramme = new StringBuffer("graph LR\n");
        List<UneClasse> listeTrieeParPackage = listeDesClassesTrouvees.stream()
                .sorted(Comparator.comparing(UneClasse::getNomDuPackage))
                .collect(Collectors.toList());

        String packageEnCours = "";
        List<String> listeDesDependancesDejaIdentifiees = new ArrayList<>();
        for (UneClasse classeEnCours : listeTrieeParPackage) {
            if (!packageEnCours.equals(classeEnCours.getNomDuPackage())) {
                diagramme.append(classeEnCours.nomDuPackage).append("\n");
                packageEnCours = classeEnCours.getNomDuPackage();
            }
            classeEnCours.listeDesAttributs
                    .stream()
                    .filter(attribut -> estUnAttributAPrendreEnCompte(attribut))
                    .forEach(attribut -> genereUneRelationPourCetAttributSiNecessaireEtMetAJourLaListeDesDependances(diagramme, classeEnCours, attribut, listeDesDependancesDejaIdentifiees));
        }

        return diagramme.toString();
    }

    private void genereUneRelationPourCetAttributSiNecessaireEtMetAJourLaListeDesDependances(StringBuffer diagramme, UneClasse classeEnCours, UnAttribut attribut, List<String> listeDesDependancesDejaIdentifiees) {
        String nomDuPackageDeLaDependance = calculeLeNomDePackageDeLaDependance(attribut.type);
        if (!classeEnCours.nomDuPackage.equals(nomDuPackageDeLaDependance)) {
            String dependance = classeEnCours.nomDuPackage + " --> " + nomDuPackageDeLaDependance;
            if (!listeDesDependancesDejaIdentifiees.contains(dependance)) {
                diagramme.append(dependance).append("\n");
                listeDesDependancesDejaIdentifiees.add(dependance);
            }
        }
    }

    private String calculeLeNomDePackageDeLaDependance(String type) {
        if (type.contains("<")) {
            type = StringUtils.substringBetween(type, "<", ">");
        }
        String nomDuPackage = type.substring(0, type.lastIndexOf("."));
        return nomDuPackage;
    }

}
