public class ArrayIterator implements Iterator{

    private int pos = -1;
    private final ArrayContainer container;

    public ArrayIterator(ArrayContainer container) {this.container = container;}

    @Override
    public boolean hasNext() {
        if(this.pos < this.container.size() - 1){
            return true;
        }
        return false;
    }

    @Override
    public int nextVal() {
        if(this.hasNext()){
            pos += 1;
            return this.container.get(pos);
        }
        return 0;
    }
}
