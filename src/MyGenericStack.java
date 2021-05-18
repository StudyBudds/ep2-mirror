public class MyGenericStack<T> {
    private MyGenericNode<T> top;

    public void push(T val) {
        if(top == null) {
            top = new MyGenericNode<>(val, null);
        }
        else {
            top = new MyGenericNode<>(val, top);
        }
    }

    public T peek() {
        return top == null ? null : top.val;
    }

    public T pop() {
        if(top == null) {
            return null;
        }
        T val = top.val;
        top = top.next;
        return val;
    }

    public static class MyGenericNode<T> {
        private T val;
        private MyGenericNode<T> next;

        public MyGenericNode(T val, MyGenericNode<T> next) {
            this.val = val;
            this.next = next;
        }
    }
}
