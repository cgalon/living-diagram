package fr.cga.livingdiagram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class GenerateurDeDiagramme {

    public static void main(String[] args) throws Exception {
        String cheminDesSources = args[0];
        String fichierDuDiagrammeGenere = args[1];

        GenerateurDePackageDiagramMermaidDepuisLesImports generateurDepuisLesImports = creeUnGenerateurDeDiagrammeViaLesImports(cheminDesSources);

        String diagramme = generateurDepuisLesImports.genereLeDiagrammePourLaListeDesClasses();

        sauvegardeLeDiagrammeSurDisque(fichierDuDiagrammeGenere, diagramme);
    }

    private static GenerateurDePackageDiagramMermaidDepuisLesImports creeUnGenerateurDeDiagrammeViaLesImports(String cheminDesSources) {
        ExtracteurDeDonnees extracteur = new ExtracteurDeDonnees(cheminDesSources);
        Collection<UneClasse> listeDesClassesTrouvees = extracteur.retrouveLaDescriptionDesClassesDuPackage();
        GenerateurDePackageDiagramMermaidDepuisLesImports generateurDepuisLesImports =
                new GenerateurDePackageDiagramMermaidDepuisLesImports(listeDesClassesTrouvees);
        return generateurDepuisLesImports;
    }

    private static void sauvegardeLeDiagrammeSurDisque(String fichierDuDiagrammeGenere, String diagramme) throws IOException {
        File fichierDuDiagramme = new File(fichierDuDiagrammeGenere);
        FileWriter ecrivain = new FileWriter(fichierDuDiagramme);
        ecrivain.write(diagramme);
        ecrivain.close();
    }

}
