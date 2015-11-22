package andreotxai.busaodadepressaoz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Batman on 22/11/2015.
 */
public class DataTree {

    private String textData;
    private static final String REGEX_COMENTARIO = "(?<=\\[)(.*?)(?=\\])";

    public DataTree() {}

    public DataTree(String textData) {
        this.textData = textData;
    }

    public String montaArvore() {
        String[] firstSplit = this.textData.split("\n");
        String stringReturn = "";
        Matcher matcher = Pattern.compile(REGEX_COMENTARIO).matcher(firstSplit[0]);
        if (matcher.find()) {
            stringReturn = matcher.group(0);
        }
        return stringReturn;
    }
}
