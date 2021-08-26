package fr.pe.incub.mescomics.referentiel.domaine;

import java.util.Objects;

/**
 * Morceau d'histoire d'un héros paru dans un numéro d'une revue.
 */
public class Episode{

    private final String heros;
    private final String titre;
    private final String dessinateur;

    public Episode(String heros, String nomDeLEpisode, String dessinateur) {
        this.heros = heros;
        this.titre = nomDeLEpisode;
        this.dessinateur = dessinateur;
    }

    public String recupereLeHeros() {
        return heros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return Objects.equals(heros, episode.heros) &&
                Objects.equals(titre, episode.titre) &&
                Objects.equals(dessinateur, episode.dessinateur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heros, titre, dessinateur);
    }
}
