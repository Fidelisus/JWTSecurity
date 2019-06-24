package pl.dmcs.rkotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.rkotas.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    Contact findById(long id);
}


