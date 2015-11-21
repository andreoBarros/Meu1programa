package andreotxai.busaodadepressaoz.DAO;

import android.content.Context;

import java.io.IOException;

import andreotxai.busaodadepressaoz.DAO.file.DataBaseEmpresaFactory;
import andreotxai.busaodadepressaoz.DAO.file.DataBaseMainFactory;
import andreotxai.busaodadepressaoz.model.Empresa;

/**
 * Created by Batman on 21/11/2015.
 */
public class EmpresasDAO implements IModelDAO {
    private Empresa empresa;
    private String stringDatabase;

    private void montaStringDataBase() {
        this.stringDatabase = String.valueOf(this.empresa.getIdEmpresa())
                + " " + String.valueOf(this.empresa.getNome());
    }

    public boolean insereDataBase(Context context) {
        DataBaseEmpresaFactory dbConnection = new DataBaseEmpresaFactory(context);
        dbConnection.setDados(this.stringDatabase);
        return dbConnection.insertData();
    }

    public String lerDataBase(Context context) throws IOException {
        DataBaseEmpresaFactory dbConnection = new DataBaseEmpresaFactory(context);
        return dbConnection.readData();
    }
}
