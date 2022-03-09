import java.util.Arrays;

public class ArrayContainer {

    private int pos = 0;
    private int[] arr = new int[pos + 1];

    public void add(int val){
        arr[pos] = val;
        pos++;

        int[] expand = Arrays.copyOf(arr, pos + 1);
        arr = expand;
    }

    public Iterator getIterator(){
        return new ArrayIterator(this);
    }

    public int size() {
        return this.pos;
    }

    public int get(int idx) {
        return arr[idx];
    }
}
