package SymReg;

public abstract class Op {

    abstract double function(double x);

    abstract String getName();
}

//*****************************************************

class Add extends Op {

    String name = "+";

    double value;

    Add(double value){
        this.value = value;
    }

    @Override
    double function(double x) {
        return 0;
    }


    @Override
    String getName() {
        return name;
    }
}

//*****************************************************

class Sub extends Op {

    String name = "-";

    double value;

    Sub(double value) {
        this.value = value;
    }

    @Override
    double function(double x) {
        return 0;
    }


    String getName() {

        return name;
    }
}

class Div extends Op {

    String name = "/";

    double value;

    Div(double value) {
        this.value = value;
    }

    @Override
    double function(double x) {
        return 0;
    }

    @Override
    String getName() {
        return name;
    }
}

//*****************************************************

class Mul extends Op {

    String name = "*";

    double value;

    Mul(double value) {
        this.value = value;
    }

    @Override
    double function(double x) {
        return 0;
    }

    @Override
    String getName() {
        return name;
    }
}