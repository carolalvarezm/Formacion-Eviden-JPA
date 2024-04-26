package es.a926666.jpaconsultas.Salida;


import java.util.Optional;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;

import es.a926666.jpaconsultas.Barco.Barco;
import es.a926666.jpaconsultas.Barco.BarcoRepository;
import es.a926666.jpaconsultas.Barco.QBarco;
import es.a926666.jpaconsultas.Persona.Persona;
import es.a926666.jpaconsultas.Persona.PersonaRepository;
import jakarta.persistence.EntityManager;


@Service
public class SalidaService {
    @Autowired
    private EntityManager em;
    @Autowired
    private SalidaRepository salidaRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private BarcoRepository barcoRepository;

    public SalidaService(SalidaRepository salidaRepository) {
        this.salidaRepository = salidaRepository;
    }
    
    public ResponseEntity<?> createSalida(SalidaRequest salidaRequest){
        try{
            Optional<Persona> patron= personaRepository.findById(salidaRequest.getIdPatron());
            Optional<Barco> barco= barcoRepository.findById(salidaRequest.getIdBarco());
            Salida salida = new Salida();
            salida.setBarco(barco.get());
            salida.setPatron(patron.get());
            salida.setFecha(salidaRequest.getFecha());
            salida.setHora(salidaRequest.getHora());
            salida.setDestino(salidaRequest.getDestino());

            if(patron.isPresent() && barco.isPresent() ){
                salida.setPatron(patron.get());
                salida.setBarco(barco.get());
                salidaRepository.save(salida);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El patrón o el barco no existe");
            }
            
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
       
    }
    public ResponseEntity<?> getAllSalidas(){
        List<Salida> salidas= salidaRepository.findAll();
    
        if(salidas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(salidas);
        }
    }
    public ResponseEntity<?> deleteSalida(Integer id){
        salidaRepository.deleteById(id);
        return ResponseEntity.ok("Se ha eliminado correctamente");
    }

    public ResponseEntity<?> UpdateSalidaById(Integer id, SalidaRequest salidaRequest) {
        try{
            Optional<Persona> patron= personaRepository.findById(salidaRequest.getIdPatron());
            Optional<Barco> barco= barcoRepository.findById(salidaRequest.getIdBarco());
            Salida salida = new Salida();
            salida.setId(id);
            salida.setBarco(barco.get());
            salida.setPatron(patron.get());
            salida.setFecha(salidaRequest.getFecha());
            salida.setHora(salidaRequest.getHora());
            salida.setDestino(salidaRequest.getDestino());

            if(patron.isPresent() && barco.isPresent() ){
                salida.setPatron(patron.get());
                salida.setBarco(barco.get());
                salidaRepository.save(salida);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El patrón o el barco no existe");
            }
            
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    public ResponseEntity<?> getAllSalidasSocio() {
        List<Salida> salidas= salidaRepository.findAllSalidasSocios();
    
        if(salidas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(salidas);
        }
    }

    public ResponseEntity<?> getAllSalidasSocioById(Integer id) {
        List<Salida> salidas= salidaRepository.findAllSalidasSociosById(id);
    
        if(salidas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(salidas);
        }
    }

    public ResponseEntity<?> getSalidasByFecha(Date fecha) {
        List<Salida> salidas= salidaRepository.findByFecha(fecha);
    
        if(salidas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(salidas);
        }
    }

    public List<Barco> findAllBarcosWithMoreThan2Patrones(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
        QBarco barco= QBarco.barco;
        QSalida salida=QSalida.salida;
        List<Barco> barcos = queryFactory.select(barco)
        .from(salida)
        .innerJoin(salida.barco,barco)
        .groupBy(barco.id)
        .having(salida.patron.countDistinct().gt(1))
        .fetch(); 
        return barcos;
    }

    public ResponseEntity<?> getBarcosWithMoreThan2Patrones() {
        //List<Barco> barcos= salidaRepository.findAllBarcosWithMoreThan2Patrones();
        List<Barco> barcos= this.findAllBarcosWithMoreThan2Patrones();
        if(barcos.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(barcos);
        }
    }

    
}
