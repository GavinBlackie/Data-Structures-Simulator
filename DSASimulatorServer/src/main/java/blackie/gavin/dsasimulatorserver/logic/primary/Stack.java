package blackie.gavin.dsasimulatorserver.logic.primary;

public class Stack {

    static {
        //System.loadLibrary("hello");
        String absolutePath = "C:\\Projects\\Personal\\Data-Structures-Simulator\\DSASimulatorServer\\hello.dll";
        System.load(absolutePath);
        //System.load("C:\\Projects\\Personal\\Data-Structures-Simulator\\DSASimulatorServer\\src\\main\\java\\blackie\\gavin\\dsasimulatorserver\\logic\\primary\\hello.dll");
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.hello();
    }

    private int[] arr;
    private int capacity;
    private int top;

    public native void hello();

//    public native void push(int element);
//
//    public native int pop();
//
//    public native int peek(int index);

}
