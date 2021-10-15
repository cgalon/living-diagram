package fr.cga.livingdiagram;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class GenerateurDeDiagrammeAbstrait implements GenerateurDeDiagramme {

    protected boolean estUnAttributAPrendreEnCompte(UnAttribut attribut) {
        boolean estAPrendreEnCompte = true;
        if (estUnTypeJavaDeBase(attribut)) {
            estAPrendreEnCompte = false;
        }
        else if (estUnTypeExterne(attribut)) {
            estAPrendreEnCompte = false;
        }
        return estAPrendreEnCompte;
    }

    protected boolean estUnImportAPrendreEnCompte(String unImport) {
        boolean estAPrendreEnCompte = true;
        if (estUnTypeJavaDeBase(unImport)) {
            estAPrendreEnCompte = false;
        }
        else if (estUnTypeExterne(unImport)) {
            estAPrendreEnCompte = false;
        }
        return estAPrendreEnCompte;
    }

    protected boolean estUnTypeExterne(UnAttribut attribut) {
        return estUnTypeExterne(attribut.type);
    }

    protected boolean estUnTypeExterne(String unType) {
        boolean estUnTypeExterne = false;
        if (unType.contains("<")) {
            unType = StringUtils.substringBetween(unType, "<", ">");
        }
        if (!unType.startsWith("fr.pe")) {
            estUnTypeExterne = true;
        }
        return estUnTypeExterne;
    }

    protected boolean estUnTypeJavaDeBase(UnAttribut attribut) {
        return estUnTypeJavaDeBase(attribut.type);
    }

    protected boolean estUnTypeJavaDeBase(String unType) {
        List<String> listeDesTypesJavaDeBase = new ArrayList<String>();
        listeDesTypesJavaDeBase.add("boolean");
        listeDesTypesJavaDeBase.add("byte");
        listeDesTypesJavaDeBase.add("char");
        listeDesTypesJavaDeBase.add("double");
        listeDesTypesJavaDeBase.add("float");
        listeDesTypesJavaDeBase.add("int");
        listeDesTypesJavaDeBase.add("long");
        listeDesTypesJavaDeBase.add("short");

        return listeDesTypesJavaDeBase.contains(unType);
    }

}
