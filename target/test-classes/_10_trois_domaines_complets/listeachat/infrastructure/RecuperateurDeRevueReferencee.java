package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.referentiel.api.ReferentielDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RecuperateurDeRevueReferencee {

    ReferentielDeRevues referentielDeRevues;

    @Autowired
    public RecuperateurDeRevueReferencee(ReferentielDeRevues referentielDeRevues) {
        this.referentielDeRevues = referentielDeRevues;
    }

    public RevueReferencee retrouveLaRevuePourCeTitre(String titre) {

        RevueReferencee revueTrouvee = null;
        Optional<Revue> revue = referentielDeRevues.retrouveLaRevuePourCeTitre(titre);
        if (revue.isPresent()) {
            Optional<Numero> premierNumero = referentielDeRevues.retrouveLePremierNumeroPourCetteRevue(revue.get());
            if (premierNumero.isPresent()) {
                revueTrouvee = new RevueReferencee(revue.get().titre, revue.get().recupereLeNombreDeNumeros(), premierNumero.get().dateDeParution, revue.get().nomDeLEditeur);
            }
        }
        return revueTrouvee;
    }
}
