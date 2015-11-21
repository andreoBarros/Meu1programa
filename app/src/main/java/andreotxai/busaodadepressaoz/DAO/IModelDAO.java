package andreotxai.busaodadepressaoz.DAO;

import android.content.Context;

import java.io.IOException;

import andreotxai.busaodadepressaoz.DAO.file.DataBaseMainFactory;
import andreotxai.busaodadepressaoz.model.Avalicoes;

/**
 * Created by Batman on 21/11/2015.
 */
interface IModelDAO {

    boolean insereDataBase(Context context);

    String lerDataBase(Context context) throws IOException;
}
