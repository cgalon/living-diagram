package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.referentiel.domaine.exception.EpisodeNonTrouveException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class NumeroDAO {

    @Id
    @GeneratedValue
    public long id;
    public final String nomDeLaRevue;
    public final int numeroDansLaSerie;
    public final LocalDate dateDeParution;
    @OneToMany(targetEntity=EpisodeDAO.class, mappedBy="id")
    private List<EpisodeDAO> listeDesEpisodes = new ArrayList<>();

    public NumeroDAO(String nomDeLaRevue, int numero, LocalDate dateDeParution) {
        this.numeroDansLaSerie = numero;
        this.dateDeParution = dateDeParution;
        this.nomDeLaRevue = nomDeLaRevue;
    }

    public String recupereLaDateDeParutionEnFrancais() {
        return dateDeParution.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void ajouteUnEpisode(EpisodeDAO unEpisode) {
        listeDesEpisodes.add(unEpisode);
    }

    public int recupereLeNombreDEpisodes() {
        return listeDesEpisodes.size();
    }

    public EpisodeDAO recupereUnEpisodeParLeNomDuHeros(String nomDuHeros) throws EpisodeNonTrouveException {
        EpisodeDAO episodeTrouve = listeDesEpisodes.stream().filter(episode -> nomDuHeros.equals(episode.recupereLeHeros())).findFirst().orElseThrow(() -> new EpisodeNonTrouveException(nomDuHeros));
        return episodeTrouve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumeroDAO numero = (NumeroDAO) o;
        return numeroDansLaSerie == numero.numeroDansLaSerie &&
                Objects.equals(nomDeLaRevue, numero.nomDeLaRevue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nomDeLaRevue, numeroDansLaSerie);
    }
}
