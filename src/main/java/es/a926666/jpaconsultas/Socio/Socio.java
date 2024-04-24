package es.a926666.jpaconsultas.Socio;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.a926666.jpaconsultas.Barco.Barco;
import es.a926666.jpaconsultas.Persona.Persona;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Socio {
    @Id
    @GeneratedValue
    private Integer id;
    
    @OneToOne
    @JoinColumn(name="persona_id")
    @JsonBackReference
    private Persona persona;

    @OneToMany(mappedBy="propietario",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Barco> barcos;

    public Socio() {
        super();
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }
    
    
    
}
