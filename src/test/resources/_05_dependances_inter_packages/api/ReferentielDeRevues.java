package fr.pe.incub.mescomics.referentiel.api;

import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import fr.pe.incub.mescomics.referentiel.infrastructure.ServiceDeRevue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReferentielDeRevues {

    ServiceDeRevue serviceDeRevue;

    @Autowired
    public ReferentielDeRevues(ServiceDeRevue serviceDeRevue) {
        this.serviceDeRevue = serviceDeRevue;
    }

    public Optional<Revue> retrouveLaRevuePourCeTitre(String titre) {
        return Optional.ofNullable(serviceDeRevue.retrouveLaRevuePourCeTitre(titre));
    }

    public Optional<Numero> retrouveLePremierNumeroPourCetteRevue(Revue revue) {
        return Optional.ofNullable(serviceDeRevue.retrouveLePremierNumeroPourCetteRevue(revue));
    }
}
