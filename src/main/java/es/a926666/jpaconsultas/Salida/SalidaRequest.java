package es.a926666.jpaconsultas.Salida;

import java.sql.Date;
import java.sql.Time;

public class SalidaRequest {

    private String destino;
    private Date fecha;
    private String hora;
    private Integer idPatron;
    private Integer idBarco;

    public SalidaRequest(String destino, Date fecha, String hora, Integer idPatron, Integer idBarco) {
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.idPatron = idPatron;
        this.idBarco = idBarco;
    }
    public String getDestino() {
        return destino;
    }
    public Date getFecha() {
        return fecha;
    }
    public Time getHora() {
        return Time.valueOf(hora);
    }
    public Integer getIdPatron() {
        return idPatron;
    }
    public Integer getIdBarco() {
        return idBarco;
    }
    
}
