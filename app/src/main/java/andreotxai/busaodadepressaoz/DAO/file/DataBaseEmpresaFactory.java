package andreotxai.busaodadepressaoz.DAO.file;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Batman on 21/11/2015.
 */
public class DataBaseEmpresaFactory extends DataBaseMainFactory {

    public DataBaseEmpresaFactory(Context context) {
        super();
        try {
            this.fileMainStream = new ManageFile(context, EMPRESA_TABLE_NAME);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }
}
