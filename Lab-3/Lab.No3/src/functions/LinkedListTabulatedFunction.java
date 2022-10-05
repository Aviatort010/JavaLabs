package functions;

class FunctionNode{
    FunctionPoint fpValue;
    FunctionNode back = null;
    FunctionNode next = null;
    FunctionNode(){
        fpValue = null;
        back = this;
        next = this;
    }
    FunctionNode(FunctionPoint val){
        fpValue = val;
        back = this;
        next = this;
    }
}

public class LinkedListTabulatedFunction {
    FunctionNode head = new FunctionNode();

    LinkedListTabulatedFunction(){}

    boolean is_empty() { return head.next == head; }

    public FunctionNode addNodeToTail(FunctionPoint val) {
        FunctionNode new_node = new FunctionNode(val);
        if (is_empty()) {
            head.next = new_node;
            new_node.next = head;
            head.back = new_node;
            new_node.back = head;
        } else {
            head.back.next = new_node;
            new_node.next = head;
            new_node.back = head.back;
            head.back = new_node;
        }
        return new_node;
    }

    FunctionNode getNodeByIndex(int index){
        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        return currentNode;
    }
}
