
package javaapplication474;

abstract class Component{
    
    public abstract int calculate();
}

class PlusOperation extends Component{
    Component operand1;
    Component operand2;
    public PlusOperation(Component operand1,Component operand2){
        this.operand1=operand1;
        this.operand2=operand2;
    }
    
    public int calculate(){
        return operand1.calculate()+operand2.calculate();
    }
}

class MultOperation extends Component{
    Component operand1;
    Component operand2;
    public MultOperation(Component operand1,Component operand2){
        this.operand1=operand1;
        this.operand2=operand2;
    }
    
    public int calculate(){
        return operand1.calculate()*operand2.calculate();
    }
}

class Primitive extends Component{
    int value;
    public Primitive(int value){
        this.value=value;
    }
    
    public int calculate(){
        return value;
    }
}
public class JavaApplication474 {

    
    public static void main(String[] args) {
        Component op1=new Primitive(2);
        Component op2=new Primitive(10);
        Component plus=new PlusOperation(op1,op2);
        Component op3=new Primitive(6);
        Component multLeft=new MultOperation(plus,op3);
        Component op4=new Primitive(18);
        Component op5=new Primitive(12);
        Component multRight=new MultOperation(op4,op5);
        Component top=new PlusOperation(multLeft,multRight);
        
        int res=top.calculate();
        System.out.println(res);
    }
    
}
