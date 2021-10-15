package fr.cga.livingdiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateurDePackageDiagramMermaidDepuisLesImports extends GenerateurDeDiagrammeAbstrait {

    private final Collection<UneClasse> listeDesClassesTrouvees;

    public GenerateurDePackageDiagramMermaidDepuisLesImports(Collection<UneClasse> listeDesClassesTrouvees) {
        this.listeDesClassesTrouvees = listeDesClassesTrouvees;
    }

    public String genereLeDiagrammePourLaListeDesClasses() {
        StringBuffer diagramme = new StringBuffer("graph LR\n");
        List<UneClasse> listeTrieeParPackage = listeDesClassesTrouvees.stream()
                .sorted(Comparator.comparing(UneClasse::getNomDuPackage))
                .collect(Collectors.toList());

        List<String> listeDesDependancesDejaIdentifiees = new ArrayList<>();
        for (UneClasse classeEnCours : listeTrieeParPackage) {
            diagramme.append(classeEnCours.nomDuPackage).append("\n");
            classeEnCours.listeDesImports
                    .stream()
                    .filter(unImport -> estUnImportAPrendreEnCompte(unImport))
                    .forEach(unImport -> genereUneRelationPourCetImportSiNecessaireEtMetAJourLaListeDesDependances(diagramme, classeEnCours, unImport, listeDesDependancesDejaIdentifiees));
        }

        return diagramme.toString();
    }

    private void genereUneRelationPourCetImportSiNecessaireEtMetAJourLaListeDesDependances(StringBuffer diagramme, UneClasse classeEnCours, String unImport, List<String> listeDesDependancesDejaIdentifiees) {
        String nomDuPackageDeLImport = calculeLeNomDePackageDeLaDependance(unImport);
        if (!classeEnCours.nomDuPackage.equals(nomDuPackageDeLImport)) {
            String dependance = classeEnCours.nomDuPackage + " --> " + nomDuPackageDeLImport;
            if (!listeDesDependancesDejaIdentifiees.contains(dependance)) {
                diagramme.append(dependance).append("\n");
                listeDesDependancesDejaIdentifiees.add(dependance);
            }
        }
    }

    private String calculeLeNomDePackageDeLaDependance(String type) {
        String nomDuPackage = type.substring(0, type.lastIndexOf("."));
        return nomDuPackage;
    }

}
