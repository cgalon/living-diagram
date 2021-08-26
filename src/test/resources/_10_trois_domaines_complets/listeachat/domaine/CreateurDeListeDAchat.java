package fr.pe.incub.mescomics.listeachat.domaine;

import fr.pe.incub.mescomics.listeachat.infrastructure.NumeroPresent;
import fr.pe.incub.mescomics.listeachat.infrastructure.RecuperateurDeComicsDeMaCollection;
import fr.pe.incub.mescomics.listeachat.infrastructure.RecuperateurDeRevueReferencee;
import fr.pe.incub.mescomics.listeachat.infrastructure.RevueReferencee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreateurDeListeDAchat {

    private RecuperateurDeComicsDeMaCollection recuperateurDeMaCollectionDeComics;

    private RecuperateurDeRevueReferencee recuperateurDeRevueReferencee;

    @Autowired
    public CreateurDeListeDAchat(RecuperateurDeComicsDeMaCollection recuperateurDeMaCollectionDeComics, RecuperateurDeRevueReferencee recuperateurDeRevueReferencee) {
        this.recuperateurDeMaCollectionDeComics = recuperateurDeMaCollectionDeComics;
        this.recuperateurDeRevueReferencee = recuperateurDeRevueReferencee;
    }

    public ListeDAchat creeUneListeDAchatPourUneRevue(String titre) {
        List<NumeroPresent> lesNumerosQueJePossede = recuperateurDeMaCollectionDeComics.retrouveLesComicsQueJePossedePourCeTitre(titre);

        RevueReferencee revue = recuperateurDeRevueReferencee.retrouveLaRevuePourCeTitre(titre);

        ListeDAchat listeDAchatPourCeTitre = new ListeDAchat();
        for (int i = 1; i <= revue.nombreDeNumeros; i++) {
            NumeroPresent numeroAVerifier = new NumeroPresent(titre, i);
            if (!lesNumerosQueJePossede.contains(numeroAVerifier)) {
                NumeroManquant numeroManquant = new NumeroManquant(titre, i, revue.dateDePremiereParution, revue.editeur);
                listeDAchatPourCeTitre.ajouteUnNumeroManquant(numeroManquant);
            }
        }

        return listeDAchatPourCeTitre;
    }

}
