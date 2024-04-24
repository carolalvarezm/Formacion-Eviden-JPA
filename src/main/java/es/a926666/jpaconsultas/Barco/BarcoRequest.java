package es.a926666.jpaconsultas.Barco;

public class BarcoRequest {

    private String matricula;
    private String nombre;
    private Integer idAmarre;
    private Integer idPropietario;
    public BarcoRequest(String matricula, String nombre, Integer idAmarre, Integer idPropietario) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.idAmarre = idAmarre;
        this.idPropietario = idPropietario;
    }
    public String getMatricula() {
        return matricula;
    }
    public String getNombre() {
        return nombre;
    }
    public Integer getIdAmarre() {
        return idAmarre;
    }
    public Integer getIdPropietario() {
        return idPropietario;
    }
    

}
