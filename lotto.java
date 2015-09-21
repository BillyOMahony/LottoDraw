import java.awt.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class lotto {
	
	/**
	 *	userNumbers is an array of ints that will contain 5 ints
	 *	between 1 and 50 that will be entered by the user.
	 *	userBonusNumbers will contain 2 ints between 1 and 11
	 *	that will be entered by the user
	 */
	static int[] userNumbers = new int[5];
	static int[] userBonusNumbers = new int[2];
	
	/**
	 *	randomNumbers is an array of ints that will be randomly
	 *	generated and will contain numbers between 1 and 50
	 *	randomBonusNumbers will contain 2 randomly generated ints 
	 *	between 1 and 11
	 */
	static int[] randomNumbers = new int[5];
	static int[] randomBonusNumbers = new int[2];
	
	/**
	 * Checks if int temp is in int array. Either returns true or false
	 * @param array		the array that will be used for comparing
	 * @param temp		the int to compare with the contents of the array
	 * @return contains	Either true or false. True if temp is in array, false if temp is not in array
	 */
	public static boolean contains(int[] array, int temp){
		boolean contains = false;
		for(int x = 0; x < array.length; x++){
			if(array[x] == temp){
				contains = true;
			}
		}
		return contains;
	}
	
	/**
	 * Counts how many numbers of the user set arrays(userNumbers and userBonusNumbers) are in the latest randomly generated arrays(randomNumbers and randomBonusNumbers)
	 * @return counter	returns the the number of ints that are in both userNumbers and randomNumbers and in both userRandomNumbers and userBonusNumbers
	 */
	public static int countNumbers(){
		int counter = 0;
		for(int x = 0; x < 5; x++){
			for(int y = 0; y < 5; y++){
				if(userNumbers[x] == randomNumbers[y]){
				//	System.out.println("Matched normal number: " + userNumbers[x]);
					counter ++;
				}
			}
		}
		for(int x = 0; x < 2; x++){
			for(int y = 0; y < 2; y++){
				if(userBonusNumbers[x] == randomBonusNumbers[y]){
				//	System.out.println("Matched bonus number: " + userBonusNumbers[x]);
					counter ++;
				}
			}
		}
		return counter;
	}
	
	/**
	 * Randomly generates numbers for randomNumbers and randomBonusNumbers. It used the contains method to
	 * make sure that no two numbers in either array are the same. In an actual lotto draw two numbers
	 * the same cannot come up (however a bonus number and a normal number can be the same.)
	 * Random numbers are then printed out. 
	 */
	public static void randomArray(){
		int temp1_11 = 0;
		String randomDraw = ""; 
		Random rand = new Random();
		
		for(int x = 0; x < 5; x++){
			int n = rand.nextInt(50) + 1;
			if(!contains(randomNumbers, n)){
				randomNumbers[x] = n;
				randomDraw = randomDraw + n + " ";
			}else{
				x--;
			}
		}
		for(int x = 0; x < 2; x++){
			int n = rand.nextInt(11) + 1;
			if(n != temp1_11){
				randomBonusNumbers[x] = n;
				randomDraw = randomDraw + n + " ";
				temp1_11 = n;
			}else{
				x--;
			}
		}
		System.out.println("The lotto draw was: " +  randomDraw);
	}
	
	/**
	 * Allows the user to add numbers to userNumbers and userBonusNumbers. 
	 * The user will add numbers one by one and will be prompted the range of numbers possible to select from
	 * If the user selects a number outside the range, or a number already selected then the user will be prompted to reselect
	 * Finally the users selection will be printed out
	 */
	public static void setArray(){
		
		String userChoice = "";
		Scanner scanner = new Scanner(System.in);
		int temp1_11 = 0;
		
		for(int x=0; x<5; x++){
			System.out.println("Please enter a number between 1 and 50: ");
			int temp = scanner.nextInt();
			
			if(temp > 0 && temp < 51 && !contains(userNumbers, temp)){
				userNumbers[x] = temp;
				userChoice = userChoice + temp + " ";
			}else{
				System.out.println("Invalid number. Please enter a number between 1 and 50");
				x--;
			}
		}
		
		for(int x = 0; x < 2; x++){
			System.out.println("Please enter a number between 1 and 11: ");
			int temp = scanner.nextInt();
			
			if(temp > 0 && temp < 12 && temp != temp1_11){
				userBonusNumbers[x]=temp;
				userChoice = userChoice + temp + " ";
			}else{
				System.out.println("Invalid number. Please enter a number between 1 and 11");
				x--;
			}
		}
		
		System.out.println("The numbers you have selected are: " + userChoice);
	}
	
	
	/**
	 * creates a counter and sets it to 0
	 * user enters lotto numbers
	 * counts how many times it takes for users numbers to match random numbers
	 * prints out how many lotto draws it took before user numbers were drawn
	 * @param args
	 */
	public static void main(String[] args){
		int counter = 0;
		setArray();
		
		int numbersDrawn = countNumbers();
		System.out.println("Number of numbers that were drawn: " + numbersDrawn);
		while(numbersDrawn < 7){	
			counter++;
			System.out.println("This is draw number " + counter);
			randomArray();
			numbersDrawn = countNumbers();
			System.out.println("You have matched " + numbersDrawn + " numbers");
		}

		System.out.println("it took " + counter + " draws for your numbers to come up in the lotto.");
	}
}

