package ApiTesting.paytmInsider;

import java.util.ArrayList;

public class primenumber {

	public static void main(String[] args) {
		
		int count = 0;
		int num = 2;
		System.out.println("First 100 prime numbers are :");
		while(count != 100) { // number of prime numbers entered keep searching..
		    boolean prime = true;// to check whether the number is prime or not
		    for (int i = 2; i <= Math.sqrt(num); i++) { 
		        if (num % i == 0) {
		            prime = false; // if number divides any other number then its not prime and so set prime to false and break the loop.
		            break;
		        }

		    }
		    if (prime) {
		        count++;
		        System.out.println(num);
		    }
		    num++; 
		}}}