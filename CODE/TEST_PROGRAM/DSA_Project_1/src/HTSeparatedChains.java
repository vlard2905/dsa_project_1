import java.util.ArrayList;
import java.util.Random;
public class HTSeparatedChains<typeKey, typeData> {
    ArrayList<SCNode<typeKey, typeData>> arrayHash;
    int size;
    int countOfNodes;
    final double overloadIndex = 0.7;
    public HTSeparatedChains() {
        size = 10;
        arrayHash = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayHash.add(null);
        }
    }
    public int getIndex(typeKey key) {
        int hashCode = key.hashCode();
        while (hashCode < size) {
            hashCode += size;
        }
        return hashCode % size;
    }
    public void insert(typeKey key, typeData data) {
        int index = getIndex(key);
        SCNode<typeKey, typeData> head = arrayHash.get(index);
        while(head != null) {
            if(head.key.equals(key)) {
                head.data = data;
                return;
            }
            head = head.next;
        }
        SCNode<typeKey, typeData> newItem = new SCNode<typeKey, typeData>(key, data);
        head = arrayHash.get(index);
        newItem.next = head;
        arrayHash.set(index, newItem);

        countOfNodes++;
        double currentOverloadIndex = (1.0 * countOfNodes) / size;
        if (currentOverloadIndex > overloadIndex) rehashIncrease();
    }

    public void rehashIncrease() {
        ArrayList<SCNode<typeKey, typeData>> temp = arrayHash;
        arrayHash = new ArrayList<SCNode<typeKey, typeData>>(2 * size);
        for (int i = 0; i < 2 * size; i++) {
            arrayHash.add(null);
        }
        countOfNodes = 0;
        size *= 2;
        for (SCNode<typeKey, typeData> head : temp) {
            while (head != null) {
                insert(head.key, head.data);
                head = head.next;
            }
        }
    }

    public void printHashTable() {
        ArrayList<SCNode<typeKey, typeData>> temp = arrayHash;
        for (int i = 0; i < arrayHash.size(); i++) {
            SCNode<typeKey, typeData> head = arrayHash.get(i);
            System.out.print(i + ") ");
            while(head != null) {
                System.out.print("key = " + head.key + ", data = " + head.data + " -> ");
                head = head.next;
            }
            System.out.println();
        }
    }

    public typeData search(typeKey key) {
        int currentIndex = getIndex(key);
        SCNode<typeKey, typeData> temp = arrayHash.get(currentIndex);
        while(temp != null) {
            if(temp.key.equals(key)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void delete(typeKey key) {
        int removingIndex = getIndex(key);
        SCNode<typeKey, typeData> head = arrayHash.get(removingIndex);
        SCNode<typeKey, typeData> prev = null;
        for (int i = 0; i < arrayHash.size(); i++) {
            if (head != null) {
                if(head.key.equals(key)) {
                    if (prev == null) {
                        arrayHash.set(removingIndex, head.next);
                    } else {
                        prev.next = head.next;
                    }
                    countOfNodes--;
                    return;
                }
                prev = head;
                head = head.next;
            }
        }
    }
}
