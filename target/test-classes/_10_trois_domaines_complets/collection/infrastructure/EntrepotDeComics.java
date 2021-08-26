package fr.pe.incub.mescomics.collection.infrastructure;

import fr.pe.incub.mescomics.collection.domaine.Comics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepotDeComics extends CrudRepository<Comics, Integer> {
}
