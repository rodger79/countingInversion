import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;


public class countingInversion0 {


	public static void main(String[] args) {
		int n = 10;
		int[] inputArray = new int[n];
		int[] temp = new int[n];
		
		for (int i = 0; i < n; i++){
			inputArray[i] =  i;
		}
		//printArray(inputArray);
		//test shuffle
		
		
		//test array
		int[] testArray = {2,4,1,3,5};
		printArray(testArray);
		System.out.println(mergeSortCountInversions(testArray,temp,0,testArray.length-1));
		
		printArray(shuffle(inputArray));
		System.out.println(mergeSortCountInversions(shuffle(inputArray),temp,0,n-1));
	}
	
	//think of as single array vs multiple array
	public static int  mergeSortCountInversions(int a[], int t[], int indexl, int indexr){
		
		
		int retval = 0;
		
		if (indexr > indexl){
			int indexm = (indexl + indexr) / 2;
			System.out.println(" indexl: " + indexl + " indexm: " + indexm +  " indexr: " + indexr);
			//indexm = (int) Math.ceil((double)a.length / 2);
			retval = mergeSortCountInversions(a,t,indexl, indexm);
			retval += mergeSortCountInversions(a,t,indexm+1, indexr);
			
			retval += mergeCount(a,t,indexl,indexm,indexr);
		}
		return retval;
	}
	public static int mergeCount(int a[], int t[], int l, int m, int r){
		int retval = 0;
		
		//indexes for incrementing, can't overwrite original values in 
		//while loop
		int leftIndex = l;
		int rightIndex = r;
		int resultIndex = l;
		
		while ((leftIndex <= m-1) && (rightIndex <= r) ){
			if (a[leftIndex] <= a[rightIndex]){
				t[resultIndex] = a[leftIndex];
				leftIndex++;
				resultIndex++;
			}else{
				System.out.println("inversion found" + 
						"leftIndex: " + leftIndex + " m: " + m);
				
				t[resultIndex] = a[rightIndex];
				resultIndex++;
				rightIndex++;
				retval += (m-leftIndex +1);
			}
		}
		
		while (leftIndex <= m-1){
			t[resultIndex] = a[leftIndex];
			resultIndex++;
			leftIndex++;
		}
		while (rightIndex <= r){
			t[resultIndex] = a[rightIndex];
			resultIndex++;
			rightIndex++;
		}
		
		return retval;
	}
	public static void printAnalysisToFile(String filename, String text) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		
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
