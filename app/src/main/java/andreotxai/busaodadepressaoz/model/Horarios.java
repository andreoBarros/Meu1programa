package andreotxai.busaodadepressaoz.model;

/**
 * Created by Ândreo on 08/11/2015.
 */
public class Horarios {

    int idHorario;
    String horario;

    public Horarios(int idHorario, String horario) {
        this.idHorario = idHorario;
        this.horario = horario;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
