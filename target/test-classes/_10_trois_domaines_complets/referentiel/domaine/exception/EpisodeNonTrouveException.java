package fr.pe.incub.mescomics.referentiel.domaine.exception;

public class EpisodeNonTrouveException extends Throwable {

    private final String nomDuHeros;

    public EpisodeNonTrouveException(String nomDuHeros) {
        this.nomDuHeros = nomDuHeros;
    }

    @Override
    public String getMessage() {
        return String.format("Episode concernant %1 introuvable.", nomDuHeros);
    }
}
