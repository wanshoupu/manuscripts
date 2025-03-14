package org.shoupu.HeavyNumber;

public class HeavyNumber {
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int countHeavyNumber(int a, int b){ 
		b++; // make b the upper boundary exclusive

	int an = a; //auxiliary variable: next boundary
	int count = 0;
	boolean isHeavy = isHeavy(a);
	while(an < b) {
	     a = an;
	     System.err.printf("a = %d\n", a);
//	     if(isHeavy)
//	          find the next non-heavy number --> an
//	     else
//	          find the next heavy number --> an
	     an = nextBoundary(a);
//	     System.err.printf("an = %d\n", an);
	     if (an > b)
	          an = b;
	     if (isHeavy)
	          count += an - a;
	     isHeavy = !isHeavy(a);
	}
	return count;
	}
	//helper function to find the next boundary
	//if a is heavy, find the next non-heavy boundary
	//if a is non-heavy, find the next heavy boundary
	private static int nextBoundary(int a){
	     int sum = sumDigit(a);
	     int len = length(a);
	     int diff = 7 * len - sum;
	     if(diff > 0){
	          int d = 1;
	          while(diff > 0){
	               int s = 9 - ( a / d) % 10;

	               //truncate to diff
	               if(diff < s){
	                    s = diff;
	               }
	               a += d * s;
	               diff -= s;
	               d *= 10;
	          }
	     } else {
	          int d = 1;
	          boolean carry = false;
	          while(!(diff > 0) ){

	               //get the current digit
	               int s = ( a / d ) % 10;

	               //compensate with the carry
	               if(carry && s == 9){
	                    carry = true;
	                    diff += 9;
	                    s = 0;
	               } else if(carry){
	                    carry = false;
	                    ++s;
	               }

	               //Don't truncate, let it implode
//	               if(diff + s > 0) {
//	                    s = - diff;
//	               }
	               if(s > 0){
	            	   a -= d * s;
	            	   diff += s;
	            	   carry = true;
	               }
	               d *= 10;
	          }
	          if(carry)
	        	  a += d;
	     }
	     return a;
	}
	
    public static void main(String[] args) {
    	int a = 789;
    	int b = 799;
    	System.out.printf("%d\n", countHeavyNumber(a,b));
    }

	private static boolean isHeavy(int a){
		if(a == 0) 
			return false;

		int len = length(a);
		int sum = sumDigit(a);
		return sum >= len * 7;
	}
	private static int sumDigit(int a){
		int sum = 0;
		while(a > 0){
			sum += a % 10;
			a /= 10;
		}
		return sum;
	}
	private static int length(int a){
		int len = 0;
		while(a > 0){
			a /= 10;
			++len;
		}
		return len;
	}

}
