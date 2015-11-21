package andreotxai.busaodadepressaoz.model;

/**
 * Created by Ândreo on 08/11/2015.
 */
public class Usuario {

    private int idUsuario;
    private String nome;
    private String senha;


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
