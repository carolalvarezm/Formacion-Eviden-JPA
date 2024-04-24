package es.a926666.jpaconsultas.Salida;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<Salida,Integer>{

    @Query("SELECT s FROM Salida s JOIN Socio so ON so.persona.id=s.patron.id")
    List<Salida> findAllSalidasSocios();

}
