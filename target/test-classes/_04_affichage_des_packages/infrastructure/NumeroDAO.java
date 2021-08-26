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
}
