package fr.cga.livingdiagram;

import java.util.Collection;

public class AssistantDePreparationDesTests {
    
    public Collection<UneClasse> fabriqueLaCollectionDesClassesSources(String cheminDuDossierDeSourcesAScanner) {
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);
        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        return listeDesClassesTrouvees;
    }

}
