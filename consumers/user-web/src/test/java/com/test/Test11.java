package com.test;

public class Test11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String[] strArrGoodsId = null;
		strArrGoodsId = new String[1];
		if(null == strArrGoodsId || strArrGoodsId.length <= 0 || null == strArrGoodsId[0]){
			System.out.println("null");
		}
		System.out.println("not null"+strArrGoodsId[0]);*/
		
		int lockSum = 0;
		String counnt = "9(1)";
		
		lockSum = lockSum + Integer.valueOf(counnt.substring(0, counnt.indexOf("(")))+ Integer.valueOf(counnt.substring(counnt.indexOf("(")+1, counnt.indexOf(")")));
		System.out.println("lockSum:"+lockSum);
	}

}
