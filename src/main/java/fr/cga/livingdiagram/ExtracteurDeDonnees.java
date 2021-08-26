package fr.cga.livingdiagram;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;

public class ExtracteurDeDonnees {

    private final String cheminDuDossierDeSourcesAScanner;

    public ExtracteurDeDonnees(String cheminDuDossierDeSourcesAScanner) {
        this.cheminDuDossierDeSourcesAScanner = cheminDuDossierDeSourcesAScanner;
    }

    public Collection<UneClasse> retrouveLaDescriptionDesClassesDuPackage() {
        File repertoireDesSources = new File(cheminDuDossierDeSourcesAScanner);
        return parcourtLesFichiersEtCreeLesClassesCorrespondantes(repertoireDesSources);
    }

    private Collection<UneClasse> parcourtLesFichiersEtCreeLesClassesCorrespondantes(File dossierDeSources) {
        JavaProjectBuilder analyseur = new JavaProjectBuilder();
        Collection<UneClasse> lesClassesScannees = new ArrayList<>();

        analyseur.addSourceTree(dossierDeSources);
        analyseur.getClasses().stream().forEach(classe -> lesClassesScannees.add(analyseLaClasse(classe)));
        return lesClassesScannees;
    }

    private UneClasse analyseLaClasse(JavaClass classeScannee) {
        UneClasse uneClasse = new UneClasse(classeScannee.getPackageName(), classeScannee.getName());
        classeScannee.getFields().stream().forEach(attribut -> uneClasse.listeDesAttributs.add(analyseUnAttribut(attribut)));
        classeScannee.getMethods().stream().forEach(methode -> uneClasse.listeDesMethodes.add(analyseUneMethode(methode)));

        return uneClasse;
    }

    private UnAttribut analyseUnAttribut(JavaField attributScanne) {
        UnAttribut unAttribut = new UnAttribut(attributScanne.getName(), attributScanne.getType().getGenericFullyQualifiedName());
        attributScanne.getAnnotations().stream().forEach(annotation -> unAttribut.listeDesAnnotations.add(analyseUneAnnotation(annotation)));
        return unAttribut;
    }

    private UneMethode analyseUneMethode(JavaMethod methodeScannee) {
        UneMethode uneMethode = new UneMethode(methodeScannee.getName(), methodeScannee.getReturnType().getFullyQualifiedName());
        methodeScannee.getAnnotations().stream().forEach(annotation -> uneMethode.listeDesAnnotations.add(analyseUneAnnotation(annotation)));
        return uneMethode;
    }

    private UneAnnotation analyseUneAnnotation(JavaAnnotation annotation) {
        UneAnnotation uneAnnotation = new UneAnnotation(annotation.getType().getName());
        return uneAnnotation;
    }


}
