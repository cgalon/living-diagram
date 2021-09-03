package fr.pe.incub.mescomics.referentiel.domaine;

import java.beans.BeanProperty;
import java.util.Objects;

/**
 * Morceau d'histoire d'un héros paru dans un numéro d'une revue.
 */
public class Episode {

    @BeanProperty
    private final String heros;
    private final String titre;
    private final String dessinateur;

}
