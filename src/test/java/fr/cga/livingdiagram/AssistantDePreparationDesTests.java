package fr.cga.livingdiagram;

import java.io.File;
import java.util.Collection;

public class AssistantDePreparationDesTests {
    
    public Collection<UneClasse> fabriqueLaCollectionDesClassesSources(String dossierDeSourcesAScanner) {
        String cheminDuDossierDeSourcesAScanner = fabriqueLeCheminAbsoluVersLaRessource(dossierDeSourcesAScanner);
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);
        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        return listeDesClassesTrouvees;
    }

    public static String fabriqueLeCheminAbsoluVersLaRessource(String dossierDeSourcesAScanner) {
        File fichier = new File("src/test/resources/" + dossierDeSourcesAScanner);
        String cheminDuDossierDeSourcesAScanner = fichier.getAbsolutePath();
        return cheminDuDossierDeSourcesAScanner;
    }

}
