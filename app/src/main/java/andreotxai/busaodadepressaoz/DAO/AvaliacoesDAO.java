package andreotxai.busaodadepressaoz.DAO;

import android.content.Context;

import java.io.IOException;

import andreotxai.busaodadepressaoz.DAO.file.DataBaseMainFactory;
import andreotxai.busaodadepressaoz.model.Avalicoes;

/**
 * Classe para inserção e pesquisa no banco de dados por avaliações.
 * Created by Batman on 21/11/2015.
 */
public class AvaliacoesDAO implements IModelDAO {
    private Avalicoes avalicoes;
    private String stringDatabase;

    public AvaliacoesDAO() {}

    public AvaliacoesDAO(Avalicoes avalicoes) {
        this.avalicoes = avalicoes;
        this.montaStringDataBase();
    }

    private void montaStringDataBase() {
        this.stringDatabase = String.valueOf(this.avalicoes.getIdRelLinhaHorarios())
                + " " + String.valueOf(this.avalicoes.getNota())
                + " <[" + this.avalicoes.getComentario() + "]>";
    }

    public boolean insereDataBase(Context context) {
        DataBaseMainFactory dbConnection = new DataBaseMainFactory(context);
        dbConnection.setDados(this.stringDatabase);
        return dbConnection.insertData();
    }

    public String lerDataBase(Context context) throws IOException {
        DataBaseMainFactory dbConnection = new DataBaseMainFactory(context);
        return dbConnection.readData();
    }


}
