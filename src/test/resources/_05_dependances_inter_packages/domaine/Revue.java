package fr.pe.incub.mescomics.referentiel.domaine;

import fr.pe.incub.mescomics.referentiel.domaine.exception.ParutionRevueTermineeException;
import java.util.Objects;

/**
 * Une parution sortant régulièrement (généralement mensuellement) et relatant les aventures de un ou plusieurs héros.
 */
public class Revue {
    public final String titre;
    private int nombreDeNumeros;
    public final String nomDeLEditeur;
    private boolean serieEnCours;

    public Revue(String titre, int nombreDeNumeros, String editeur, boolean serieEnCours) {
        this.titre = titre;
        this.nombreDeNumeros = nombreDeNumeros;
        this.nomDeLEditeur = editeur;
        this.serieEnCours = serieEnCours;
    }

    public int recupereLeNombreDeNumeros() {
        return nombreDeNumeros;
    }

    public void modifieLeNombreDeNumeros(int nombreDeNumeros) throws ParutionRevueTermineeException {
        if (serieEnCours) {
            this.nombreDeNumeros = nombreDeNumeros;
        }
        else {
            throw new ParutionRevueTermineeException(titre);
        }
    }

    public boolean encoreDesNumerosAParaitre() {
        return serieEnCours;
    }

    public void termineLaSerie() {
        serieEnCours = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revue revue = (Revue) o;
        return nombreDeNumeros == revue.nombreDeNumeros &&
                Objects.equals(titre, revue.titre) &&
                Objects.equals(nomDeLEditeur, revue.nomDeLEditeur);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titre, nombreDeNumeros, nomDeLEditeur);
    }
}