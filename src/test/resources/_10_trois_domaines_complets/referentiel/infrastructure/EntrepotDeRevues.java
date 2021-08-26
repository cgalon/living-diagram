package fr.pe.incub.mescomics.referentiel.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepotDeRevues extends CrudRepository<RevueDAO, String> {

}