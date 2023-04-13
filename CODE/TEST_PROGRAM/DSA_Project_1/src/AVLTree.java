

class AVLTree {
    private AVLNode root;
    long countOfNodes = 0;
    public AVLTree() {
        this.root = null;
    }

    public int getHeight(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    public void getCountOfNodes() {
        System.out.println(countOfNodes);
    }


    public void updateHeight(AVLNode node) {
        node.height = Math.max(getHeight(node.left), getHeight((node.right))) + 1;
    }

    public int getBalanceCoef(AVLNode node) {
        return (getHeight(node.left) - getHeight(node.right));
    }


    public void insert(long key, String data) {
        root = insert(root, key, data);
    }

    public AVLNode insert(AVLNode node, long key, String data) {
        if(node == null) {
            node = new AVLNode(key,data);
            countOfNodes++;
        } else if(node.key < key) {
            node.right = insert(node.right, key, data);
            if(getBalanceCoef(node) < -1) {
                if (key <= node.right.key) {
                    node.right = rotateWithLeft(node.right);
                }
                node = rotateWithRight(node);
            }
        } else if(node.key > key) {
            node.left = insert(node.left, key, data);
            if(getBalanceCoef(node) > 1) {
                if (key >= node.left.key) {
                    node.left = rotateWithRight(node.left);
                }
                node = rotateWithLeft(node);
            }
        }
        updateHeight(node);
        return node;
    }

    public AVLNode rotateWithRight(AVLNode node) {
        AVLNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        temp.height = Math.max(getHeight(temp.right), node.height) + 1;
        return temp;
    }

    public AVLNode rotateWithLeft(AVLNode node) {
        AVLNode temp = node.left;
        node.left = temp.right;
        temp.right = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        temp.height = Math.max(getHeight(temp.left), node.height) + 1;
        return temp;
    }

    public void delete(long key) {
        root = delete(key, root);
    }

    /*public AVLNode delete(AVLNode node, long key) {
        if (node == null) {
            return null;
        } else if(node.key < key) {
            node.right = delete(node.right, key);
            if(getBalanceCoef(node) < -1) {
                if (key <= node.right.key) {
                    node.right = rotateWithLeft(node.right);
                }
                node = rotateWithRight(node);
            }
        } else if(node.key > key) {
            node.left = delete(node.left, key);
            if(getBalanceCoef(node) > 1) {
                if (key >= node.left.key) {
                    node.left = rotateWithRight(node.left);
                }
                node = rotateWithLeft(node);
            }
        } else {
            if(node.left == null || node.right == null) {
                AVLNode temp = null;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                node = temp;
            } else {
                AVLNode temp = theMostLeft(node.right);
                node.key = temp.key;
                node.data = temp.data;
                node.right = delete(node.right,temp.key);
            }
        }
        return node;
    }*/

    private AVLNode delete(long element, AVLNode node) {
        if (node == null) {
            return null;
        }

        if (element < node.key) {
            node.left = delete(element, node.left);
        } else if (element > node.key) {
            node.right = delete(element, node.right);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode temp = null;
                if (null == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                node = temp;
            } else {
                AVLNode temp = theMostLeft(node.right);
                node.key = temp.key;
                node.right = delete(temp.key, node.right);
            }
        }

        if (node == null) {
            return null;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        int balance = getBalanceCoef(node);

        if (balance > 1 && getBalanceCoef(node.left) >= 0) {
            return rotateWithLeft(node);
        }

        if (balance > 1 && getBalanceCoef(node.left) < 0) {
            node.left = rotateWithRight(node.left);
            return rotateWithLeft(node);
        }

        if (balance < -1 && getBalanceCoef(node.right) <= 0) {
            return rotateWithRight(node);
        }

        if (balance < -1 && getBalanceCoef(node.right) > 0) {
            node.right = rotateWithLeft(node.right);
            return rotateWithRight(node);
        }

        return node;
    }
    private AVLNode theMostLeft(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void search(long key) {
        search(root, key);
    }

    public String search(AVLNode node, long key) {
        //String data = "Is not found...";
        String data = "";
        while (node != null) {
            if (node.key > key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                //data = node.data;
                data = "";
                break;
            }
            data = search(node, key);
        }
        return data;
    }

    public void clearTree() {
        root = null;
    }
}
