package andreotxai.busaodadepressaoz.DAO.file;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe para manipulação de dados no banco de dados
 * Created by Batman on 21/11/2015.
 */
public class DataBaseMainFactory {
    final protected static String TAG = "DataBaseMainFactory";
    final private static String MAIN_TABLE_NAME = "main.txt";
    final protected static String EMPRESA_TABLE_NAME = "empresa.txt";
    final protected static String HORARIO_TABLE_NAME = "horario.txt";
    final protected static String LINHA_TABLE_NAME = "linha.txt";
    final protected static String REL_LINHA_HORARIO_TABLE_NAME = "rel_linha_horario.txt";

    protected ManageFile fileMainStream;
    private String dados;
    private String dadosBanco;

    public DataBaseMainFactory() {}

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

    public String readData() throws IOException {
        return this.fileMainStream.ReadFile();
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getDadosBanco() {
        return dadosBanco;
    }

    public void setDadosBanco(String dadosBanco) {
        this.dadosBanco = dadosBanco;
    }
}
