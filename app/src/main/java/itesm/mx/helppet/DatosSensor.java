package itesm.mx.helppet;

import java.util.Date;

/**
 * Created by beeme on 24/04/2016.
 */
public class DatosSensor {
    private int pasos;
    private Date fecha;

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "DatosSensor{" +
                "pasos=" + pasos +
                ", fecha=" + fecha +
                '}';
    }
}
