package fr.pe.incub.mescomics.listeachat.infrastructure;

import java.util.Objects;

public class NumeroPresent {

    public final String serie;
    public final int numero;

    public NumeroPresent(String serie, int numero) {
        this.serie = serie;
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumeroPresent that = (NumeroPresent) o;
        return numero == that.numero &&
                Objects.equals(serie, that.serie);
    }

    @Override
    public int hashCode() {

        return Objects.hash(serie, numero);
    }
}
