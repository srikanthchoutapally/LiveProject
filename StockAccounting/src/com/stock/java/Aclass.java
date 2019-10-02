package com.stock.java;

public class Aclass {
	//Variable
	int i = 10;
	//method
	public void add(){
		int a=50;
		int b=60;
		int c=a+b;
		System.out.println(c);
	}
	public static void main(String[] args) {
		Aclass obj= new Aclass();
		obj.add();
	}
}
