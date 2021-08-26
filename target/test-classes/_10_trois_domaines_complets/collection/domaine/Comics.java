package fr.pe.incub.mescomics.collection.domaine;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Le numéro d'une revue détenu par l'utilisateur.
 */
@Entity
public class Comics {

    @Id
    public final String titreDeLaRevue;
    public final int numero;
    public int valeurEnEuros;

    public Comics(String nomDeLaRevue, int numero) {
        this.titreDeLaRevue = nomDeLaRevue;
        this.numero = numero;
        this.valeurEnEuros = 0;
    }

    public Comics(String nomDeLaRevue, int numero, int valeurEnEuros) {
        this.titreDeLaRevue = nomDeLaRevue;
        this.numero = numero;
        this.valeurEnEuros = valeurEnEuros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return numero == comics.numero &&
                Objects.equals(titreDeLaRevue, comics.titreDeLaRevue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titreDeLaRevue, numero);
    }

    public void modifieLaValeurEnEuros(int nouvelleValeur) {
        valeurEnEuros = nouvelleValeur;
    }
}
