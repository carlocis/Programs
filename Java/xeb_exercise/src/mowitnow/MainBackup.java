package mowitnow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainBackup {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to MowItNow challenge!");
		System.out.println("How do you want to mow your lawn today?");
		System.out.println("Press [1] to input new settings");
		System.out.println("Press [2] for default settings");
		System.out.println("Press [0] to exit.");
		System.out.print("Insert your choice: ");

		int choice;
		try {
			choice = input.nextInt();

			while((choice != 1) && (choice != 2) && (choice != 0)) {
				System.out.print("Wrong choice! Please tap 0, 1 or 2: ");
				choice = input.nextInt();
			}
			System.out.println("You chose: "+choice);


			switch (choice) {
			case 0:
				System.out.println("Exit.");
				break;
			case 1:
				// I need to ask input parameters and then put them as input
				// for my methods
				System.out.print("Choose the x dimension of the lawn (x>0): ");
				
				
				int x_max = input.nextInt();
				
				System.out.print("Choose the y dimension of the lawn (y>0): ");
				int y_max = input.nextInt();
				
				Coordinates topRightCorner = new Coordinates(x_max, y_max);
				Mower.setTopRightCorner(topRightCorner);
				Mower.printTopRightCorner();
				
				System.out.print("How many mowers do you want to use? ");
				int number_of_mowers = input.nextInt();
				
				List<Coordinates> listCoordinates1 = new ArrayList<Coordinates>();
				List<Mower> listMowers1 = new ArrayList<Mower>();
				List<char[]> commands_list = new ArrayList<char[]>();
				
				for(int i=0; i<number_of_mowers; i++) {
					System.out.print("Choose the x position on the mower "+i+": ");
					int x_initial = input.nextInt();
					
					System.out.print("Choose the y position on the mower "+i+": ");
					int y_initial = input.nextInt();
					
					System.out.print("Choose the orientation on the mower "+i+" [N,E,W,S]: ");
					char orientation_initial = input.next().charAt(0);
					
					System.out.println("Adding values to the list of coordinates");
					Coordinates initialPoint = new Coordinates(x_initial, y_initial, orientation_initial);
					listCoordinates1.add(initialPoint);
					
					
					System.out.println("Choose the commands of the mower "+i+".");
					System.out.print("How many commands you want to insert? ");
					int number_of_commands = input.nextInt();
					
					char[] commands_array = new char[number_of_commands];
					
					for(int j=0; j<number_of_commands; j++) {
						System.out.print("Choose next command [D,G,A]: ");
						commands_array[j] = input.next().charAt(0);
					}
					
					System.out.print("You inserted the following commands: ");
					for(int k=0; k<number_of_commands; k++) {
						System.out.print(commands_array[k]);
					}
					System.out.println("");
					
					commands_list.add(commands_array);
					
				}
				
				// Here I initialise the initial point and list of commands of each mower
				Mower.initializeMowers(listMowers1, listCoordinates1, commands_list);
				Mower.printSetup(listMowers1, listCoordinates1, commands_list);
				
				System.out.println("You have successfully initialed your mowers.");
				System.out.println("Starting mowing...");
				
				Mower.followMowers(listMowers1, listCoordinates1);
				
				System.out.println("Mowers have finished their job.");
	
				break;
			case 2: 

				// I get the path of the file I save the File and I pass to the parser
				URL path = MainBackup.class.getResource("data1.txt");
				File f = new File(path.getFile());
				List<String> lines = DataParser.parseFile(f);

				List<char[]> position = DataParser.getPositionAndCommands(lines).get(0);
				List<char[]> commands = DataParser.getPositionAndCommands(lines).get(1);

				// I save the first line of my file as the right corner of the lawn
				// The coordinates without orientation are the coordinates of the top right point
				Coordinates boundaryCoordinates = DataParser.rightCornerCoordinates(lines);

				// This contains the initial coordinates of all the mowers,
				// while the list commands contains the movements (as a char[]) of each mower
				List<Coordinates> listCoordinates = DataParser.coordinatesList(position);
				List<Mower> listMowers = new ArrayList<Mower>();

				// Here I set the top right corner valid for all mowers
				Mower.setTopRightCorner(boundaryCoordinates);
				Mower.printTopRightCorner();

				// Here I initialise the initial point and list of commands of each
				Mower.initializeMowers(listMowers, listCoordinates, commands);
				Mower.printSetup(listMowers, listCoordinates, commands);
				Mower.followMowers(listMowers, listCoordinates);
				
				System.out.println("Mowers have finished their job.");
				
				break;

			}

		} catch (Exception e) {
			System.out.println("Repeat and insert a number.");
		}

	}
}
