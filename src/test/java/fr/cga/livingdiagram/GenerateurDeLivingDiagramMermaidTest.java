package fr.cga.livingdiagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;

public class GenerateurDeLivingDiagramMermaidTest {

    @Test
    public void doitGenererUnDiagrammePourUneSeuleClasse() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_01_classe_simple");
        GenerateurDeLivingDiagramMermaid generateur = new GenerateurDeLivingDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "graph LR\n" + "Episode\n";

        String diagramme = generateur.genereUnLivingDiagramEnMermaid();

        assertEquals(diagrammeAttendu, diagramme);

    }

    @Test
    public void doitGenererUnDiagrammePourDeuxClasses() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_02_plusieurs_classes_simples");
        GenerateurDeLivingDiagramMermaid generateur = new GenerateurDeLivingDiagramMermaid(listeDesClassesTrouvees);
        String diagrammeAttendu = "graph LR\n" + "Episode\n" + "Revue\n";

        String diagramme = generateur.genereUnLivingDiagramEnMermaid();

        assertEquals(diagrammeAttendu, diagramme);

    }

    @Test
    public void doitGenererLesCadresPourChaquePackage() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_04_affichage_des_packages");
        GenerateurDeLivingDiagramMermaid generateur = new GenerateurDeLivingDiagramMermaid(listeDesClassesTrouvees, true);
        String diagrammeAttendu = "graph LR\n" + 
        "subgraph api\n" + "ReferentielDeRevues\n" + "end\n" + 
        "subgraph domaine\n" + "Episode\n" + "Numero\n" + "Revue\n" + "end\n" + 
        "subgraph infrastructure\n" + "EntrepotDeNumeros\n" + "EntrepotDeRevues\n" + "EpisodeDAO\n" + "NumeroDAO\n" + "RevueDAO\n" + "ServiceDeRevue\n" + "end\n" + 
        "subgraph ressource\n" + "ReferentielDeRevuesController\n" + "end\n";

        String diagramme = generateur.genereUnLivingDiagramEnMermaid();

        assertEquals(diagrammeAttendu, diagramme);
    }

    @Test
    public void doitGenererLesDependancesEntreLesClasses() throws Exception {
        Collection<UneClasse> listeDesClassesTrouvees = fabriqueLaCollectionDesClassesSources("E:\\dev\\living-diagram\\src\\test\\resources\\_05_dependances_intra_composant");
        GenerateurDeLivingDiagramMermaid generateur = new GenerateurDeLivingDiagramMermaid(listeDesClassesTrouvees, true);
        String diagrammeAttendu = "graph LR\n" + 
        "subgraph api\n" + 
            "ReferentielDeRevues\n" + 
            "ReferentielDeRevues --> ServiceDeRevue\n" + 
        "end\n" + 
        "subgraph domaine\n" + 
            "Episode\n" + 
            "Numero\n" + 
            "Numero -- liste --> Episode\n" + 
            "Revue\n" + 
        "end\n" + 
        "subgraph infrastructure\n" + 
            "EntrepotDeNumeros\n" + 
            "EntrepotDeRevues\n" + 
            "EpisodeDAO\n" + 
            "NumeroDAO\n" + 
            "NumeroDAO -- liste --> EpisodeDAO\n" + 
            "RevueDAO\n" + 
            "ServiceDeRevue\n" + 
            "ServiceDeRevue --> EntrepotDeRevues\n" + 
            "ServiceDeRevue --> EntrepotDeNumeros\n" + 
        "end\n" + 
        "subgraph ressource\n" + 
        "ReferentielDeRevuesController\n" + 
        "ReferentielDeRevuesController --> ReferentielDeRevues\n" + 
        "end\n";

        String diagramme = generateur.genereUnLivingDiagramEnMermaid();

        assertEquals(diagrammeAttendu, diagramme);
    }

    private Collection<UneClasse> fabriqueLaCollectionDesClassesSources(String cheminDuDossierDeSourcesAScanner) {
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);
        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        return listeDesClassesTrouvees;
    }

}
