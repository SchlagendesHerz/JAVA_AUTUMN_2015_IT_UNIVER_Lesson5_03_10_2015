package javaapplication469;
class Factory{
    public static Triangle createProduct(String name){
        switch(name){
            case "black":
                return new BlackTriangle();
            case "white":
                return new WhiteTriangle();
            default:
                return null;
        }
    }
}

abstract class Triangle{
    abstract public int area();
}

class BlackTriangle extends Triangle{
    public int area(){
        return 0;
    }
}

class WhiteTriangle extends Triangle{
    public int area(){
        return 0;
    }
}

public class JavaApplication469 {

    
    public static void main(String[] args) {
        Triangle t=Factory.createProduct("black");
        t.area();
    }
    
}
