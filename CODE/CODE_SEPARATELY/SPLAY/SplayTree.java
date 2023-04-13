public class SplayTree {
    SplayNode root;
    boolean check = false;
    public SplayTree() {
        root = null;
    }

    public void clearTree() {
        root = null;
    }

    public void insert(long key, String data) {
        root = insert(root, key, data);
    }
    public SplayNode insert(SplayNode node, long key, String data) {
        if(node == null) {
            node = new SplayNode(key, data);
        } else if(node.key > key) {
            node.left = insert(node.left, key, data);
        } else if(node.key < key) {
            node.right = insert(node.right, key, data);
        }
        return splay(node, key);
    }

    public SplayNode splay(SplayNode node, long key) {
        if(node == null || node.key == key) {
            check = true;
            return node;
        }
        if (node.key > key) {
            if(node.left == null) {
                check = true;
                return node;
            }
            if(node.left.key > key) {
                node.left.left = splay(node.left.left, key);
                node = rightRotation(node);
            } else if (node.left.key < key){
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null) {
                    node.left = leftRotation(node.left);
                }
            }
            if (node.left == null) {
                check = true;
                return node;
            } else {
                return rightRotation(node);
            }
        } else {
            if(node.right == null) {
                check = true;
                return node;
            }
            if (node.right.key > key) {
                node.right.left = splay(node.right.left, key);
                if(node.right.left != null) {
                    node.right = rightRotation(node.right);
                }
            } else if (node.right.key < key) {
                node.right.right = splay(node.right.right, key);
                if(node.right.right != null) {
                    node = leftRotation(node);
                }
            }
            if (node.right == null) {
                check = true;
                return node;
            } else {
                return leftRotation(node);
            }
        }
    }


    public SplayNode rightRotation(SplayNode node) {
        SplayNode temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }
    public SplayNode leftRotation(SplayNode node) {
        SplayNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }

    public void delete(long key) {
        root = delete(root, key);
    }

    /*public Node delete(Node node, long key) {
        if (check) {
            return node;
        } else {
            node = splay(node, key);
        }
        if (node.left == null) {
            node = node.right;
        } else if (node.right == null){
            node = node.left;
        } else {
            Node temp = node.right;
            Node temp2 = temp;
            node = node.left;
            while(temp2.left != null) {
                temp2.top = temp;
                temp2 = temp2.left;
            }
            temp2 = node.right;
            while(temp2 != temp) {
                temp2 = temp2.top;
            }
            node.right = temp2;
        }
        return node;
    }*/

    public SplayNode delete(SplayNode node, long key) {
        if (node == null) {
            return null;
        }
        node = splay(node, key);
        if (node.key != key) {
            return node;
        }
        if (node.left == null) {
            node = node.right;
        } else {
            SplayNode rightSubtree = node.right;
            node = node.left;
            node = splay(node, key);
            node.right = rightSubtree;
        }
        return node;
    }

    public boolean search(long key) {
        boolean existence = false;
        root = search(root, key);
        if (root != null) {
            if (root.key == key) {
                //System.out.print(root.data + " "); for void if I want to know data.
                existence = true;
            }
        }
        return existence;
    }

    public SplayNode search(SplayNode node, long key) {
        return splay(node, key);
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(SplayNode node) {
        if (node == null) {
            return 0;
        } else {
            int count = 1;
            count += countNodes(node.left);
            count += countNodes(node.right);
            return count;
        }
    }

    /*public Node theMostLeft(Node node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }*/
}
