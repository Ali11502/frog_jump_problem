public class Queue<T> {
    Vertex[] vertices;
    int F;
    int R;

    //non-parametrised constructor
    Queue() {
        vertices = new Vertex[100];
        F = 9;
        R = 9;
    }

    // Constructor
    Queue(int size) {
        vertices = new Vertex[size];
        F = size - 1;
        R = size - 1;
    }

    public void Enqueue(Vertex obj) {
        if (isFull()) {
            System.out.println("The queue is FULL");
        } else {

            R = (R + 1) % vertices.length;
            vertices[R] = obj;
        }

    }

    public Vertex Dequeue() {

        if (isEmpty()) {
            return null;
        } else {
            F = (F + 1) % vertices.length;
            return vertices[F];
        }
    }

    public boolean isEmpty() {
        return F == R;
    }

    public boolean isFull() {
        return ((R + 1) % vertices.length == F);
    }

    public String toString() {
        String str = "";
        int i = 0;
        if (isEmpty()) {
            return null;
        } else {
            for (i = (F + 1) % vertices.length; i != R; i = (i + 1) % vertices.length) {
                str = str + vertices[i] + " ";
            }
            return str + vertices[i];
        }
    }
}
