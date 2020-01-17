package offer;

import java.util.Stack;

public class Test07 {


    // 插入栈，只用于插入的数据
    private Stack<Object> stack1 = new Stack<Object>();
    private Stack<Object> stack2 = new Stack<Object>();

    public void add(Object e) {
        stack1.add(e);
    }

    // 模拟队列FIFO 拿出元素
    public Object pop(Object object) {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        Object pop = stack2.pop();
        return pop;
    }

}
