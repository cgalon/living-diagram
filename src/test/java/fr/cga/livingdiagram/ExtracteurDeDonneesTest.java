package fr.cga.livingdiagram;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

public class ExtracteurDeDonneesTest {
    
    @Test
    public void doitRecupererLeContenuDuneClasseVide() throws Exception {
        String cheminDuDossierDeSourcesAScanner = AssistantDePreparationDesTests.fabriqueLeCheminAbsoluVersLaRessource("_01_classe_simple");
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);

        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        UneClasse[] resultat = listeDesClassesTrouvees.toArray(new UneClasse[0]);

        assertEquals("Episode", resultat[0].nomDeLaClasse);
        assertEquals("fr.pe.incub.mescomics.referentiel.domaine", resultat[0].nomDuPackage);
    }

    @Test
    public void doitRecupererLeContenuDeDeuxClassesVides() throws Exception {
        String cheminDuDossierDeSourcesAScanner = AssistantDePreparationDesTests.fabriqueLeCheminAbsoluVersLaRessource("_02_plusieurs_classes_simples");
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);

        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        UneClasse[] resultat = listeDesClassesTrouvees.toArray(new UneClasse[0]);

        assertEquals("Episode", resultat[0].nomDeLaClasse);
        assertEquals("fr.pe.incub.mescomics.referentiel.domaine", resultat[0].nomDuPackage);
        assertEquals("Revue", resultat[1].nomDeLaClasse);
        assertEquals("fr.pe.incub.mescomics.referentiel.domaine", resultat[1].nomDuPackage);
    }

    @Test
    public void doitRecupererLeContenuDeTroisClassesCompletes() throws Exception {
        String cheminDuDossierDeSourcesAScanner = AssistantDePreparationDesTests.fabriqueLeCheminAbsoluVersLaRessource("_03_une_classe_complete");
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);

        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        UneClasse[] resultat = listeDesClassesTrouvees.toArray(new UneClasse[0]);

        assertEquals("Episode", resultat[0].nomDeLaClasse);
        assertEquals("fr.pe.incub.mescomics.referentiel.domaine", resultat[0].nomDuPackage);
        UnAttribut[] listeDesAttributs = resultat[0].listeDesAttributs.toArray(new UnAttribut[0]);
        assertEquals("heros", listeDesAttributs[0].nom);
        assertEquals("java.lang.String", listeDesAttributs[0].type);
        UneAnnotation[] listeDesAnnotationDeHeros = listeDesAttributs[0].listeDesAnnotations.toArray(new UneAnnotation[0]);
        assertEquals("BeanProperty", listeDesAnnotationDeHeros[0].nom);

        assertEquals("titre",listeDesAttributs[1].nom);
        assertEquals("java.lang.String", listeDesAttributs[1].type);
        assertEquals("dessinateur", listeDesAttributs[2].nom);
        assertEquals("java.lang.String", listeDesAttributs[2].type);

        UneMethode[] listeDesMethodes = resultat[0].listeDesMethodes.toArray(new UneMethode[0]);
        assertEquals("recupereLeHeros", listeDesMethodes[0].nom);
        assertEquals("equals", listeDesMethodes[1].nom);
        UneAnnotation[] listeDesAnnotationDeEquals = listeDesMethodes[1].listeDesAnnotations.toArray(new UneAnnotation[0]);
        assertEquals("Override", listeDesAnnotationDeEquals[0].nom);

        assertTrue(resultat[0].listeDesImports.contains("java.beans.BeanProperty"));
        assertTrue(resultat[0].listeDesImports.contains("java.util.Objects"));
    }

    @Test
    public void doitRecupererLeContenuDeTroisDomainesComplets() throws Exception {
        String cheminDuDossierDeSourcesAScanner = AssistantDePreparationDesTests.fabriqueLeCheminAbsoluVersLaRessource("_10_trois_domaines_complets");
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDuDossierDeSourcesAScanner);

        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        List<UneClasse> listeTrieeParPackage = listeDesClassesTrouvees.stream()
                .sorted(Comparator.comparing(UneClasse::getNomDuPackage).thenComparing(UneClasse::getNomDeLaClasse))
                .collect(Collectors.toList());
        UneClasse[] resultat = listeTrieeParPackage.toArray(new UneClasse[0]);

        assertEquals(26, resultat.length);
    }
}
