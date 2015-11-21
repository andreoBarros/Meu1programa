package andreotxai.busaodadepressaoz.DAO.file;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

/**
 * Classe para manipulação de dados no banco de dados
 * Created by Batman on 21/11/2015.
 */
public class DataBaseMainFactory {
    final private static String TAG = "DataBaseMainFactory";
    final private static String MAIN_TABLE_NAME = "main.txt";

    private ManageFile fileMainStream;
    private String dados;

    public DataBaseMainFactory(Context context) {
        try {
            this.fileMainStream = new ManageFile(context, MAIN_TABLE_NAME);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    public boolean insertData() {
        return this.fileMainStream.WriteFile(this.dados);
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }
}
