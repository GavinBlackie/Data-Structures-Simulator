package blackie.gavin.dsasimulatorserver.logic.primary;

public class Stack {

    private int[] arr;
    private int capacity;
    private int top;

    public native void push(int element);

    public native int pop();

    public native int peek(int index);

}
