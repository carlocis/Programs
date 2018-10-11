package mowitnow;

import java.util.Scanner;

public class Controls {

	// Here I created methods to help me "control" the input parameters
	
	// Checks if I insert the right number
	public static void checkChoice(int choice) {
		Scanner input = new Scanner(System.in);
		
		while((choice != 1) && (choice != 2) && (choice != 0)) {
			System.out.print("Wrong number! Please tap 0, 1 or 2: ");
			choice = input.nextInt();
		}
	}
	
	public static int  checkXDimension() {
		
		int x_max;
		while(true) {
			System.out.print("Choose the x dimension of the lawn (x>0): ");
			Scanner input = new Scanner(System.in);
			try {
				x_max = input.nextInt();
				
				if(x_max < 1) {
					System.out.println("The value should be greater than 0. Please repeat.");
					continue;
				}
				
				break;
			} catch (Exception e) {
				System.out.println("Value for x not valid. Repeat.");
				continue;
			}
		}
		return x_max;
		
	}

	public static int checkYDimension() {
		
		int y_max;
		while(true) {
			System.out.print("Choose the y dimension of the lawn (y>0): ");
			Scanner input = new Scanner(System.in);
			try {
				y_max = input.nextInt();
				
				if(y_max < 1) {
					System.out.println("The value should be greater than 0. Please repeat.");
					continue;
				}
				
				break;
			} catch (Exception e) {
				System.out.println("Value for y not valid. Repeat.");
				continue;
			}
		}
		return y_max;
		
	}
	
	
	public static int  checkNumberMowers() {
		
		int number_of_mowers;
		while(true) {
			System.out.print("How many mowers do you want to use? ");
			Scanner input = new Scanner(System.in);
			try {
				number_of_mowers = input.nextInt();
				
				if(number_of_mowers < 1) {
					System.out.println("The value should be greater than 0. Please repeat.");
					continue;
				}
				
				break;
			} catch (Exception e) {
				System.out.println("Value for mowers not valid. Repeat.");
				continue;
			}
		}
		return number_of_mowers;
		
	}
	
	// Check that the initial position of the mower is greater than 0
	// but small than the x_max
	public static int checkXPosition(int i, int x_max) {
		
		int x_initial;
		while(true) {
			System.out.print("Choose the x position on the mower "+i+": ");
			Scanner input = new Scanner(System.in);
			try {
				x_initial = input.nextInt();
				
				if(x_initial < 0 || x_initial > x_max) {
					System.out.println("The value should be >= 0 and < "+x_max+". Please repeat.");
					continue;
				}
				
				break;
			} catch (Exception e) {
				System.out.println("Value x position not valid. Repeat.");
				continue;
			}
		}
		return x_initial;	
	}
	
	
	// Check that the initial position of the mower is greater than 0
	// but small than the y_max
	public static int checkYPosition(int i, int y_max) {
		
		int y_initial;
		while(true) {
			System.out.print("Choose the y position on the mower "+i+": ");
			Scanner input1 = new Scanner(System.in);
			try {
				y_initial = input1.nextInt();
				
				if(y_initial < 0 || y_initial > y_max) {
					System.out.println("The value should be >= 0 and < "+y_max+". Please repeat.");
					continue;
				}
				
				break;
			} catch (Exception e) {
				System.out.println("Value y position not valid. Repeat.");
				continue;
			}
		}
		return y_initial;	
	}
	
	
	public static char checkOrientation(int i) {
		Scanner input = new Scanner(System.in);
		System.out.print("Choose the orientation on the mower "+i+" [N,E,W,S]: ");
		char orientation_initial = input.next().charAt(0);

		while(orientation_initial != 'N' 
				&& orientation_initial != 'E'
				&& orientation_initial != 'W'
				&& orientation_initial != 'S') {
			
			System.out.print("Wrong char. Insert correct one [N,E,W,S]: ");
			orientation_initial = input.next().charAt(0);
		}
		return orientation_initial;
	}
	
	
	public static int checkNumberCommands() {
		
		int number_of_commands;
		while(true) {
			System.out.print("How many commands you want to insert? ");
			Scanner input = new Scanner(System.in);
			try {
				number_of_commands = input.nextInt();
				
				if(number_of_commands < 1) {
					System.out.println("The value should be greater than 0. Please repeat.");
					continue;
				}
				
				break;
			} catch (Exception e) {
				System.out.println("Value for number of commands not valid. Repeat.");
				continue;
			}
		}
		return number_of_commands;
		
	}
	
	
	public static char checkCommands() {
		Scanner input = new Scanner(System.in);
		System.out.print("Choose next command [D,G,A]: ");
		char command = input.next().charAt(0);

		while(command != 'A' 
				&& command != 'G'
				&& command != 'D') {
			
			System.out.print("Wrong char. Insert correct one [D,G,A]: ");
			command = input.next().charAt(0);
		}
		return command;
	}
	
}
