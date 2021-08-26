package fr.pe.incub.mescomics.referentiel.infrastructure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class EpisodeDAO {
    @Id
    @GeneratedValue
    private long id;
    private final String heros;
    private final String titre;
    private final String dessinateur;

    public EpisodeDAO(String heros, String nomDeLEpisode, String dessinateur) {
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
        EpisodeDAO episode = (EpisodeDAO) o;
        return Objects.equals(heros, episode.heros) &&
                Objects.equals(titre, episode.titre) &&
                Objects.equals(dessinateur, episode.dessinateur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(heros, titre, dessinateur);
    }
}
