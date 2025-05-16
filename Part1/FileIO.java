package Part1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	public FileIO() {

	}
	ArrayList<Product> products = new ArrayList<>();
	
	

	public void readData() {
		
		Scanner reader = null;

		try {
			reader = new Scanner(new FileInputStream("TradeData.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			return;
		}

		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			if (line.isEmpty())
				continue;

			String[] parts = line.split(",");

			if (parts.length != 4) {
				System.out.println("Invalid line: " + line);
				continue; // Skip lines with incorrect format
			}

			String product = parts[0];
			String country = parts[1];
			String category = parts[2];
			double price = Double.parseDouble(parts[3]);

			products.add(new Product(product, country, category, price));

		}

		reader.close();
		
		products.trimToSize();

		for (Product p : products) {
			p.applyTariff();
		}
		
	}

//	Method to sort according to product name
	public void sortByName(ArrayList<Product> products) {

		for (int i = 0; i < products.size() - 1; i++) {
			int smallestIndex = i;
			for (int j = i + 1; j < products.size(); j++) {

				if (products.get(j).compareTo(products.get(smallestIndex)) < 0) {
					smallestIndex = j;
				}
			}

			Product temp = products.get(i);
			products.set(i, products.get(smallestIndex));
			products.set(smallestIndex, temp);
		}
	}

	public void writeUpdatedData() { 
		sortByName(products);
//		Open the file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream("UpdatedTradeData.txt"));
			for (Product p : products) {
				writer.println(
						p.getProductName() + "," + p.getCountry() + "," + p.getCategory() + "," + p.getInitialPrice());
			}
		} catch (FileNotFoundException fnfex) {
			System.out.println("File Not found.");
		}
		
		

		writer.close();
	}
	
}
