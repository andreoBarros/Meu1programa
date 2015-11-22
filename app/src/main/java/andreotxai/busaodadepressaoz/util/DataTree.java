package andreotxai.busaodadepressaoz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Batman on 22/11/2015.
 */
public class DataTree {
    private String textData;
    private static final String REGEX_COMENTARIO = "(?<=\\[)(.*?)(?=\\])";
    private TreeImp tree;

    public DataTree() {
        tree = new TreeImp("");
    }

    public DataTree(String textData) {
        this();
        this.textData = textData;
    }

    public void montaArvore() {
        String[] firstSplit = this.textData.split("\n");
        String[] palavrasComentarios;
        for (int i = 0; i < firstSplit.length; i++) {
            Matcher matcher = Pattern.compile(REGEX_COMENTARIO).matcher(firstSplit[i]);
            if (matcher.find()) {
                palavrasComentarios = matcher.group(0).split(" ");
                TreeImp.Node node = tree.getRoot();
                for (int j = 0; j < palavrasComentarios.length; j++) {
                    String string = palavrasComentarios[j];
                    for (int k = 0; k < string.length(); k++) {
                        node = this.insereLetra(String.valueOf(string.charAt(k)), node);
                        tree.getRoot();
                    }
                    node = tree.getRoot();
                }
            }
        }
    }

    public String funcaoTeste() {
        String teste = "";
        for (int i = 0; i < this.tree.getRoot().childrenLength(); i++) {
            teste += ((TreeImp.Node) this.tree.getRoot().getChildren().get(i)).getData();
        }
        return teste;
    }

    private TreeImp.Node insereLetra(String letra, TreeImp.Node node) {
        int index = node.procuraLetraChildren(letra);
        if (index < 0) {
            index = node.add(letra);
        }
        return (TreeImp.Node) node.getChildren().get(index);
    }
}
