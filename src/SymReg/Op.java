package SymReg;

public abstract class Op {

    abstract double function(double x, double y);

    abstract String getName();
}

class Add extends Op {

    String name = "+";

    Add(double x, double y) {
        function(x, y);
    }

    @Override
    double function(double x, double y) {
        return x + y;
    }

    @Override
    String getName() {
        return name;
    }
}

class Sub extends Op {

    String name = "-";

    Sub(double x, double y) {
        function(x, y);
    }

    @Override
    double function(double x, double y) {
        return 0;
    }

    String getName() {
        return name;
    }
}

class Div extends Op {

    String name = "/";

    @Override
    double function(double x, double y) {
        if (y == 0) {
            return 0;
        }
        else return x / y;
    }

    @Override
    String getName() {
        return name;
    }
}

class Mul extends Op {

    String name = "*";

    @Override
    double function(double x, double y) {
        return x * y;
    }

    @Override
    String getName() {
        return name;
    }
}