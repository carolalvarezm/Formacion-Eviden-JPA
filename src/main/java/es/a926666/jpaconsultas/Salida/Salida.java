package es.a926666.jpaconsultas.Salida;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.a926666.jpaconsultas.Barco.Barco;
import es.a926666.jpaconsultas.Persona.Persona;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Salida {
    @Id
    @GeneratedValue
    private Integer id;

    @Basic
    private String destino;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Time hora;

    @ManyToOne
    @JoinColumn(name="barco_id")
    @JsonBackReference(value="Salidas-Barco")
    private Barco barco; 

    @ManyToOne
    @JoinColumn(name="patron_id")
    @JsonBackReference(value="Patron-Salida")
    private Persona patron;

    public Salida() {
    }

    public Salida(String destino, Date fecha, Time hora) {
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
    }

    
    public Integer getId() {
        return id;
    }
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public Persona getPatron() {
        return patron;
    }

    public void setPatron(Persona patron) {
        this.patron = patron;
    }

    public void setId(Integer id) {
        this.id = id;
    }  
    

}
