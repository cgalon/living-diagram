package fr.cga.livingdiagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GenerateurDeClassDiagramMermaidTest {

    private AssistantDePreparationDesTests assistant = new AssistantDePreparationDesTests();

    @Test
    public void doitGenererUnDiagrammePourUneSeuleClasse() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_01_classe_simple");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "classDiagram\n" +
                "class Episode\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    public void doitGenererUnDiagrammePourDeuxClasses() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_02_plusieurs_classes_simples");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "classDiagram\n" +
                "class Episode\n" +
                "class Revue\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    public void doitGenererLesAttributsPourUneClasse() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_06_une_classe_avec_des_attributs");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "classDiagram\n" +
                "class Episode\n" +
                "Episode : java.lang.String heros\n" +
                "Episode : java.lang.String titre\n" +
                "Episode : java.lang.String dessinateur\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    public void doitGenererLesAttributsEtLesMethodesPourUneClasse() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_03_une_classe_complete");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "classDiagram\n" +
                "class Episode\n" +
                "Episode : java.lang.String heros\n" +
                "Episode : java.lang.String titre\n" +
                "Episode : java.lang.String dessinateur\n" +
                "Episode : recupereLeHeros()\n" +
                "Episode : equals()\n" +
                "Episode : hashCode()\n";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    @Disabled
    public void doitGenererLesCadresPourChaquePackage() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_04_affichage_des_packages");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    @Disabled
    public void doitGenererLesDependancesEntreLesClasses() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = assistant.fabriqueLaCollectionDesClassesSources("_05_dependances_intra_composant");
        GenerateurDeDiagramme generateur = new GenerateurDeClassDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "";

        String diagramme = generateur.genereLeDiagrammePourLaListeDesClasses();

        assertEquals(diagrammeAttendu, diagramme);
    }

}
