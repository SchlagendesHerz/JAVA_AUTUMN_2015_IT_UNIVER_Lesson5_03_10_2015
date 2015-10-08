
package javaapplication472;

import java.util.ArrayList;
import java.util.List;

abstract class Operation{
    abstract public int calculate(int a, int b);
} 
class Sum extends Operation{
    
    public Sum(){
      
    }
    
    public int calculate(int a, int b){
        return a+b;
    }
}

class SumProxy extends Operation{
    Sum hardObject;
    List<Integer> masA=new ArrayList<>();
    List<Integer> masB=new ArrayList<>();
    List<Integer> result=new ArrayList<>();
    public SumProxy(){
        
    }
    
    public int calculate(int a, int b){
        for(int i=0; i<masA.size();i++){
            if(masA.get(i)==a && masB.get(i)==b){
                return result.get(i);
            }
        }
        hardObject=new Sum();
        int res=hardObject.calculate(a,b);
        masA.add(a);
        masB.add(b);
        result.add(res);
        return res;
    }
}

public class JavaApplication472 {

    
    public static void main(String[] args) {
       Operation p=new SumProxy();
       p.calculate(10, 20);
    }
    
}
