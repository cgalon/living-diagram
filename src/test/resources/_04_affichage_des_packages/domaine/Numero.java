package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.mescomics.referentiel.domaine.exception.EpisodeNonTrouveException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Parution d'une instance de revue à une date précise. Un numéro peut contenir un ou plusieurs épisodes de un ou plusieurs héros.
 */
public class Numero {

    public final String nomDeLaRevue;
    public final int numeroDansLaSerie;
}
