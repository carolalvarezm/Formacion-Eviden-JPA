package es.a926666.jpaconsultas.Amarre;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class AmarreService {
    @Autowired
    public AmarreRepository amarreRepository;
    @Autowired
    EntityManager em;

    public AmarreService(AmarreRepository amarreRepository) {
        this.amarreRepository = amarreRepository;
    }
    
    public ResponseEntity<?> createAmarre(Amarre amarre){
        try{
            amarreRepository.save(amarre);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        
    }

    public ResponseEntity<?> getAllAmarres(){
        List<Amarre> amarres= amarreRepository.findAll();
        if(amarres.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(amarreRepository.findAll());
        }
    }

    public ResponseEntity<?> deleteAmarre(Integer id){
        try{
            amarreRepository.deleteById(id);
            return ResponseEntity.ok("Se ha eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?>  updateAmarreById(Integer id,Amarre amarreNew) {
        try{
            Optional<Amarre> amarre= amarreRepository.findById(id);
            if(amarre!=null){
                amarreNew.setId(id);
                amarreRepository.save(amarreNew);
                return ResponseEntity.ok(amarreNew);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
    public List<Amarre> findAllNullAmarres(){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Amarre> cq= cb.createQuery(Amarre.class);
        Root<Amarre> amarre= cq.from(Amarre.class);
        Predicate nullbarco = cb.isNull(amarre.get("barco"));
        cq.where(nullbarco);
        TypedQuery<Amarre> query = em.createQuery(cq);
        return query.getResultList();
        
    }
    public ResponseEntity<?> getAllNullAmarres() {
        List<Amarre> amarres= this.findAllNullAmarres();
        
        if(amarres.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el recurso");
        }
        else{
            return ResponseEntity.ok(amarres);
        }
    }
}
