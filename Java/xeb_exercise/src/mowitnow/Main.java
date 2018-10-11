package mowitnow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		boolean exit = false;

		while(!exit) {

			Scanner input = new Scanner(System.in);

			System.out.println("*****************************");
			System.out.println("Welcome to MowItNow challenge!");
			System.out.println("*****************************");
			System.out.println("How do you want to mow your lawn today?");
			System.out.println("Press [1] to input new settings");
			System.out.println("Press [2] for default settings");
			System.out.println("Press [0] to exit.");
			System.out.print("Insert your choice: ");

			int choice;


			try {

				// Checking that the choice is valid
				choice = input.nextInt();
				Controls.checkChoice(choice);

				System.out.println("You chose: "+choice);


				switch (choice) {
				case 0:
					System.out.println("Exit.");
					exit = true;

					break;
				case 1:
					// I ask input parameters and then put them as input for my methods
					// I check that the dimensions are correct
					
					int x_max = Controls.checkXDimension();
					int y_max = Controls.checkYDimension();

					// Setting the top right corner
					Coordinates topRightCorner = new Coordinates(x_max, y_max);
					Mower.setTopRightCorner(topRightCorner);
					Mower.printTopRightCorner();

					int number_of_mowers = Controls.checkNumberMowers();

					List<Coordinates> listCoordinates1 = new ArrayList<Coordinates>();
					List<Mower> listMowers1 = new ArrayList<Mower>();
					List<char[]> commands_list = new ArrayList<char[]>();

					// Initializing the lists with input data
					for(int i=0; i<number_of_mowers; i++) {
						int x_initial = Controls.checkXPosition(i, x_max);
						int y_initial = Controls.checkYPosition(i, y_max);
						char orientation_initial = Controls.checkOrientation(i);

						System.out.println("Adding values to the list of coordinates");
						Coordinates initialPoint = new Coordinates(x_initial, y_initial, orientation_initial);
						listCoordinates1.add(initialPoint);

						System.out.println("Choose the commands of the mower "+i+".");
						int number_of_commands = Controls.checkNumberCommands();

						char[] commands_array = new char[number_of_commands];

						for(int j=0; j<number_of_commands; j++) {
							commands_array[j] = Controls.checkCommands();
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

					exit = true;
					break;
				case 2: 

					// I get the path of the file I save the File and I pass to the parser
					exit = true;
					URL path = Main.class.getResource("data2.txt");
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

					exit = true;
					break;

				}

			} catch (Exception e) {
				System.out.println("You inserted a number (case 1) or the file was not found (case 2). \n");
				continue;
			}


		}



	}
}
