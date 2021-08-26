package fr.pe.incub.mescomics.listeachat.infrastructure;

import fr.pe.incub.mescomics.collection.api.MaCollectionDeComics;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RecuperateurDeComicsDeMaCollection {

    private MaCollectionDeComics maCollectionDeComics;

    @Autowired
    public RecuperateurDeComicsDeMaCollection(MaCollectionDeComics maCollectionDeComics) {
        this.maCollectionDeComics = maCollectionDeComics;
    }

    public List<NumeroPresent> retrouveLesComicsQueJePossedePourCeTitre(String titre) {
        List<Comics> lesComicsDeMaCollection = maCollectionDeComics.retourneMaListeDeComicsPourCeTitre(titre);
        return lesComicsDeMaCollection.stream().map(transformeEnNumeroPresent).collect(Collectors.toList());
    }

    private Function<Comics, NumeroPresent> transformeEnNumeroPresent = comics -> {
        NumeroPresent numero = new NumeroPresent(comics.titreDeLaRevue, comics.numero);
        return numero;
    };
}
