package es.a926666.jpaconsultas.Socio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/api/v1/socios")
public class SocioController {
    
    @Autowired
    private SocioService socioService;


    @PostMapping
    public ResponseEntity<?> createSocio(@RequestBody Integer id)
    {
        return socioService.createSocio(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllSocios() {
        return socioService.getAllSocios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSocioById(@PathVariable Integer id) {
        return socioService.getSocioById(id);
    }
    @GetMapping("{id}/barcos")
    public ResponseEntity<?> getBarcosBySocioId(@PathVariable Integer id) {
        return socioService.getBarcosBySocioId(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSocioById(@PathVariable Integer id,@RequestBody Socio socio) {
        return socioService.updateSocioById(id,socio);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSocio(@PathVariable Integer id) {
        return socioService.deleteSocio(id);
    }
}
