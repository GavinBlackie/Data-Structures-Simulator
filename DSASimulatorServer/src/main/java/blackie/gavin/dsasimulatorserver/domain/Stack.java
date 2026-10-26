package blackie.gavin.dsasimulatorserver.domain;

public class Stack extends LinearStructure{

//    static {
//        System.loadLibrary("DSALogicLibrary");
//    }

    // TODO: get rid of testing here and get a designated testing class
    static void main() {
        Stack stack = new Stack();

        System.out.println("IsEmpty?: " + stack.isEmpty() );
        System.out.println("IsFull?: " + stack.isFull() );

        for (int iCount = 1; iCount <= 10; iCount++) {
            stack.push(iCount);
            System.out.println("push: " + iCount);
        }
        System.out.println("IsEmpty?: " + stack.isEmpty() );
        System.out.println("IsFull?: " + stack.isFull() );

        System.out.println("\nCapacity: " + stack.capacity);
        System.out.println("IsFull?: " + stack.isFull() );
        System.out.println("push: " + 11);
        stack.push(11);
        System.out.println("Capacity: " + stack.capacity);
        System.out.println("IsFull?: " + stack.isFull() + "\n");

        for (int iCount = 10; iCount > 0; iCount--) {
            System.out.println("pop: " + stack.pop());
        }

        System.out.println("IsEmpty?: " + stack.isEmpty() );
        System.out.println("IsFull?: " + stack.isFull() );
    }

    int[] array;
    private int capacity;
    private int top;

    public Stack() {
        this.capacity = 10;
        this.array = new int[this.capacity];
        this.top = -1;
    }

    //public native void hello();

    //public native void push(int element);
    /*
    public native int pop(int index);

    public native int peek(int index);

    public native boolean isEmpty();
    */

    // region Methods
    public void push(int elem) {
        if (top == capacity - 1) {
            grow();
        }
        array[++top] = elem;
    }
    public int pop() {
        return array[top--];
    }

    @Override
    public boolean isFull() {
        return top == capacity - 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    private void grow() {
        capacity *= 2;
        int[] temp = new int[capacity];
        // Copy original contents into new array using arrayCopy function!
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp; // swap to larger temp array
    }
    // endregion
}
