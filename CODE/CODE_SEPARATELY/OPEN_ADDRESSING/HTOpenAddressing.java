
public class HTOpenAddressing {
    OANode[] arrayHash;
    int capacity;
    int size;
    final double overloadIndex = 0.75;

    public HTOpenAddressing() {
        capacity = 29;
        arrayHash = new OANode[capacity];
    }

    public int getIndex(String key) {
        int hash = 5381;
        for (int i = 0; i < key.length(); i++) {
            hash = ((hash << 5) + hash) + key.charAt(i);
        }
        return Math.abs(hash) % capacity;
    }

    /*public int getIndex(String key) {
        int hashCode = 0;
        for(int i = 0; i < key.length(); i++) {
            hashCode += key.charAt(i);
        }
        return hashCode % capacity;
    }*/

    public void insert(String key, String data) {
        int index = getIndex(key);
        OANode newItem = new OANode(key, data);
        if (arrayHash[index] != null) {
            while (arrayHash[index] != null) {
                index++;
                if (index == capacity) {
                    index = 0;
                }
            }
        }
        arrayHash[index] = newItem;
        size++;
        if ((double) size / capacity > overloadIndex) {
            rehashIncrease();
        }
    }

    public void rehashIncrease() {
        OANode[] temp = arrayHash;
        arrayHash = new OANode[2 * capacity];
        capacity *= 2;
        size = 0;
        for (OANode node : temp) {
            if (node != null) {
                insert(node.key, node.data);
            }
        }
    }

    public int counter() {
        int counter = 0;
        for(OANode arrayHash: arrayHash) {
            if (arrayHash != null)
                counter++;
        }
        return counter;
    }

    public void printHashTable() {
        for (int i = 0; i < capacity; i++) {
            OANode current = arrayHash[i];
            if (current != null && current.data != null) {
                System.out.println("Index " + i + ": " + current.key + ":" + current.data);
            } else {
                System.out.println("Index " + i + ": null");
            }
        }
    }

    public int size() {
        return size;
    }

    public String delete(String key) {
        int index = getIndex(key);
        int counter = 0;
        while (arrayHash[index] != null) {
            if (arrayHash[index].key.equals(key)) {
                arrayHash[index] = null;
                size--;
                return "Node deleted.";
            }
            counter++;
            index++;
            if (index == capacity) {
                index = 0;
            }
            if ((double) counter / capacity > overloadIndex) {
                return "This node must be deleted.\n";
            }
        }
        return "This node must be deleted.\n";
    }

    public String search(String key) {
        int index = getIndex(key);
        int counter = 0;
        while (arrayHash[index] != null) {
            if (arrayHash[index].key.equals(key)) {
                return arrayHash[index].data;
            }
            counter++;
            index++;
            if (index == capacity) {
                index = 0;
            }
        }
        return null;
    }
}
