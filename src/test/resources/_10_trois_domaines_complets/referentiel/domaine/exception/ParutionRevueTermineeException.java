package fr.pe.incub.mescomics.referentiel.domaine.exception;

public class ParutionRevueTermineeException extends Throwable {

    private final String nomDeLaRevue;

    public ParutionRevueTermineeException(String nomDeLaRevue) {
        this.nomDeLaRevue = nomDeLaRevue;
    }

    @Override
    public String getMessage() {
        return String.format("La revue %1 ne parait plus. Impossible de modifier le nombre de numéro quelle contient.", nomDeLaRevue);
    }
}
