package com.sample;

class SuperClass{

}

class SubClass extends SuperClass{
	public void method(){
		System.out.println("Subclass Method Calling...");
	}
}


public class DownCastingExample {

	public static void main(String[] args) {
		SuperClass superClass1 = new SuperClass();
		SuperClass superClass2 = new SubClass();

		//Valid down casting
		SubClass subClass1 = (SubClass)superClass2;
		subClass1.method();

		//Invalid down casting
//		SubClass subClass2 = (SubClass)superClass1;
	}
}

