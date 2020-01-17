package other;

import java.util.Stack;

/**
 *
 * 两个栈实现队列
 *
 * stack1把元素压入 stack2 stack1弹出的元素压入stack2.然后stack2弹出元素就行。完成啦队列的取出元素。
 *
 */
public class Queue {

    Stack stack1 = new Stack();
    Stack stack2 = new Stack();

    public void push(int node) {
        stack1.push(node);
    }
    public int pop() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("栈为空！");
        }
        // stack2用来盛装元素
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return (int) stack2.pop();
    }

}
