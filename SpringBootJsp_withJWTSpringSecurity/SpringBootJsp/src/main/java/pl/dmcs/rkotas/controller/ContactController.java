package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.rkotas.model.Contact;

@Controller
public class ContactController {

    @RequestMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("message","Simple String from ContactController.");
        Contact newContact = new Contact();
        newContact.setFirstname("John");
        newContact.setEmail("email@email.com");
        model.addAttribute("contact",newContact);
        return "contact";
    }

    @RequestMapping(value = "/addContact.html", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact) {

        System.out.println(contact.getFirstname() + " " + contact.getLastname() +
                " " + contact.getEmail() + " " + contact.getTelephone());

        return "redirect:contact";
    }

}
