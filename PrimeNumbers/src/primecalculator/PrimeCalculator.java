package primecalculator;

import java.util.ArrayList;

public class PrimeCalculator extends Thread{
private int numOfPrimes;
private int start, end;
private ArrayList<Integer> primeNumberList;

	PrimeCalculator(int start, int end){
		super();
		numOfPrimes =0 ;
		this.start = start;
		this.end = end;
		primeNumberList = new ArrayList<Integer>();
	}
	
	
	public void run(){
		boolean isPrime;
		int P;
		
		System.out.println(Thread.currentThread().getName() + " ["+
		start + "," + end + "] started...");
		
		
		for(int curNumber = start; curNumber <= end; curNumber++){
			isPrime = true;
			if(curNumber ==1){
				isPrime = false;
			}
			for(P= 2; P < curNumber; P++){
				
				
				if( curNumber %P ==0){
					isPrime = false;
				}
			}
			if (isPrime){
				numOfPrimes++;
				primeNumberList.add(P);
			}
		}
		System.out.println("..." + Thread.currentThread().getName()+
				" [" + start + ","+ end + "] completed. "
				+ "Prime numbers found: " + numOfPrimes + " (" + getPrimeNumbers()+ ")");
	}
	public int getNumPrimeNumbers(){
		return numOfPrimes;
	}
	
	public String getPrimeNumbers(){
		String s = "";
		for(int i = 0; i< primeNumberList.size(); i++)
			s+= primeNumberList.get(i) + " ";
		return s;
	}
	
	public static void main(String[] args) throws InterruptedException{
		Thread ct = Thread.currentThread();
		System.out.println("Main thread name: " + ct.getName());
		
		int firstNumber =1; 
		int lastNumber = 20;
		int numOfThreads = 3;
		int numFoundPrimes= 0;
		
		int range = (int) (lastNumber - firstNumber +1)/ numOfThreads;
		
		PrimeCalculator threads[] = new PrimeCalculator[numOfThreads];
		
		int fromNumber = -1;
		int toNumber = -1;
		
		for (int i=0; i< numOfThreads; i++){
			fromNumber = i* range + firstNumber;
			toNumber = (i< numOfThreads -1) ? (fromNumber + range-1): (lastNumber);
			
			threads[i] = new PrimeCalculator(fromNumber, toNumber);
		}
		
		for(int i = 0; i< numOfThreads; i++){
			threads[i].start();
		}
		for(int i =0; i<numOfThreads; i++){
			threads[i].join();
		}
		
		
		for(int i =0; i< numOfThreads; i++){
			numFoundPrimes += threads[i].getNumPrimeNumbers();
		}
		
		System.out.println("N. of prime numbers = " + numFoundPrimes);
	}
}
