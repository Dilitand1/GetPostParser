package ru.litvinov.getPostParser;

import java.io.Serializable;

public class Main {

    private static String outputSuccessFile = "outputSuccessFile.txt";

    public static void main(String[] args) throws Exception {

    }
}

class A implements Serializable {
    String name = "A";

    public A(String name) {
        this.name += name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class B extends A {
    String name = "B";

    public B(String name) {
        super(name);
        this.name += name;
    }
}

class C extends B implements Serializable {
    String name = "C";

    public C(String name) {
        super(name);
        this.name = name;
    }
}

