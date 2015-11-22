package andreotxai.busaodadepressaoz.util;

import java.util.ArrayList;
import java.util.List;

import andreotxai.busaodadepressaoz.model.Empresa;

/**
 * Created by Batman on 21/11/2015.
 */
final public class DataBaseValuesConvert {

    public static List<Empresa> databaseToApplicationEmpresas(String databaseValues) {
        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
        for(String empresaDb : databaseValues.split("\n")) {
            listaEmpresas.add(new Empresa(Integer.valueOf(empresaDb.split(";")[0]), empresaDb.split(";")[1]));
        }
        return listaEmpresas;
    }

}
