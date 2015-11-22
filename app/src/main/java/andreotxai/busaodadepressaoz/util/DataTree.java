package andreotxai.busaodadepressaoz.util;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import andreotxai.busaodadepressaoz.DAO.AvaliacoesDAO;
import andreotxai.busaodadepressaoz.model.Avalicoes;

/**
 * Created by Batman on 22/11/2015.
 */
public class DataTree {
    private String textData;
    private TreeImp tree;

    public DataTree() {
        tree = new TreeImp("", -1);
    }

    public DataTree(String textData) {
        this();
        this.textData = textData;
    }

    public void montaArvore() {
        String[] linhasSplit = this.textData.split("\n");
        String[] palavrasComentarios;
        for (int i = 0; i < linhasSplit.length; i++) {
            Matcher matcher = Pattern.compile(DataBaseValuesConvert.REGEX_COMENTARIO).matcher(linhasSplit[i]);
            if (matcher.find()) {
                palavrasComentarios = matcher.group(0).split(" ");
                TreeImp.Node node = tree.getRoot();
                for (int j = 0; j < palavrasComentarios.length; j++) {
                    String string = palavrasComentarios[j];
                    for (int k = 0; k < string.length(); k++) {
                        node = this.insereLetra(String.valueOf(string.charAt(k)), (k == (string.length()-1) ? i: -1), node);
                    }
                    node = tree.getRoot();
                }
            }
        }
    }

    public String pesquisaPorPalavra(Context context, String palavra) throws IOException {
        TreeImp.Node node = this.tree.getRoot();
        int index = -1;
        String avaliacao = "";
        char letra;
        for (int i = 0; i < palavra.length(); i++) {
            letra = palavra.charAt(i);
            index = node.procuraLetraChildren(String.valueOf(letra));
            node = (TreeImp.Node) node.getChildren().get(index);
        }
        Integer idAvaliacao = (Integer) node.getLinhas().get(0);
        if (idAvaliacao >= 0) {
            AvaliacoesDAO dao = new AvaliacoesDAO();
            avaliacao = dao.pegarAvaliacaoBanco(context, idAvaliacao);
        }
        return avaliacao;
    }

    public String funcaoTeste() {
        String teste = "";
        for (int i = 0; i < this.tree.getRoot().childrenLength(); i++) {
            teste += ((TreeImp.Node) this.tree.getRoot().getChildren().get(i)).getData();
        }
        return teste;
    }

    private TreeImp.Node insereLetra(String letra, int indexComentario, TreeImp.Node node) {
        int index = node.procuraLetraChildren(letra);
        if (index < 0) {
            index = node.add(letra, indexComentario);
        } else {
            if (node.getData().equals(letra)) {
                node.addLinha(indexComentario);
            }
        }
        return (TreeImp.Node) node.getChildren().get(index);
    }
}
