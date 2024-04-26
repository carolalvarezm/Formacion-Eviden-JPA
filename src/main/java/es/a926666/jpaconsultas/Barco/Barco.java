package es.a926666.jpaconsultas.Barco;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.a926666.jpaconsultas.Amarre.Amarre;
import es.a926666.jpaconsultas.Salida.Salida;
import es.a926666.jpaconsultas.Socio.Socio;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Barco {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique=true)
    private String matricula;
    
    @Basic
    private String nombre;

    @OneToOne(mappedBy = "barco", cascade =CascadeType.ALL)
    
    @JsonManagedReference(value="Amarre-barco")
    private Amarre amarre;

    @OneToMany(mappedBy = "barco",cascade = CascadeType.ALL)
    @JsonManagedReference(value="Salidas-Barco")
    private List<Salida> salidas;

    @ManyToOne
    @JoinColumn(name="propietario_id")
    @JsonBackReference(value = "Propietario-Barco")
    private Socio propietario;

    public Barco() {
    }
    
    public Barco(Integer id, String matricula, String nombre) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;

    }

    public Socio getPropietario() {
        return propietario;
    }

    public void setPropietario(Socio propietario) {
        this.propietario = propietario;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Salida> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<Salida> salidas) {
        this.salidas = salidas;
    }

    public Amarre getAmarre() {
        return amarre;
    }

    public void setAmarre(Amarre amarre) {
        this.amarre = amarre;
    }




}
