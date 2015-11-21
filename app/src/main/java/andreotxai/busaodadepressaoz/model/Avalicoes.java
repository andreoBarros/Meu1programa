package andreotxai.busaodadepressaoz.model;

/**
 * Classe que guardará os valores da Avaliação que o usuário criar.
 *
 * Created by Ândreo on 08/11/2015.
 */
public class Avalicoes {

    private int idAvaliacao;
    private int idRelLinhaHorarios;
    private int idUsuario;
    private double nota;
    private String comentario;

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getIdRelLinhaHorarios() {
        return idRelLinhaHorarios;
    }

    public void setIdRelLinhaHorarios(int idRelLinhaHorarios) {
        this.idRelLinhaHorarios = idRelLinhaHorarios;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
