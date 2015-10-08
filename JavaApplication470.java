
package javaapplication470;
class Foreman{
    Builder builder;
    public Foreman(Builder builder){
        this.builder=builder;
    }
    public void create(){
        builder.createFundament();
        builder.createWalls();
        builder.createRoof();
    }
    
}

abstract class Builder{
    Building build;
    
    abstract void createFundament();
    abstract void createWalls();
    abstract void createRoof();
    public Building getProduct(){
        return build;
    }
} 

class CottageBuilder extends Builder{
    public CottageBuilder(){
        build=new Cottage();
    }

    @Override
    void createFundament() {
        build.setFundament("fundament");
    }

    @Override
    void createWalls() {
        build.setWalls("walls");
    }

    @Override
    void createRoof() {
        build.setRoof("roof");
    }
    
}

abstract class Building{
     String fundament;
     String walls;
     String roof;
    abstract public void setFundament(String fundament);
    abstract public void setWalls(String walls);
    abstract public void setRoof(String roof);
}

class Cottage extends Building{
     
    @Override
    public void setFundament(String fundament) {
        this.fundament=fundament;
    }

    @Override
    public void setWalls(String walls) {
      this.walls=walls;
    }

    @Override
    public void setRoof(String roof) {
      this.roof=roof;
    }
    
}

public class JavaApplication470 {

   
    public static void main(String[] args) {
       Builder b=new CottageBuilder();
       Foreman f=new Foreman(b);
       f.create();
       Building build=b.getProduct();
    }
    
}
