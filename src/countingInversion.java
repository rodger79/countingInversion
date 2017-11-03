import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;


public class countingInversion {


	public static void main(String[] args) {
		int n = 100;
		int[] inputArray = new int[n];
		
		for (int i = 0; i < n; i++){
			inputArray[i] =  i;
		}
		//printArray(inputArray);
		
		//printArray(shuffle(inputArray));
		System.out.println(10/2);
		System.out.println(((int)Math.ceil((double)11/2)));

	}
	
	//think of as single array vs multiple array
	public static int [] mergeSortCountInversions(int a[], int indexl, int indexr){
		//if (a.length == 1) return a;
		
		int retval = 0;
		int m = (int) Math.ceil((double)a.length / 2);
		
		mergeSortCountInversions(a,indexl, m);
		mergeSortCountInversions(a,m+1, indexr);
		
		
		return mergeCount(a,indexl,m,indexr);
	}
	public static int[] mergeCount(int a[], int l, int m, int r){
	
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
		//sort array using selection sort
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
