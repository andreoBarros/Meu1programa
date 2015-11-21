package andreotxai.busaodadepressaoz.DAO.file;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Classe responsável pela escrita e leitura de arquivo.
 * Created by Batman on 20/11/2015.
 */
public class ManageFile {
    private static final String TAG = "ManageFile";

    private String fileName;
    private Context context;

    public ManageFile(Context context, String fileName) throws IOException {
        this.context = context;
        this.fileName = fileName;
        File file = this.context.getFileStreamPath(this.fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Escreve no arquivo texto.
     * @param text Texto a ser escrito.
     * @return True se o texto foi escrito com sucesso.
     */
    public boolean WriteFile(String text){
        try {
            // Abre o arquivo para escrita ou cria se não existir
            FileOutputStream out = context.openFileOutput(this.fileName,
                    Context.MODE_APPEND);
            out.write(text.getBytes());
            out.write("\n".getBytes());
            out.flush();
            out.close();
            return true;

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    /**
     * Faz a leitura do arquivo
     * @return O texto lido.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String ReadFile() throws IOException {
        File file = context.getFilesDir();
        File textfile = new File(file + "/" + this.fileName);

        FileInputStream input = context.openFileInput(this.fileName);
        byte[] buffer = new byte[(int)textfile.length()];

        input.read(buffer);

        return new String(buffer);
    }
}
