package es.a926666.jpaconsultas.Amarre;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.a926666.jpaconsultas.Barco.Barco;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Amarre 
{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique=true)
    private Integer numero;
    @Column(precision=10, scale=2)
    private BigDecimal cuota;

    @OneToOne
    @JoinColumn(name="barco_id")
    @JsonManagedReference
    private Barco barco;

    public Amarre() {
    }
    public Amarre(Integer id, Integer numero, BigDecimal cuota) {
        this.id = id;
        this.numero = numero;
        this.cuota = cuota;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public BigDecimal getCuota() {
        return cuota;
    }
    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }
    public Barco getBarco() {
        return barco;
    }
    public void setBarco(Barco barco) {
        this.barco = barco;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    
}
