package fr.cga.livingdoc.qdox;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class UtilisationDeQDoxTest {
    
    @Test
    public void doitCreerUnFichierFichierAvecLaDescriptiondesElementsDuPackage() throws Exception {
        String cheminDuDossierDeSourcesAScanner = new String("E:\\dev\\living-diagram\\src\\test\\resources\\_10_trois_domaines_complets");
        byte[] contenuDuFichierDeReference = Files.readAllBytes(FileSystems.getDefault().getPath("E:\\dev\\living-diagram\\src\\test\\resources\\resultat-utilisation-qdox-reference.json"));

        ExplorateurDeClasses explorateurDeClasses = new ExplorateurDeClasses();
        String resultat = explorateurDeClasses.creeLaDescriptionDuContenuDuPackage(cheminDuDossierDeSourcesAScanner);
        
        assertTrue(Arrays.equals(contenuDuFichierDeReference, resultat.getBytes()));
    }


}
