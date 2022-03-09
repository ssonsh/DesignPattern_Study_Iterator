public class Main {
    public static void main(String[] args) {
        System.out.println("hi");

        ArrayContainer arrayContainer = new ArrayContainer();
        arrayContainer.add(1);
        arrayContainer.add(3);
        arrayContainer.add(5);
        arrayContainer.add(7);
        arrayContainer.add(9);

        Iterator iterator = arrayContainer.getIterator();
        while(iterator.hasNext()){
            System.out.println("value =: " + iterator.nextVal());
        }

    }
}
