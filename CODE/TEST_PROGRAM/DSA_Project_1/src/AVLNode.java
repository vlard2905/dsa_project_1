class AVLNode
{
    long key;
    String data;
    int height;  //for height
    AVLNode left;
    AVLNode right;

    //default constructor to create null node
    public AVLNode()
    {
        left = null;
        right = null;
        key = 0;
        height = 0;
    }
    // parameterized constructor
    public AVLNode(long element, String data)
    {
        left = null;
        right = null;
        this.key = element;
        this.data = data;
        height = 0;
    }
}
