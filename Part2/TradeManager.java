/* 
 * This program simulates a Trade Manager that handles tariffs between different countries. 
 * It processes tariff information from a file, evaluates trade requests based on minimum tariff values, 
 * and displays the results for accepted, conditionally accepted, and rejected trade requests.
 * It also allows the user to interact with a list of tariffs, find specific tariffs, 
 * add new tariffs, remove tariffs, and perform other list operations like replacing, inserting, 
 * and copying tariffs in the list.
 */

package Part2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import Part1.FileIO;

import java.util.ArrayList;

public class TradeManager {

	public static void main(String[] args) {
		
		System.out.println("*********************************************************************************************");
		System.out.println("Welcome to the Trade Manager by Haaris Muhammad Khawaja(40261525) & Farhan Ali Khan(40287970)");
		System.out.println("*********************************************************************************************");
		System.out.println("");
		
		Scanner keyboard = new Scanner(System.in);
		FileIO a = new FileIO();

		// Reading and writing data from files
		a.readData();
		a.writeUpdatedData();


        // Create an object of TariffList to store the tariffs
		TariffList list1 = new TariffList();

		Scanner file = null;
		Scanner requestFile = null;
		double tar = 0;

//		Open tariffs.txt
		try {
			file = new Scanner(new FileInputStream("Tariffs.txt"));

		} catch (FileNotFoundException fnfex) {
			System.out.println("File not found!");
			System.exit(0);
		}

		 // Read tariffs from the Tariffs.txt file and add them to the list
		while (file.hasNextLine()) {
			String line = file.nextLine();
			Scanner lineScanner = new Scanner(line);

			try {

				String destination = lineScanner.next();
				String origin = lineScanner.next();
				String product = lineScanner.next();
				double tariff = lineScanner.nextDouble();

				Tariff temp = new Tariff(destination, origin, product, tariff);

				list1.addToStart(temp);

			} catch (NumberFormatException e) {
				System.out.println("Invalid price format on line: " + line);
			} catch (Exception e) {
				System.out.println("Unexpected error on line: " + line);
				e.printStackTrace();

			}

		}

//		close Tariffs.txt
		file.close();

		// Lists to store the trade request statuses
		ArrayList<String> accepted = new ArrayList<>();
		ArrayList<String> conditional = new ArrayList<>();
		ArrayList<String> rejected = new ArrayList<>();
		// Read TradeRequests.txt
		try {
			requestFile = new Scanner(new FileInputStream("TradeRequests.txt"));

		} catch (FileNotFoundException e) {
			System.out.println("TradeRequests.txt not found.");
			System.exit(0);
		}

		while (requestFile.hasNext()) {
			String requestID = requestFile.next();
			String origin = requestFile.next();
			String destination = requestFile.next();
			String category = requestFile.next();
			double tradeValue = requestFile.nextDouble();
			double proposedTariff = requestFile.nextDouble();

			if (list1.contains(origin, destination, category)) {

				Tariff checker = list1.find(origin, destination, category);

				String result = list1.evaluateTrade(proposedTariff, checker.getMinimumTariff());

				if (result.equals("Accepted")) {
					accepted.add(requestID + " - Accepted.");
				} else if (result.equals("Conditionally Accepted")) {
					double surcharge = tradeValue * ((checker.getMinimumTariff() - proposedTariff) / 100.0);

					conditional.add(requestID + "- Conditionally Accepted.\n Surcharge applied: $" + surcharge);
				} else if (result.equals("Rejected")) {
					rejected.add(requestID + " - Rejected.");
				}
			}
		}

//		close TradeRequests
		requestFile.close();

//		Display results of trade requests
		System.out.println(
				"------------------------------\n" + "      ACCEPTED REQUESTS\n" + "------------------------------");

		System.out.println("-" + accepted + "\n");

		System.out.println(
				"------------------------------\n" + "      CONDITIONAL REQUESTS\n" + "------------------------------");
		System.out.println("-" + conditional + "\n");

		System.out.println(
				"------------------------------\n" + "      REJECTED REQUESTS\n" + "------------------------------");
		System.out.println("-" + rejected + "\n");

//		Testing various functionalities
		System.out.println("\n---------------------\n TEST CASES \n---------------------");

		System.out.println("\nCreating new tariff (t1):");
		Tariff t1 = new Tariff("USA", "China", "Electronics", 30.0);
		System.out.println(t1.toString());

		System.out.println("\nCopying t1 to t2:");
		// testing copy constructor
		Tariff t2 = new Tariff(t1);

		System.out.println("\nAssigning new values to t2.\n");
		t2.setMinimumTariff(20.0);
		t2.setDestinationCountry("Germany");
		t2.setOriginCountry("USA");
		t2.setProductCategory("Agriculture");

		System.out.println("Current list: ");
		list1.display();

		System.out.println("Enter the information for the tariff you would like to find ");
		System.out.print("\nDestination: ");
		String inputDestination = keyboard.next();
		System.out.print("Origin: ");
		String inputOrigin = keyboard.next();
		System.out.print("Product :");
		String inputProduct = keyboard.next();

		System.out.println("Was it found?\n" + list1.find(inputOrigin, inputDestination, inputProduct));

		System.out.println("\nAdding to list 1:");

		list1.addToStart(t1);
		list1.addToStart(t2);

		list1.addToStart(new Tariff("Canada", "Mexico", "Agriculture", 10.0));

		list1.display();

		// testing getSize()
		System.out.println("\nList size: " + list1.getSize());

		System.out.println("\nInserting at an index 1...");
		Tariff t4 = new Tariff("Japan", "Yemen", "Sushi", 13.0);
		// testing insertAtIndex()
		list1.insertAtIndex(t4, 1);

		list1.display();

		System.out.println("\nList size after inserting at index: " + list1.getSize());

		System.out.println("\nDeleting from start:");
		// testing deleteFromStart()
		list1.deleteFromStart();

		list1.display();

		System.out.println("\nList size after deleting from start: " + list1.getSize());

		System.out.println("\nRemoving from index 2...");
		// testing deleteFromIndex()
		list1.deleteFromIndex(2);
		list1.display();

		System.out.println("\nList size after deleting from index: " + list1.getSize());

		System.out.println("\nReplacing at index 0");
		Tariff newTariff = new Tariff("Brazil", "Argentina", "Coffee", 20.0);
		// testing replaceAtIndex()
		list1.replaceAtIndex(newTariff, 0);
		list1.display();

		System.out.println("\nDoes the list contains: \"Mexico\", \"Canada\", \"Agriculture\"");
		// testing contains()
		System.out.println(list1.contains("Mexico", "Canada", "Agriculture"));

		System.out.println("\nDoes the list contains: \"USA\", \"China\", \"Argiculture\"");
		System.out.println(list1.contains("China", "USA", "Agriculture"));

		System.out.println("\nIs there a tariff: \"USA\", \"Germany\", \"Automobiles\"");
		// testing find()
		Tariff found = list1.find("USA", "Germany", "Automobiles");
		System.out.println(found != null ? found : "Not Found");

		System.out.println("\nFinal list size: " + list1.getSize());

		System.out.println("\nCopying list to list2:");
		// testing copy constructor
		TariffList list3 = new TariffList(list1);

		System.out.println("\nCopied list2 size: " + list3.getSize());

		System.out.println("\nDoes list1 equals list2");
		// testing equals()
		System.out.println(list1.equals(list3));

	}
}
