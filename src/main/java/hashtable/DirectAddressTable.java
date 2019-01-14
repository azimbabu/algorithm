package hashtable;

public class DirectAddressTable {

    public static class Element {
        private final int key;
        private final int data;

        public Element(int key, int data) {
            this.key = key;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Element{" +
                   "key=" + key +
                   ", data=" + data +
                   '}';
        }
    }

    private Element[] table;

    public DirectAddressTable(int m) {
        this.table = new Element[m];
    }

    public void insert(Element element) {
        this.table[element.key] = element;
    }

    public Element search(int key) {
        return this.table[key];
    }

    public void delete(Element element) {
        this.table[element.key] = null;
    }

    public static void main(String[] args) {
        DirectAddressTable directAddressTable = new DirectAddressTable(10);

        Element two = new Element(2, 200);
        Element three = new Element(3, 300);
        Element five = new Element(5, 500);
        Element eight = new Element(8, 800);

        directAddressTable.insert(two);
        directAddressTable.insert(three);
        directAddressTable.insert(five);
        directAddressTable.insert(eight);

        for (int i = 0; i < 10; i++) {
            System.out.println("Search " + i + ":" + directAddressTable.search(i));
        }

        directAddressTable.delete(two);
        System.out.println("Search " + two.key + ":" + directAddressTable.search(two.key));
    }
}
