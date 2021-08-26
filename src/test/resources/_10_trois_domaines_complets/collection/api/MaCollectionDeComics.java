package fr.pe.incub.mescomics.collection.api;

import fr.pe.incub.mescomics.collection.domaine.Collection;
import fr.pe.incub.mescomics.collection.domaine.Comics;
import fr.pe.incub.mescomics.collection.infrastructure.ServiceDeCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaCollectionDeComics {

    ServiceDeCollection serviceDeCollection;

    @Autowired
    public MaCollectionDeComics(ServiceDeCollection serviceDeCollection) {
        this.serviceDeCollection = serviceDeCollection;
    }

    public List<Comics> retourneMaListeDeComicsPourCeTitre(String titre) {
        Collection maCollection = serviceDeCollection.recupererMaCollectionCompleteDeComics();
        return maCollection.recupereLaListeDesComicsDeLaSerie(titre);
    }

}
