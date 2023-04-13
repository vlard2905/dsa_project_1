public class SplayNode {
    long key;
    String data;
    SplayNode left, right, top;

    public SplayNode(long key) {
        this.key = key;
        this.data = null;
        left = right = top = null;
    }
    public SplayNode(long key, String data) {
        this.key = key;
        this.data = data;
        left = right = top = null;
    }
}
