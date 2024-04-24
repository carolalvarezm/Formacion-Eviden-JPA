package es.a926666.jpaconsultas.Amarre;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/amarres")
public class AmarreController {
    @Autowired
    private AmarreService amarreService;

    @GetMapping
    public ResponseEntity<?> getAllAmarres() {
        return amarreService.getAllAmarres();
    }
    @PostMapping
    public ResponseEntity<?> createdAmarre(@RequestBody Amarre amarre) {
        return amarreService.createAmarre(amarre);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>  updateAmarreById(@PathVariable Integer id, @RequestBody Amarre amarre) {
        return amarreService.updateAmarreById(id,amarre);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAmarre(@PathVariable Integer id) {
        return amarreService.deleteAmarre(id);
    }
}
