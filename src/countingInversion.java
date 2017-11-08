/*
 * Rodger Byrd
 * 11/8/17
 * Homework 3 part 2
 * CS5720
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;


public class countingInversion {


	public static void main(String[] args) 
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		//set input size n
		int n = 5;
		int[] inputArray = new int[n];
		int[] shuffledArray = new int[n];
		Random rand = new Random();
		//int[] temp = new int[n];
		
		for (int i = 0; i < n; i++){
			inputArray[i] =  i;
			//inputArray[i] =  rand.nextInt(50)+1; //testing random inputs vs incremental
		}

		//test shuffle
		shuffledArray = shuffle(inputArray);
		
		/*
		//test arrays for algorithm verification
		int[] testArray = {2,4,1,3,5};
		printArray(testArray);
		System.out.println(mergeSortCountInversions(testArray,0,5-1));
		
		int testArray1[] = {12, 11, 13, 5, 6, 7};
		printArray(testArray1);
		System.out.println(mergeSortCountInversions(testArray1,0,6-1));
		*/
		
		//report time
		long startTime = System.currentTimeMillis();
		
		printArray(shuffledArray);
		int inversions = mergeSortCountInversions(shuffledArray,0,n-1);
		System.out.println(inversions);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		String analysis = "Input size: " + n +
				"\nTotal Inversions: " + inversions +
				"\nTotal Runtime: " + totalTime + "\n";
		//printAnalysisToFile("output.txt",analysis);
		
		/*
		//used for creating chart data
		int increment = 1;
		for (int i = 0; i < 10; i++){
			int[] outputArray = new int[increment*=10];
			for (int j = 0; j < increment; j++){
				outputArray[j] =  j;
			}
			outputArray = shuffle(outputArray);
			startTime = System.currentTimeMillis();
			inversions = mergeSortCountInversions(outputArray,0,increment-1);
			endTime   = System.currentTimeMillis();
			totalTime = endTime - startTime;
			analysis = increment + "\t" + totalTime + "\n";
			printAnalysisToFile("output1.txt",analysis);
			*/

	}
	
	public static int  mergeSortCountInversions(int a[], int indexl, int indexr){
		
		int indexm = 0;
		int retval = 0;
		
		if (indexr > indexl){
			
			indexm = (indexl + indexr) / 2;
			retval = mergeSortCountInversions(a,indexl, indexm);
			retval += mergeSortCountInversions(a,indexm+1, indexr);
			
			retval += mergeCount(a,indexl,indexm,indexr);
		}
		return retval;
	}
	public static int mergeCount(int a[], int l, int m, int r){
		
		int retval = 0;
		int n1 = m-l+1;
		int n2 = r-m;
		
		//create temp arrays
		int[] Left = new int[n1];
		int[] Right = new int[n2];
		
		for (int i = 0; i < n1; i++){
			Left[i] = a[l+i];
		}
		for (int i = 0; i < n2; i++){
			Right[i] = a[m+1+i];
		}
		
		//indexes for incrementing, can't overwrite original values in 
		//while loop
		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = l;
		
		
		while ((leftIndex < n1) && (rightIndex < n2) ){
			if (Left[leftIndex] <= Right[rightIndex]){
				a[resultIndex] = Left[leftIndex];
				leftIndex++;
			}else{

				a[resultIndex] = Right[rightIndex];
				rightIndex++;
				retval += (m-leftIndex +1);
			}
			resultIndex++;
		}
		
		while (leftIndex < n1){
			a[resultIndex] = Left[leftIndex];
			resultIndex++;
			leftIndex++;
		}
		while (rightIndex < n2){
			a[resultIndex] = Right[rightIndex];
			resultIndex++;
			rightIndex++;
		}
		
		return retval;
	}
	public static void printAnalysisToFile(String filename, String text) 
			throws UnsupportedEncodingException, FileNotFoundException, IOException{
		
		try (FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)){
				out.print(text);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			} 

	}
	public static int[] shuffle(int a[]){
		Random rand = new Random();
		int randIndex = 0;
		//shuffle array
		for (int i = 0; i < a.length; i++){
			randIndex = rand.nextInt(a.length);
			int temp = a[randIndex];
			a[randIndex] = a[i];
			a[i] = temp;
		}
		return a;
		
	}
	//debugging, print to console
	public static void printArray(int a[]){
		for (int i = 0; i < a.length; i++){
			System.out.print(a[i]+ " ");
		}
		System.out.print("\n");
	}

}
