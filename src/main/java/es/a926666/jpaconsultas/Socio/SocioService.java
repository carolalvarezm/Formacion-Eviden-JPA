package es.a926666.jpaconsultas.Socio;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.jpaconsultas.Barco.Barco;
import es.a926666.jpaconsultas.Barco.BarcoRepository;
import es.a926666.jpaconsultas.Persona.Persona;
import es.a926666.jpaconsultas.Persona.PersonaRepository;


@Service
public class SocioService {
    @Autowired
    public SocioRepository socioRepository;
    @Autowired
    public PersonaRepository personaRepository;
    @Autowired
    public BarcoRepository barcoRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }
    

    public ResponseEntity<?> createSocio(Integer idPersona){
        Optional<Persona> persona= personaRepository.findById(idPersona);
        Socio socio= new Socio();
        try{
            if(persona.isPresent()){
                socio.setPersona(persona.get());
                socioRepository.save(socio);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso persona");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        
    }

    public ResponseEntity<?> getAllSocios(){
        
        List<Socio> socios= socioRepository.findAll();
    
        if(socios.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(socios);
        }
    }

    public ResponseEntity<?> deleteSocio(Integer id){
        try{
            socioRepository.deleteById(id);
            return ResponseEntity.ok("Se ha eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> updateSocioById(Integer id, Socio socioNew) {
        try{
            Optional<Socio> socio = socioRepository.findById(id);
            if(socio!=null){
                socioNew.setId(id);
                socioRepository.save(socioNew);
                return ResponseEntity.ok(socioNew);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


    public ResponseEntity<?> getSocioById(Integer id) {
        Optional<Socio> socio= socioRepository.findById(id);
        if(socio!=null){
            return ResponseEntity.ok(socio.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }


    public ResponseEntity<?> getBarcosBySocioId(Integer id) {
        Optional<Socio> socio= socioRepository.findById(id);
        List<Barco> barcos = socio.get().getBarcos();
        if(barcos.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(barcos);
        }
    }
}
