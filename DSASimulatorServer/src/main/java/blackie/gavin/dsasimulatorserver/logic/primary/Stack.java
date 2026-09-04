package blackie.gavin.dsasimulatorserver.logic.primary;

public class Stack {

    static {
        System.loadLibrary("DSALogicLibrary");
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.hello();
    }

    private int[] arr;
    private int capacity;
    private int top;

    public Stack() {
        this.capacity = 10;
        this.arr = new int[this.capacity];
    }

    public int test() {
        this.hello();
        return 5;
    }

    public native void hello();

    public native void push(int element);

    public native int pop();

    public native int peek(int index);

}
