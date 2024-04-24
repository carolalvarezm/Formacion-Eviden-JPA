package es.a926666.jpaconsultas.Salida;

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
@RequestMapping("/api/v1/salidas")
public class SalidaController {

        @Autowired
        private SalidaService salidaService;
    
        @PostMapping
        public ResponseEntity<?> createSalida(@RequestBody SalidaRequest salidaRequest) {
            return salidaService.createSalida(salidaRequest);
        }
        @GetMapping
        public ResponseEntity<?> getAllSalidas() {
            return salidaService.getAllSalidas();
        }
        @GetMapping("/socios")
        public ResponseEntity<?> getAllSalidasSocio() {
            return salidaService.getAllSalidasSocio();
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<?> updateSalidaById(@PathVariable Integer id,@RequestBody SalidaRequest salidaRequest) {
            return salidaService.UpdateSalidaById(id,salidaRequest);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteSalida(@PathVariable Integer id) {
            return salidaService.deleteSalida(id);
        }
    }
    