package andreotxai.busaodadepressaoz.DAO;

import android.content.Context;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import andreotxai.busaodadepressaoz.DAO.file.DataBaseMainFactory;
import andreotxai.busaodadepressaoz.model.Avalicoes;
import andreotxai.busaodadepressaoz.model.Empresa;
import andreotxai.busaodadepressaoz.model.Horarios;
import andreotxai.busaodadepressaoz.model.Linha;

/**
 * Classe para inserção e pesquisa no banco de dados por avaliações.
 * Created by Batman on 21/11/2015.
 */
public class AvaliacoesDAO implements IModelDAO {
    private Avalicoes avalicoes;
    private Empresa empresa;
    private Linha linha;
    private Horarios horario;
    private String stringDatabase;

    public AvaliacoesDAO() {}

    public AvaliacoesDAO(Avalicoes avalicoes) {
        this.avalicoes = avalicoes;
    }

    private void montaStringDataBase() {
        this.stringDatabase = String.valueOf(this.empresa.getIdEmpresa())
                + " " + String.valueOf(this.avalicoes.getNota())
                + " <[" + this.avalicoes.getComentario() + "]>"
                + " " + String.valueOf(this.linha.getIdLinha())
                + " " + String.valueOf(this.horario.getIdHorario())
                + " " + this.avalicoes.getData();
    }

    public boolean insereDataBase(Context context) {
        this.montaStringDataBase();
        DataBaseMainFactory dbConnection = new DataBaseMainFactory(context);
        dbConnection.setDados(this.stringDatabase);
        return dbConnection.insertData();
    }

    public String lerDataBase(Context context) throws IOException {
        DataBaseMainFactory dbConnection = new DataBaseMainFactory(context);
        return dbConnection.readData();
    }

    public String pegarAvaliacaoBanco(Context context, ArrayList<Integer> linhas) throws IOException {
        DataBaseMainFactory dbConnection = new DataBaseMainFactory(context);
        String linhaAvaliacao = "";
        for (int i = 0; i < linhas.size(); i++) {
            linhaAvaliacao += dbConnection.readData().split("\n")[linhas.get(i)] + "\n";
        }
        return linhaAvaliacao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }
}
