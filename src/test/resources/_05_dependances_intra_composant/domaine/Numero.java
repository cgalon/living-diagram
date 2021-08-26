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
    public final LocalDate dateDeParution;
    private List<Episode> listeDesEpisodes = new ArrayList<>();

    public Numero(String nomDeLaRevue, int numero, LocalDate dateDeParution) {
        this.numeroDansLaSerie = numero;
        this.dateDeParution = dateDeParution;
        this.nomDeLaRevue = nomDeLaRevue;
    }

    public String recupereLaDateDeParutionEnFrancais() {
        return dateDeParution.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void ajouteUnEpisode(Episode unEpisode) {
        listeDesEpisodes.add(unEpisode);
    }

    public int recupereLeNombreDEpisodes() {
        return listeDesEpisodes.size();
    }

    public Episode recupereUnEpisodeParLeNomDuHeros(String nomDuHeros) throws EpisodeNonTrouveException {
        Episode episodeTrouve = listeDesEpisodes.stream().filter(episode -> nomDuHeros.equals(episode.recupereLeHeros())).findFirst().orElseThrow(() -> new EpisodeNonTrouveException(nomDuHeros));
        return episodeTrouve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numero numero = (Numero) o;
        return numeroDansLaSerie == numero.numeroDansLaSerie &&
                Objects.equals(nomDeLaRevue, numero.nomDeLaRevue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nomDeLaRevue, numeroDansLaSerie);
    }
}
