package com.example.callc;

public class Testdll
{
	static   
	{   
		System.loadLibrary("TestdllManager");   
	}
	
	public native static int getVal();   
	
	public native static void setVal(int i);  
	
	public static void main(String[] args)   
	{   
		setVal(10);
		System.out.println(getVal());
	}   

}
