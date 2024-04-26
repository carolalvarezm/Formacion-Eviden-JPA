package es.a926666.jpaconsultas.Salida;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.a926666.jpaconsultas.Barco.Barco;

import java.sql.Date;


@Repository
public interface SalidaRepository extends JpaRepository<Salida,Integer>{
    //Salidas de los socios
    @Query("SELECT s FROM Salida s JOIN Socio so ON so.persona.id=s.patron.id")
    List<Salida> findAllSalidasSocios();

    //Salidas de un socio
    @Query("SELECT s FROM Salida s JOIN Socio so ON so.persona.id=s.patron.id WHERE s.patron.id = ?1")
    List<Salida> findAllSalidasSociosById(Integer id);

    //Salidas de una fecha específica
    List<Salida> findByFecha(Date fecha);

    // @Query("SELECT b From Salida s Join Barco b on s.barco.id =b.id GROUP BY s.barco.id,b.id Having Count(Distinct s.patron.id)>1")
    // List<Barco> findAllBarcosWithMoreThan2Patrones();
}
