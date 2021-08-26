package fr.cga.livingdoc.qdox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ExplorateurDeClasses {

    public String creeLaDescriptionDuContenuDuPackage(String cheminDuDossierDesSources) throws IOException {
        File repertoireDesSources = new File(cheminDuDossierDesSources);
        Collection<UneClasse> lesClassesScannees = scanneUnDossierDeSources(repertoireDesSources);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(lesClassesScannees);
    }

    private Collection<UneClasse> scanneUnDossierDeSources(File dossierDeSources) {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        Collection<UneClasse> lesClassesScannees = new ArrayList<>();

        builder.addSourceTree(dossierDeSources);
        builder.getClasses().stream().forEach(classe -> lesClassesScannees.add(scanneLaClasse(classe)));
        return lesClassesScannees;
    }

    private UneClasse scanneLaClasse(JavaClass classeScannee) {
        UneClasse uneClasse = new UneClasse(classeScannee.getPackageName(), classeScannee.getName());
        classeScannee.getFields().stream().forEach(attribut -> uneClasse.listeDesAttributs.add(scanneUnAttribut(attribut)));
        classeScannee.getMethods().stream().forEach(methode -> uneClasse.listeDesMethodes.add(scanneUneMethode(methode)));
        classeScannee.getAnnotations().stream().forEach(annotation -> uneClasse.listeDesAnnotations.add(scanneUneAnnotation(annotation)));
        return uneClasse;
    }

    private UneAnnotation scanneUneAnnotation(JavaAnnotation annotation) {
        UneAnnotation uneAnnotation = new UneAnnotation(annotation.getType().getName());

        return uneAnnotation;
    }

    private UnAttribut scanneUnAttribut(JavaField attributScanne) {
        UnAttribut unAttribut = new UnAttribut(attributScanne.getName(), attributScanne.getType().getName());
        attributScanne.getAnnotations().stream().forEach(annotation -> unAttribut.listeDesAnnotations.add(scanneUneAnnotation(annotation)));
        return unAttribut;
    }

    private UneMethode scanneUneMethode(JavaMethod methodeScannee) {
        UneMethode uneMethode = new UneMethode(methodeScannee.getName(), methodeScannee.getReturnType().getFullyQualifiedName());
        methodeScannee.getAnnotations().stream().forEach(annotation -> uneMethode.listeDesAnnotations.add(scanneUneAnnotation(annotation)));
        methodeScannee.getParameters().stream().forEach(parametre -> uneMethode.listeDesParametres.add(scanneUnParametre(parametre)));
        return uneMethode;
    }

    private UnParametre scanneUnParametre(JavaParameter parametre) {
        UnParametre unParametre = new UnParametre(parametre.getName(), parametre.getType().getFullyQualifiedName());
        return unParametre;
    }

}
