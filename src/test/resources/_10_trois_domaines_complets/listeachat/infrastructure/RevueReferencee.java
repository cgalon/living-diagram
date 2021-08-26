package fr.pe.incub.mescomics.listeachat.infrastructure;

import java.time.LocalDate;
import java.util.Objects;

public class RevueReferencee {

    public final String serie;
    public final int nombreDeNumeros;
    public final LocalDate dateDePremiereParution;
    public final String editeur;

    public RevueReferencee(String serie, int nombreDeNumeros, LocalDate dateDePremiereParution, String editeur) {
        this.serie = serie;
        this.nombreDeNumeros = nombreDeNumeros;
        this.dateDePremiereParution = dateDePremiereParution;
        this.editeur = editeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RevueReferencee that = (RevueReferencee) o;
        return nombreDeNumeros == that.nombreDeNumeros &&
                Objects.equals(serie, that.serie) &&
                Objects.equals(editeur, that.editeur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(serie, nombreDeNumeros, editeur);
    }
}
