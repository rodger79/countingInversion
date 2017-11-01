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
		printArray(inputArray);
		
		printArray(shuffle(inputArray));


	}
	public static int countInversions(){
		int retval = 0;
		
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
