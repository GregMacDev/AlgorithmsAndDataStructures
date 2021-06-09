package enhancement;

import java.util.Scanner;
import java.util.ArrayList;

/**
* <h1>Item bids Array List Enhancement</h1>
* This program creates and stores ItemForBid class objects in an
* ArrayList. This program has been converted from C++. The ArrayList
* can be sorted, using a quick sort algorithm, by each of the ItemForBid object's
* variables. Title, Fund, Vehicle, or Bid Amount.
* <p>
*
* @author  Greg MacPhelemy
* @version 2.0
* @since   05-28-2021
*/

public class App {
	/**
	 * Iterates through the ArrayList of items and calls displayBid() to 
	 * print out the elements of each item
	 * @param items This is the ArrayList of items to print out
	 */
	private static void displayAllBids(ArrayList<ItemForBid> items) {
		for (int i = 0; i < items.size(); i++) {
			System.out.printf("%6s%s\n"," ", "Bid " + (i+1) + ": ");
			displayBid(items.get(i));
		}
	}
	
	/**
	 * This method creates and populates the ArrayList with sample data to 
	 * conduct proof of functionality within the program.
	 * This testing method is purposely left in for proof of functionality.
	 * Please remove or comment out for full functionality.
	 * @param items This is the Array list to be populated with class objects
	 */
	private static void testPopulate(ArrayList<ItemForBid> items) {
		
		//Each line creates a new instance of ItemForBid and adds it to the ArrayList
		items.add(new ItemForBid("Coach Purse", "General", "N/A", 29.0));
		items.add(new ItemForBid("Chevy Malibu", "Enterprise", "Car", 2625.0));
		items.add(new ItemForBid("Dell Laptop", "Enterprise", "N/A", 450.0));
		items.add(new ItemForBid("Ford Ranger", "Enterprise", "Pickup", 8250.0));
		items.add(new ItemForBid("Ford F450", "Enterprise", "Box Truck", 9740.00));
		items.add(new ItemForBid("Dining Set", "General", "N/A", 175.45));
		items.add(new ItemForBid("IPhone 4", "N/A", "N/A", 75.00));
		items.add(new ItemForBid("Dell Computer Lot", "General", "N/A", 4500.00));
		items.add(new ItemForBid("Jeep Cherokee", "Enterprise", "Jeep", 1650.00));
	}
	
	/**
	 * This method prompts the user to enter the data for an item they wish to update.
	 * If the item is not found, no other data is entered and a message is printed to the screen.
	 * If the item is found, the user can update the item's title, fund, vehicle or bid amount.
	 * @param items This is the Array list of items used in the program
	 * @param scanner This is the scanner object, allowing for user input.
	 */
	private static void updateBid(ArrayList<ItemForBid> items, Scanner scanner) {
		String itemTitle = "";
		String updateColumn = "";
		String updateData = "";
		int index = 0;
		
		System.out.println("Enter title of item to update.");
		itemTitle = scanner.nextLine();
		
		// Check if item exists. searchNoSort returns -1 if no item is found.
		index = searchNoSort(items, itemTitle);
		if(index != -1) {
			// User validation for proper element to be changed.
			do {
				System.out.println("Enter element to change. (Title, Fund, Vehicle, Bid)");
				updateColumn = scanner.nextLine();
				if (!updateColumn.equalsIgnoreCase("title") && !updateColumn.equalsIgnoreCase("fund") && !updateColumn.equalsIgnoreCase("Vehicle") && !updateColumn.equalsIgnoreCase("bid")) {
					System.out.println("No element exists by that name.");
				}
			}while(!updateColumn.equalsIgnoreCase("title") && !updateColumn.equalsIgnoreCase("fund") && !updateColumn.equalsIgnoreCase("Vehicle") && !updateColumn.equalsIgnoreCase("bid"));
			
			System.out.println("Enter new data.");
			if(updateColumn.equalsIgnoreCase("bid")) {
				
				// User validation to prevent invalid data type to be entered where a double is required.
				while(!scanner.hasNextDouble()) {
					System.out.println("Invalid Price. Please Re-enter the new price.");
					scanner.nextLine();
				}
				updateData = scanner.nextLine();
				items.get(index).setBidAmount(Double.parseDouble(updateData));
			}else {
				updateData = scanner.nextLine();
				if (updateColumn.equalsIgnoreCase("Title")) {
					items.get(index).setItemTitle(updateData);
				}else if (updateColumn.equalsIgnoreCase("fund")) {
					items.get(index).setFundType(updateData);
				}else if (updateColumn.equalsIgnoreCase("Vehicle")) {
					items.get(index).setVehicleType(updateData);
				}
			}
			System.out.println(itemTitle + " updated.");
		}else {
			System.out.println("Item not found. Sorry.");
		}
	}
	
	/**
	 * This method performs a linear search to find an item in the ArrayList. It iterates through the ArrayList until it finds
	 * the item the user is looking for.
	 * @param items This is the ArrayList of items being searched.
	 * @param title This is the title of the item being searched for.
	 * @return Returns the element number of the item being searched or returns -1 if search fails.
	 */
	private static int searchNoSort(ArrayList<ItemForBid> items, String title) {
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).getItemTitle().equalsIgnoreCase(title)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * This method deletes an item based on the item title the user inputs.
	 * @param items This sit he ArrayList of items
	 * @param scanner This is the scanner object to accept user input
	 */
	private static void deleteBid(ArrayList<ItemForBid> items, Scanner scanner) {
		String itemTitle = "";
		int index = 0;
		
		System.out.println("Enter title of item to delete.");
		itemTitle = scanner.nextLine();
		
		// Check if item exists. searchNoSort returns -1 if no item is found.
		index = searchNoSort(items, itemTitle);
		if (index != -1) {
			items.remove(index);
			System.out.println(itemTitle + " deleted.");
		}else {
			System.out.println("Item not found. Sorry.");
		}
	}
	
	/**
	 * This method presents the user with a list of ItemForBid elements to search on. The user's choice is used to call
	 * searchResults that finds the items to search for and packages them. This method then prints those results to the screen.
	 * @param items This is the ArrayList of ItemForBid objects
	 * @param scanner This is the scanner object that allows for user input.
	 */
	private static void searchBids(ArrayList<ItemForBid> items, Scanner scanner) {
		int userChoice = 0;
		String userData = "";
		
		while(userChoice != 5) {
			System.out.println("Search for....");
			System.out.println("1. Title");
			System.out.println("2. Fund");
			System.out.println("3. Vehicle");
			System.out.println("4. Bid Amount");
			System.out.println("5. Back...");
		
		// Validating user input
			do {
				// Prevents invalid input by checking for an int input from the user
				while(!scanner.hasNextInt()) {
					System.out.println("Number not entered. Please enter a valid number.");
					scanner.next();
				}
				userChoice = scanner.nextInt();
				
				// White listing user input
				if(userChoice !=1 && userChoice !=2 && userChoice !=3 && userChoice !=4 && userChoice !=5) {
					System.out.println("Invalid menu option. Please enter a valid menu option.");
				}
				//White listing conditional for do/while loop
			} while (userChoice !=1 && userChoice !=2 && userChoice !=3 && userChoice !=4 && userChoice !=5);
			
			// Clear scanner buffer
			scanner.nextLine();			
			
			switch(userChoice) {
			case 1:	// Search for Title
				System.out.println("Enter value to search for.");
				userData = scanner.nextLine();
				displayAllBids(searchResults(items, "name", userData));
				userChoice = 5;
				break;
			case 2:	// Search for Fund
				System.out.println("Enter value to search for.");
				userData = scanner.nextLine();
				displayAllBids(searchResults(items, "fund", userData));
				userChoice = 5;
				break;
			case 3:	// Search for Vehicle
				System.out.println("Enter value to search for.");
				userData = scanner.nextLine();
				displayAllBids(searchResults(items, "vehicle", userData));
				userChoice = 5;
				break;
			case 4:	// Search for Bid Amount
				System.out.println("Enter MAX value to search for.");
				// User validation to prevent invalid data type to be entered where a double is required.
				while(!scanner.hasNextDouble()) {
					System.out.println("Invalid value. Must be a number. Please Re-enter the new price.");
					scanner.nextLine();
				}
				userData = scanner.nextLine();
				displayAllBids(searchResults(items, "bid", userData));
				userChoice = 5;
				break;
			}
		}
		
	}
	
	/**
	 * This method finds all objects that match the search terms and packages them into a new ArrayList. For Bid Amount, the user enters
	 * a MAX number and the method prints out all objects that contain a Bid Amount less than the MAX number.
	 * @param items This is the ArrayList to be searched
	 * @param element This is the element of the ArrayList object being searched. (Title, Fund, Vehicle, Bid Amount)
	 * @param data This is the data that is being searched.
	 * @return This returns an ArrayList containg all the search result objects.
	 */
	private static ArrayList<ItemForBid> searchResults(ArrayList<ItemForBid> items, String element, String data) {
		ArrayList<ItemForBid> list = new ArrayList<ItemForBid>();
		
		for (int i = 0; i < items.size(); i++) {
			if(element.equals("name")) {
				if(items.get(i).getItemTitle().equalsIgnoreCase(data)) {
				list.add(items.get(i));
				}
			}else if(element.equals("fund")) {
				if(items.get(i).getFundType().equalsIgnoreCase(data)) {
				list.add(items.get(i));
				}
			}if(element.equals("vehicle")) {
				if(items.get(i).getVehicleType().equalsIgnoreCase(data)) {
				list.add(items.get(i));
				}
			}if(element.equals("bid")) {
				if(items.get(i).getBidAmount() < Double.parseDouble(data)) {
				list.add(items.get(i));
				}
			}
		}
		
		// Calls quicksort on either bid amount or title for easier to read search results.
		if(element.equals("bid")) {
			quickSort(list, 0, list.size() -1, 4);
		}else {
			quickSort(list, 0, list.size() -1, 1);
		}
		return list;
	}
	
	/**
	 * This method prints out the elements of an ItemForBid object
	 * @param passedItem This is the ItemForBid object to be printed to the screen.
	 */
	private static void displayBid(ItemForBid passedItem) {
		//String bid = String.format("%.2f", passedItem.getBidAmount());
		
		System.out.printf("%-12s%s\n","Title: ", passedItem.getItemTitle());
		System.out.printf("%-12s%s\n","Fund: ", passedItem.getFundType());
		System.out.printf("%-12s%s\n","Vehicle: ", passedItem.getVehicleType());
		System.out.printf("%-12s%.2f\n\n","Bid Amount: $", passedItem.getBidAmount());
	}
	
	/**
	 * This method gathers information from the user, stores it as an ItemForBid
	 * object and returns the object.
	 * @param scanner This is the scanner object that reads a user's input
	 * @return newItem This returns the object containing user input data.
	 */
	private static ItemForBid getBid(Scanner scanner) {
		
		// Creates a new ItemForBid object
		ItemForBid newItem = new ItemForBid();
		
		// Collects user input data for each of the object's variables
		System.out.println("Enter title: ");
		newItem.setItemTitle(scanner.nextLine());
		
		System.out.println("Enter fund: ");
		newItem.setFundType(scanner.nextLine());
		
		System.out.println("Enter vehicle: ");
		newItem.setVehicleType(scanner.nextLine());
		
		System.out.println("Enter amount: ");
		// User validation to prevent invalid data type to be entered where a double is required.
		while(!scanner.hasNextDouble()) {
			System.out.println("Invalid Bid Amount. Must be a number. Please Re-enter the new price.");
			scanner.nextLine();
		}
		newItem.setBidAmount(scanner.nextDouble());
		
		return newItem;
	}
	
	/**
	 * This method creates the secondary menu for a user to choose which variable to sort
	 * the list on. Title: 1 - Fund: 2 - Vehicle: 3 - Bid Amount: 4
	 * @param Items This is the ArrayList of bids
	 * @param scanner This is the scanner object that reads a user's input
	 */
	private static void sortBids(ArrayList<ItemForBid> Items, Scanner scanner) {
		int userChoice = 0;
		
		while(userChoice != 5) {
			System.out.println("Sort Bids by...");
			System.out.println("1. Title");
			System.out.println("2. Fund");
			System.out.println("3. Vehicle");
			System.out.println("4. Bid Amount");
			System.out.println("5. Back...");
		
		// Validating user input
			do {
				// Prevents invalid input by checking for an int input from the user
				while(!scanner.hasNextInt()) {
					System.out.println("Number not entered. Please enter a valid number.");
					scanner.next();
				}
				userChoice = scanner.nextInt();
				
				// White listing user input
				if(userChoice !=1 && userChoice !=2 && userChoice !=3 && userChoice !=4 && userChoice !=5) {
					System.out.println("Invalid menu option. Please enter a valid menu option.");
				}
				//White listing conditional for do/while loop
			} while (userChoice != 1 && userChoice != 2 && userChoice !=3 && userChoice !=4 && userChoice !=5);
			
			// Clear scanner buffer
			scanner.nextLine();
			
			// User input switch control. calls quickSort method based on variable to sort on, and prints out the sorted list
			// Title: 1 - Fund: 2 - Vehicle: 3 - Bid Amount: 4
			switch(userChoice) {			
			case 1:
				quickSort(Items, 0, Items.size() -1, 1);
				System.out.println("Sorted by Title\n");
				displayAllBids(Items);
				userChoice = 5;
				break;
			case 2:
				quickSort(Items, 0, Items.size() -1, 2);
				System.out.println("Sorted by Fund\n");
				displayAllBids(Items);
				userChoice = 5;
				break;
			case 3:
				quickSort(Items, 0, Items.size() -1, 3);
				System.out.println("Sorted by Vehicle\n");
				displayAllBids(Items);
				userChoice = 5;
				break;
			case 4:
				quickSort(Items, 0, Items.size() -1, 4);
				System.out.println("Sorted by Bid Amount\n");
				displayAllBids(Items);
				userChoice = 5;
				break;
			}
		}
	}
	
	/**
	 * This method partitions the ArrayList of bids into two parts, low and high
	 * @param items This is the ArrayList instance to be partitioned
	 * @param begin	This it the beginning index to partition
	 * @param end	This is the ending index to partition
	 * @param sortBy	This is the ArrayList object element to be sorted by, either Title or Bid
	 * @return This returns the end location of the partition
	 */
	public static int partition(ArrayList<ItemForBid> items, int begin, int end, int sortBy) {
		int midPoint = 0;
		ItemForBid pivot;
		ItemForBid temp;
		boolean done = false;
		
		// Find the midpoint of the passed ArrayList and assign it as the pivot position
		midPoint = begin + (end - begin) /2;
		pivot = items.get(midPoint);
		
		// while loop to partition the list
		while(!done) {
			// Switch statement that controls the sort based on the user's choice of variable to sort on.
			switch(sortBy) {
			case 1:	// Sort on Title
				// iterates from beginning of the list until an element larger than the pivot element is found
				while(items.get(begin).getItemTitle().compareTo(pivot.getItemTitle())<0) {
					begin++;
				}
				// Iterates backwards from the end of the list until an element smaller than the pivot element is found
				while(pivot.getItemTitle().compareTo(items.get(end).getItemTitle())<0) {
					end--;
				}
				
				// loop end toggle when the entire list has been iterated over
				if(begin>=end)	{
					done = true;
				}
				// Swap values of begin and end, working towards sorting the list
				else {
					temp = items.get(begin);
					items.set(begin, items.get(end));
					items.set(end, temp);
					
					begin++;
					end--;
				}
				break;
			case 2: // Sort on Fund
				// iterates from beginning of the list until an element larger than the pivot element is found
				while(items.get(begin).getFundType().compareTo(pivot.getFundType())<0) {
					begin++;
				}
				// Iterates backwards from the end of the list until an element smaller than the pivot element is found
				while(pivot.getFundType().compareTo(items.get(end).getFundType())<0) {
					end--;
				}
				
				// loop end toggle when the entire list has been iterated over
				if(begin>=end)	{
					done = true;
				}
				// Swap values of begin and end, working towards sorting the list
				else {
					temp = items.get(begin);
					items.set(begin, items.get(end));
					items.set(end, temp);
					
					begin++;
					end--;
				}
				break;
			case 3:	// Sort on Vehicle
				// iterates from beginning of the list until an element larger than the pivot element is found
				while(items.get(begin).getVehicleType().compareTo(pivot.getVehicleType())<0) {
					begin++;
				}
				// Iterates backwards from the end of the list until an element smaller than the pivot element is found
				while(pivot.getVehicleType().compareTo(items.get(end).getVehicleType())<0) {
					end--;
				}
				
				// loop end toggle when the entire list has been iterated over
				if(begin>=end)	{
					done = true;
				}
				// Swap values of begin and end, working towards sorting the list
				else {
					temp = items.get(begin);
					items.set(begin, items.get(end));
					items.set(end, temp);
					
					begin++;
					end--;
				}
				break;
			case 4: // Sort on Bid Amount
				// iterates from beginning of the list until an element larger than the pivot element is found
				while(Double.compare(items.get(begin).getBidAmount(), pivot.getBidAmount())<0) {
					begin++;
				}
				// Iterates backwards from the end of the list until an element smaller than the pivot element is found
				while(Double.compare(pivot.getBidAmount(),items.get(end).getBidAmount())<0) {
					end--;
				}
				// loop end toggle when the entire list has been iterated over
				if(begin>=end)	{
					done = true;
				}
				// Swap values of begin and end, working towards sorting the list
				else {
					temp = items.get(begin);
					items.set(begin, items.get(end));
					items.set(end, temp);
					
					begin++;
					end--;
				}
				break;
			}
		}		
		return end;
	}
	
	/**
	 * Performs a quick sort on the ArrayList of items by either title or bid.
	 * @param items This is the ArrayList instance to be sorted
	 * @param begin	This it the beginning index to sort on
	 * @param end	This is the ending index to sort on
	 * @param sortBy	This is the ArrayList object element to be sorted by, either Title or Bid
	 */
	public static void quickSort(ArrayList<ItemForBid> items, int begin, int end, int sortBy) {
		int lowElem = 0;
		
		// Base case to determine if the list is sorted
		if(begin>=end) {
			return;
		}
		
		// Calls the partition method and assigns the returned int as the last element of the lower partition
		lowElem = partition(items, begin, end, sortBy);
		
		quickSort(items, begin, lowElem, sortBy);	//Recursive call to quicksort the lower partition
		quickSort(items, lowElem + 1, end, sortBy);	//Recursive call to quicksort the upper partition
	}

	/**
	 * This is the main method that prints the menu to the screen and controls the
	 * user's navigation of the menu.
	 * @param args unused
	 */
	public static void main(String[] args) {
		
		ArrayList<ItemForBid> Items = new ArrayList<ItemForBid>();
		Scanner scanner = new Scanner(System.in);
		ItemForBid newItem = new ItemForBid();
		int userChoice = 0;
		
		// Populate test data
		// Please remove or comment out for custom functionality
		testPopulate(Items);		
		 
		// Main menu for user selection
		while(userChoice != 9) {
			System.out.println("Menu:");
			System.out.println("1. Enter Bid");
			System.out.println("2. Display Bids");
			System.out.println("3. Update bid");
			System.out.println("4. Delete bid");
			System.out.println("5. Sort bids...");
			System.out.println("6. Search bids...");
			System.out.println("9. Exit");
			
			// Validating user input
			do {
				// Prevents invalid input by checking for an int input from the user
				while(!scanner.hasNextInt()) {
					System.out.println("Number not entered. Please enter a valid number.");
					scanner.next();
				}
				userChoice = scanner.nextInt();
				
				// White listing user input
				if(userChoice !=1 && userChoice !=2 && userChoice !=3 && userChoice !=4 && userChoice !=5 && userChoice !=6 && userChoice !=9) {
					System.out.println("Invalid menu option. Please enter a valid menu option.");
				}
				//White listing conditional for do/while loop
			} while (userChoice != 1 && userChoice != 2 && userChoice !=3 && userChoice !=4 && userChoice !=5 && userChoice !=6 && userChoice !=9);
			
			// Clear scanner buffer
			scanner.nextLine();
			
			// User input switch control.
			switch(userChoice) {
			case 1: // Enter bids
				newItem = getBid(scanner);
				Items.add(newItem);
				break;
			case 2:	// Display all bids
				displayAllBids(Items);
				break;
			case 3: // Update bid
				updateBid(Items, scanner);
				break;
			case 4:	// Delete bid
				deleteBid(Items, scanner);
				break;
			case 5:	// Sort bids by...
				sortBids(Items, scanner);
				break;
			case 6: // Search bids
				searchBids(Items, scanner);
				break;
				
			}
		}
		// Always close the scanner!
		scanner.close();
	}

}
