package SymReg;

public class Point {
    double x,y;

   public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    double getX(){
        return x;
    }

    double getY(){
        return y;
    }

    @Override
    public String toString(){
        return "X: " + x + " Y: " + y;
    }
}
