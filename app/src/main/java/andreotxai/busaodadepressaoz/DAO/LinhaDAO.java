package andreotxai.busaodadepressaoz.DAO;

import android.content.Context;

import java.io.IOException;

import andreotxai.busaodadepressaoz.DAO.file.DataBaseLinhaFactory;
import andreotxai.busaodadepressaoz.model.Linha;

/**
 * Created by Batman on 21/11/2015.
 */
public class LinhaDAO implements IModelDAO {
    private Linha linha;
    private String stringDatabase;

    private void montaStringDataBase() {
        this.stringDatabase = String.valueOf(this.linha.getIdLinha())
                + ";" + this.linha.getCodigo()
                + ";" + this.linha.getNome()
                + ";" + String.valueOf(this.linha.getIdEmpresa());
    }

    public boolean insereDataBase(Context context) {
        DataBaseLinhaFactory dbConnection = new DataBaseLinhaFactory(context);
        dbConnection.setDados(this.stringDatabase);
        return dbConnection.insertData();
    }

    public String lerDataBase(Context context) throws IOException {
        DataBaseLinhaFactory dbConnection = new DataBaseLinhaFactory(context);
        return dbConnection.readData();
    }
}
