package javaapplication473;
abstract class ElementOfArt{
    abstract public void show();
}

class Picture extends ElementOfArt{
    String name;
    public Picture(String name){
        this.name=name;
    }
    
    public void show(){
        System.out.println(name);
    }
}

abstract class PictureDecorator extends ElementOfArt{
    ElementOfArt element;
    
    abstract public void show();
}

class Frame extends PictureDecorator{
    String name;
    public Frame(String name,ElementOfArt element){
      this.name=name;
      this.element=element;
    }
    
    public void show(){
        element.show();
        System.out.println(name);
    }
}

class Pattern extends PictureDecorator{
    String name;
    public Pattern(String name,ElementOfArt element){
        this.name=name;
        this.element=element;
    }
    
    public void show(){
        element.show();
        System.out.println(name);
    }
}
public class JavaApplication473 {

 
    public static void main(String[] args) {
      ElementOfArt picture=new Picture("picaso");
      PictureDecorator pictureInFrame=new Frame("frame",picture);
      PictureDecorator pictureInFrameWithPattern=
             new Pattern("pattern",pictureInFrame);
      pictureInFrameWithPattern.show();
    }
    
}
