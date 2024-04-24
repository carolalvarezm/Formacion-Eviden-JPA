package es.a926666.jpaconsultas.Barco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.a926666.jpaconsultas.Amarre.Amarre;
import es.a926666.jpaconsultas.Socio.Socio;
import es.a926666.jpaconsultas.Amarre.AmarreRepository;
import es.a926666.jpaconsultas.Salida.Salida;
import es.a926666.jpaconsultas.Socio.SocioRepository;

@Service
public class BarcoService {
    @Autowired
    private BarcoRepository barcoRepository;
    @Autowired
    private AmarreRepository amarreRepository;
    @Autowired
    private SocioRepository socioRepository;


    public BarcoService(BarcoRepository barcoRepository) {
        this.barcoRepository = barcoRepository;
    }
    
    public ResponseEntity<?> createBarco(BarcoRequest barcoRequest){
        try{
            Barco barco= new Barco();
            Optional<Amarre> amarre = amarreRepository.findById(barcoRequest.getIdAmarre());
            Optional<Socio> propietario =socioRepository.findById(barcoRequest.getIdPropietario());
            if(amarre.isPresent() && propietario.isPresent()){
                barco.setMatricula(barcoRequest.getMatricula());
                barco.setNombre(barcoRequest.getNombre());
                barco.setAmarre(amarre.get());
                barco.setPropietario(propietario.get());
                barcoRepository.save(barco);

                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el punto de amarre o el propietario indicado");
            }

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        
        
    }

    public ResponseEntity<?> deleteBarco(Integer id) {
       
        try{
            barcoRepository.deleteById(id);
            return ResponseEntity.ok("Se ha eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> getAllBarcos() {
        
        List<Barco> barcos= barcoRepository.findAll();;
        if(barcos.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(barcos);
        }
    }

    public ResponseEntity<?> updateBarcoById(Integer id,BarcoRequest barcoRequest) {
        try{
            Barco barco= new Barco();
            Optional<Amarre> amarre = amarreRepository.findById(barcoRequest.getIdAmarre());
            Optional<Socio> propietario =socioRepository.findById(barcoRequest.getIdPropietario());
            if(amarre.isPresent() && propietario.isPresent()){
                barco.setId(id);
                barco.setMatricula(barcoRequest.getMatricula());
                barco.setNombre(barcoRequest.getNombre());
                barco.setAmarre(amarre.get());
                barco.setPropietario(propietario.get());
                barcoRepository.save(barco);

                return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado correctamente");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el punto de amarre o el propietario indicado");
            }

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


    public ResponseEntity<?> getBarcoById(Integer id) {
        Optional<Barco> barco= barcoRepository.findById(id);
        if(barco!=null){
            return ResponseEntity.ok(barco.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
    }

    public ResponseEntity<?> getAllSalidasById(Integer id) {
        Optional<Barco> barco= barcoRepository.findById(id);
        List<Salida> salidas = barco.get().getSalidas();
        if(salidas.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(salidas);
        }
    }
}
