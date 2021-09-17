package fr.cga.livingdiagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GenerateurDePackageDiagramMermaidTest {

    private AssistantDePreparationDesTests assistant = new AssistantDePreparationDesTests();

    @Test
    public void doitGenererUnDiagrammePourUneSeuleClasse() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_01_classe_simple");
        GenerateurDePackageDiagramMermaid generateur = new GenerateurDePackageDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "graph LR\n" + "fr.pe.incub.mescomics.referentiel.domaine\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    public void doitGenererUnDiagrammePourDeuxClassesDansDeuxPackagesDifferents() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_07_deux_classes_dans_des_packages_differents");
        GenerateurDePackageDiagramMermaid generateur = new GenerateurDePackageDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "graph LR\n" +
                "fr.pe.incub.mescomics.referentiel.domaine\n" +
                "fr.pe.incub.mescomics.referentiel.domaine2\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    public void doitGenererUnDiagrammeAvecPlusieursDependancesEntreLesPackages() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_05_dependances_inter_packages");
        GenerateurDePackageDiagramMermaid generateur = new GenerateurDePackageDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "graph LR\n" +
	        "fr.pe.incub.mescomics.referentiel.api\n" +
	        "fr.pe.incub.mescomics.referentiel.api --> fr.pe.incub.mescomics.referentiel.infrastructure\n" +
	        "fr.pe.incub.mescomics.referentiel.domaine\n" +
	        "fr.pe.incub.mescomics.referentiel.infrastructure\n" +
	        "fr.pe.incub.mescomics.referentiel.ressource\n" +
	        "fr.pe.incub.mescomics.referentiel.ressource --> fr.pe.incub.mescomics.referentiel.api\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

}
