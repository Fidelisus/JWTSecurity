package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.rkotas.model.Contact;
import pl.dmcs.rkotas.repository.ContactRepository;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/restApi/contacts")
public class ContactRESTController {

    private ContactRepository contactRepository;

    @Autowired
    public ContactRESTController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method = RequestMethod.GET/*, produces = "application/xml"*/)
    //@GetMapping
    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    //@PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Contact> deleteContact (@PathVariable("id") long id) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            System.out.println("Contact not found!");
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }

        contactRepository.deleteById(id);
        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    //@PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable("id") long id) {
        contact.setId(id);
        contactRepository.save(contact);
        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
    //@PatchMapping("/{id}")
    public ResponseEntity<Contact> updatePartOfContact(@RequestBody Map<String, Object> updates, @PathVariable("id") long id) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            System.out.println("Contact not found!");
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(contact,updates);
        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }

    private void partialUpdate(Contact contact, Map<String, Object> updates) {
        if (updates.containsKey("firstname")) {
            contact.setFirstname((String) updates.get("firstname"));
        }
        if (updates.containsKey("lastname")) {
            contact.setLastname((String) updates.get("lastname"));
        }
        if (updates.containsKey("email")) {
            contact.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("telephone")) {
            contact.setTelephone((String) updates.get("telephone"));
        }
        contactRepository.save(contact);
    }

}


