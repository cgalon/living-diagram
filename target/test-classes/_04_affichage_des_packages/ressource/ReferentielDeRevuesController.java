package fr.pe.incub.mescomics.referentiel.ressource;

import fr.pe.incub.mescomics.referentiel.api.ReferentielDeRevues;
import fr.pe.incub.mescomics.referentiel.domaine.Numero;
import fr.pe.incub.mescomics.referentiel.domaine.Revue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Ressource de gestion du référentiel de Comics. Permet d'ajouter ou de supprimer des numéros.
 */
@RestController
@RequestMapping("/revue")
public class ReferentielDeRevuesController {

}
