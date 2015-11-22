package andreotxai.busaodadepressaoz.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Batman on 22/11/2015.
 */
public class TreeImp {
    private Node<String> root;

    public TreeImp(String rootData) {
        root = new Node<String>(rootData);
        root.data = rootData;
        root.children = new ArrayList<Node<String>>();
    }

    public Node getRoot() {
        return this.root;
    }

    public static class Node<String> {
        private String data;
        private Node<String> parent;
        private ArrayList<Node<String>> children;

        public Node(String data) {
            this.data = data;
            this.children = new ArrayList<Node<String>>();
        }

        public int childrenLength() {
            return this.children.size();
        }

        public int add(String data) {
            Node<String> node = new Node<String>(data);
            this.children.add(node);
            return this.children.indexOf(node);
        }

        public int procuraLetraChildren(String letra) {
            int index = -1;
            if (this.childrenLength() != 0) {
                for (int i = 0; i < this.childrenLength(); i++) {
                    if (this.children.get(i).data.equals(letra)) {
                        index = i;
                        break;
                    }
                }
            }
            return index;
        }

        public String getData() {
            return this.data;
        }

        public List<Node<String>> getChildren() {
            return this.children;
        }
    }
}
