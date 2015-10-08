
package javaapplication475;

import java.util.ArrayList;
import java.util.List;

interface Container{
    Container clone();
    int size();
    int get(int index);
    boolean contains(int element);
    void add(int element);
}

class MyArray implements Container{
    List<Integer> l=new ArrayList<>();
    public Container clone(){
        List<Integer> cl=new ArrayList<>();
        for(int element:l){
            cl.add(element);
        }
        MyArray m=new MyArray();
        m.l=cl;
        return m;
    }
    
    public int size(){
        return l.size();
    }
    
    public int get(int index){
        return l.get(index);
        
    }
    
    public boolean contains(int element){
        return l.contains(element);
    }
    
    public void add(int element){
        l.add(element);
    }
}
class Set{
    Container c;
    public Set(Container c){
        this.c=c;
    }
    
    public Set union(Set s){
        Set result=new Set(this.c.clone());
        for(int i=0;i<s.c.size();i++){
            int element=s.c.get(i);
            if(!result.c.contains(element)){
                result.c.add(element);
            }
        }
        return result;
    }
}
public class JavaApplication475 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
