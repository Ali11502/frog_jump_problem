import java.util.LinkedList;

public class Vertex<T extends Comparable<T>> {

    StringBuilder ch;
    boolean visited;

    LinkedList neighbours = new LinkedList<>();


    public Vertex(StringBuilder x) {
        ch = x;
        visited = false;
    }

    public String toString() {
        return "" + ch;
    }

    @Override
    //checks whether two vertices are equal or not
    public boolean equals(Object obj) {
        Vertex x = (Vertex) obj;
        String a = x.ch.toString();
        String b = this.ch.toString();
        if (a.equals(b)) {
            return true;
        } else
            return false;
    }
}
