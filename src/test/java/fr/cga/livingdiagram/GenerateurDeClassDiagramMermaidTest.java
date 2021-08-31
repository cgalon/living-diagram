package fr.cga.livingdiagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GenerateurDeClassDiagramMermaidTest {

    private AssistantDePreparationDesTests assistant = new AssistantDePreparationDesTests();

    @Test
    @Disabled
    public void doitGenererUnDiagrammePourUneSeuleClasse() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_01_classe_simple");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    @Disabled
    public void doitGenererUnDiagrammePourDeuxClasses() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_02_plusieurs_classes_simples");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    @Disabled
    public void doitGenererLesCadresPourChaquePackage() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_04_affichage_des_packages");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    @Disabled
    public void doitGenererLesDependancesEntreLesClasses() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_05_dependances_intra_composant");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

}
