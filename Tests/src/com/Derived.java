package com;

import java.util.HashSet;
import java.util.Set;


class Base {
    public String f1(int a) {
        return "f1";
    }
    public String f2() {
        return f1(1);
    }
}

class Derived extends Base {
    public String f1(String a) {
        return "f1t";
    }
    public String f2() {
        return f1(1);
    }
    
    public static void main(String args[]) {
		Derived d=new Derived();
		d.f2();
	}
}

