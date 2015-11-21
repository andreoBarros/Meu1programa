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

    final private String[] arrayEmpresas = new String[] {
            "<none>", "Carris", "Conorte", "STS", "Unibus"
    };

    private void montaStringDataBase() {
        this.stringDatabase = String.valueOf(this.empresa.getIdEmpresa())
                + ";" + String.valueOf(this.empresa.getNome());
    }

    @Override
    public boolean insereDataBase(Context context) {
        DataBaseEmpresaFactory dbConnection = new DataBaseEmpresaFactory(context);
        dbConnection.setDados(this.stringDatabase);
        return dbConnection.insertData();
    }

    @Override
    public String lerDataBase(Context context) throws IOException {
        DataBaseEmpresaFactory dbConnection = new DataBaseEmpresaFactory(context);
        return dbConnection.readData();
    }

    public boolean insereEmpresas(Context context) {
        this.stringDatabase = "";
        for (int i = 1; i <= (arrayEmpresas.length - 1); i++) {
            this.stringDatabase += String.valueOf(i) + ";" + this.arrayEmpresas[i] + "\n";
        }
        DataBaseEmpresaFactory dbConnection = new DataBaseEmpresaFactory(context);
        dbConnection.setDados(this.stringDatabase);
        return dbConnection.insertData();
    }
}
