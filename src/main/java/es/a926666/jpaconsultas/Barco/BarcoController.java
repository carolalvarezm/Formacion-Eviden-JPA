package es.a926666.jpaconsultas.Barco;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/barcos")
public class BarcoController {
    @Autowired
    private BarcoService barcoService;

    @GetMapping
    public ResponseEntity<?> getAllBarcos() {
        return barcoService.getAllBarcos();
    }
    @PostMapping
    public ResponseEntity<?> createdBarco(@RequestBody BarcoRequest barcoRequest) {
        return barcoService.createBarco(barcoRequest);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBarcoById(@PathVariable Integer id) {
        return barcoService.getBarcoById(id);
    }
    @GetMapping("/{id}/salidas")
    public ResponseEntity<?> getMethodName(@PathVariable Integer id) {
        return barcoService.getAllSalidasById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Integer id,@RequestBody  BarcoRequest barcoRequest) {
        return barcoService.updateBarcoById(id,barcoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBarcoById(@PathVariable Integer id) {
        return barcoService.deleteBarco(id);
    }

}
