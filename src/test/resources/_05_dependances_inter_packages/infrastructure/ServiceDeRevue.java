package fr.pe.incub.mescomics.referentiel.infrastructure;

import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDeRevue {

    EntrepotDeRevues entrepotDeRevues;

    EntrepotDeNumeros entrepotDeNumeros;

    @Autowired
    public ServiceDeRevue(EntrepotDeRevues entrepotDeRevues, EntrepotDeNumeros entrepotDeNumeros) {
        this.entrepotDeRevues = entrepotDeRevues;
        this.entrepotDeNumeros = entrepotDeNumeros;
    }

    public Revue retrouveLaRevuePourCeTitre(String titre) {
        String identifiantDeLaRevue = retrouveLIdentifiantDuneRevueParSonTitre(titre);
        RevueDAO revueDAO = entrepotDeRevues.findById(identifiantDeLaRevue).get();
        return new Revue(revueDAO.titre, revueDAO.recupereLeNombreDeNumeros(), revueDAO.nomDeLEditeur, revueDAO.encoreDesNumerosAParaitre());
    }

    public Numero retrouveLePremierNumeroPourCetteRevue(Revue revue) {
        String identifiantDeLaRevue = retrouveLIdentifiantDuneRevueParSonTitre(revue.titre);
        NumeroDAO numeroDAO = entrepotDeNumeros.findById(identifiantDeLaRevue).get();
        return new Numero(numeroDAO.nomDeLaRevue, numeroDAO.numeroDansLaSerie, numeroDAO.dateDeParution);
    }

    private String retrouveLIdentifiantDuneRevueParSonTitre(String titre) {
        return titre + "-id";
    }
}
