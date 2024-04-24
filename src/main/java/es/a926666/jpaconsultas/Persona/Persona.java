package es.a926666.jpaconsultas.Persona;


import java.util.List;

import es.a926666.jpaconsultas.Salida.Salida;
import es.a926666.jpaconsultas.Socio.Socio;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Persona {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Basic
    private String firstname;
    private String lastname;

    @OneToOne(mappedBy = "persona",cascade = CascadeType.ALL)
    private Socio socio;

    @OneToMany(mappedBy = "patron",cascade = CascadeType.ALL)
    private List<Salida> salidas;

    public Socio getSocio() {
        return socio;
    }
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    public List<Salida> getSalidas() {
        return salidas;
    }
    public void setSalidas(List<Salida> salidas) {
        this.salidas = salidas;
    }
    public Persona() {
    }
    public Persona(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public Integer getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setId(Integer id) {
        this.id = id;
    }


}
