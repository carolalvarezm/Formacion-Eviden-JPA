package es.a926666.jpaconsultas.Persona;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/personas")
    public ResponseEntity<?> createPersonas(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }
    @GetMapping("/personas")
    public ResponseEntity<?> getAllPersonas() {
        return personaService.getAllPersonas();
    }
    @GetMapping("/personas/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable Integer id) {
        return personaService.getPersonaById(id);
    }
    @GetMapping("personas/{id}/salidas")
    public ResponseEntity<?> getMethodName(@PathVariable Integer id) {
        return personaService.getAllSalidasById(id);
    }
    @PutMapping("/personas/{id}")
    public ResponseEntity<?> updatePersonaById(@PathVariable Integer id, @RequestBody Persona persona) {
        return personaService.updatePersonaById(id,persona);
    }
    @DeleteMapping("personas/{id}")
    public ResponseEntity<?> deletePersonas(@PathVariable Integer id) {
        return personaService.deletePersona(id);
    }
}
