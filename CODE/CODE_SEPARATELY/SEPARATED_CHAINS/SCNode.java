public class SCNode<typeKey, typeData> {
    typeKey key;
    typeData data;
    SCNode<typeKey, typeData> next;

    public SCNode(typeKey key, typeData data) {
        this.key = key;
        this.data = data;
        next = null;
    }

}
