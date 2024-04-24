package es.a926666.jpaconsultas.Persona;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.jpaconsultas.Salida.Salida;


@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public ResponseEntity<?> createPersona(Persona persona){
        try{
            personaRepository.save(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        
    }
    
    public ResponseEntity<?> deletePersona(Integer id) {

        try{
            personaRepository.deleteById(id);
            return ResponseEntity.ok("Se ha eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
     }

    public ResponseEntity<?> getAllPersonas() {
        List<Persona> personas= personaRepository.findAll();
        if(personas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(personaRepository.findAll());
        }
        
    }

    public ResponseEntity<?> getPersonaById(Integer id) {
        Optional<Persona> persona= personaRepository.findById(id);
        if(persona!=null){
            return ResponseEntity.ok(persona);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        
    }

    public ResponseEntity<?> updatePersonaById(Integer id,Persona personaNew) {
        try{
            Optional<Persona> persona= personaRepository.findById(id);
            if(persona!=null){
                personaNew.setId(id);
                personaRepository.save(personaNew);
                return ResponseEntity.ok(personaNew);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> getAllSalidasById(Integer id) {
        Optional<Persona> patron= personaRepository.findById(id);
        List<Salida> salidas = patron.get().getSalidas();
        if(salidas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(salidas);
        }
    }

}
