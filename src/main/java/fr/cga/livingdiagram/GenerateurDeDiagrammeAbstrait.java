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

    protected boolean estUnTypeExterne(UnAttribut attribut) {
        boolean estUnTypeExterne = false;
        String typeDeLAttribut = attribut.type;
        if (attribut.type.contains("<")) {
            typeDeLAttribut = StringUtils.substringBetween(typeDeLAttribut, "<", ">");
        }
        if (!typeDeLAttribut.startsWith("fr.pe")) {
            estUnTypeExterne = true;
        }
        return estUnTypeExterne;
    }

    protected boolean estUnTypeJavaDeBase(UnAttribut attribut) {
        List<String> listeDesTypesJavaDeBase = new ArrayList<String>();
        listeDesTypesJavaDeBase.add("boolean");
        listeDesTypesJavaDeBase.add("byte");
        listeDesTypesJavaDeBase.add("char");
        listeDesTypesJavaDeBase.add("double");
        listeDesTypesJavaDeBase.add("float");
        listeDesTypesJavaDeBase.add("int");
        listeDesTypesJavaDeBase.add("long");
        listeDesTypesJavaDeBase.add("short");

        return listeDesTypesJavaDeBase.contains(attribut.type);
    }

}
