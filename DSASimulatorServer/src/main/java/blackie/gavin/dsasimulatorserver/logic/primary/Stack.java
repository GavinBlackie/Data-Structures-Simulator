package blackie.gavin.dsasimulatorserver.logic.primary;

public class Stack {

    static {
        System.loadLibrary("DSALogicLibrary");
    }

    static void main(String[] args) {
        Stack stack = new Stack();

        System.out.println("IsEmpty?: " + stack.isEmpty() );
        System.out.println("IsFull?: " + stack.isFull() );
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("IsEmpty?: " + stack.isEmpty() );
        System.out.println("IsFull?: " + stack.isFull() );
        for (int iCount = 1; iCount <= 8; iCount++) {
            stack.push(3 + iCount);
        }
        System.out.println("IsEmpty?: " + stack.isEmpty() );
        System.out.println("IsFull?: " + stack.isFull() );
        stack.push(11); // will not push?

        stack.hello();
    }

    private int[] array;
    private int capacity;
    private int top;

    public Stack() {
        this.capacity = 10;
        this.array = new int[this.capacity];
        this.top = -1;
    }

    public int test() {
        return 5;
    }

    public native void hello();
    /*
    public native void push(int element);

    public native int pop(int index);

    public native int peek(int index);

    public native boolean isEmpty();
    */

    // region Methods
    public void push(int elem) {
        if (top == capacity - 1) {
            System.out.println("Stack overflow");
            grow();
            return;
        }
        array[++top] = elem;
        System.out.println(elem + " pushed onto stack");
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void grow() {
        capacity *= 2;
        int[] temp = new int[capacity];
        // Copy original contents into new array
        for (int iElem = 0; iElem < array.length; iElem++) {
            temp[iElem] = array[iElem];
        }
        array = temp; // swap to larger temp array

        System.out.println("Length: " + this.array.length);
    }
    // endregion
}
