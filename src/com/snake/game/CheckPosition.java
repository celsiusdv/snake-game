package com.snake.game;

public class CheckPosition{
	public static int findFruit(int array[],int left, int right,int objetive) {
		if(right>=left) {
			int mid=left+(right-left)/2;
			if(array[mid]==objetive) return mid;
			if(array[mid]>objetive) return findFruit(array,left, mid-1, objetive);
	
			return findFruit(array,mid+1,right,objetive);
		}
		return -1;
	}
}
