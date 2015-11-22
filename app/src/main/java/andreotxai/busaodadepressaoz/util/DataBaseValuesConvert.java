package andreotxai.busaodadepressaoz.util;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import andreotxai.busaodadepressaoz.DAO.AvaliacoesDAO;
import andreotxai.busaodadepressaoz.model.Empresa;

/**
 * Created by Batman on 21/11/2015.
 */
final public class DataBaseValuesConvert {
    public static DataTree dataTree;

    public static final String[] arraySpinnerEmpresa = new String[] {
            "<none>","Carris", "Conorte", "STS", "Unibus"
    };

    public static final String[] vazio = new String[] {
            "<nenhuma empresa selecionada>"
    };

    public static final String[] arraySpinner2Carris = new String[] {
            "<none>","353 - IPIRANGA / PUC", "343 - CAMPUS / IPIRANGA", "T9 - PUC"
            , "D43 - UNIVERSITARIA-DIRETA", "T10 - TRIANGULO / ANTONIO DE CARVALHO"
            , "T1 - TRANSVERSAL1", "T1D - T1 DIRETA"
    };

    public static final String[] arraySpinner2Conorte = new String[] {
            "<none>", "637 - CHACARA DAS PEDRAS", "B09 - AEROPORTO/IGUATEMI"
            , "TR60 - TRONCAL TRIANGULO", "608 - IAPI", "611 - LINDOIA"
            , "615 - SARANDI", "617 - IGUATEMI"
    };

    public static final String[] arraySpinner2Sts = new String[] {
            "<none>", "165 - COHAB", "178 - PRAIA DE BELAS", "179 - SERRARIA"
            , "187 - PADRE REUS", "209 - RESTINGA", "260 - BELEM VELHO/OSCAR PEREIRA"
            , "267 - ORFANATROFIO"
    };

    public static final String[] arraySpinner2Unibus = new String[] {
            "<none>","314 - PUC RESTINGA", "340 - JARDIM BOTANICO", "344 - SANTA MARIA"
            , "346 - SAO JOSE", "347 - ALAMEDA", "348 - JARDIM BENTO GONCALVES"
            , "360 - I P E"
    };

    public static final String[] arraySpinnerHorario = new String[] {
            "<none>","08:00", "08:20", "08:40", "09:00", "09:20"
    };

    public static int getEmpresaIndex(String empresa) {
        return getIndex(empresa, arraySpinnerEmpresa);
    }

    public static int getLinhaIndex(String linha, String empresa) {
        int index = -1;
        switch(empresa) {
            case "Carris":
                index = getIndex(linha, arraySpinner2Carris);
                break;
            case "Conorte":
                index = getIndex(linha, arraySpinner2Conorte);
                break;
            case "STS":
                index = getIndex(linha, arraySpinner2Sts);
                break;
            case "Unibus":
                index = getIndex(linha, arraySpinner2Unibus);
                break;
        }
        return index;
    }

    public static int getHorarioIndex(String horario) {
        return getIndex(horario, arraySpinnerHorario);
    }

    private static int getIndex(String string, String[] array) {
        int index = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(string)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static List<Empresa> databaseToApplicationEmpresas(String databaseValues) {
        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
        for(String empresaDb : databaseValues.split("\n")) {
            listaEmpresas.add(new Empresa(Integer.valueOf(empresaDb.split(";")[0]), empresaDb.split(";")[1]));
        }
        return listaEmpresas;
    }

    public static void mountDataTree(Context context) throws IOException {
        AvaliacoesDAO dao = new AvaliacoesDAO();
        String teste = dao.lerDataBase(context);
        dataTree = new DataTree(teste);
        dataTree.montaArvore();
    }

}
