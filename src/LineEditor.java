import java.util.ArrayList;
import java.util.Scanner;

public class LineEditor {
    private static ArrayList<String> myArrList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in); // Create Scanner instance

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            String command = SafeInput.getRegExString(input, "Enter command ", "[AaDdIiPpQq]"); // Pass Scanner
            switch (command.toLowerCase()) {
                case "a":
                    addItem();
                    break;
                case "d":
                    deleteItem();
                    break;
                case "i":
                    insertItem();
                    break;
                case "p":
                    printList();
                    break;
                case "q":
                    running = !quitProgram(); // Correctly set running based on quit confirmation
                    break;
            }
        }
        input.close(); // Close the Scanner when done
    }

    private static void displayMenu() {
        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem() {
        String item = SafeInput.getRegExString(input, "Enter item to add: ", ".+"); // Pass Scanner
        myArrList.add(item);
        System.out.println("Item added.");
    }

    private static void deleteItem() {
        if (myArrList.isEmpty()) {
            System.out.println("List is empty. No items to delete.");
            return;
        }
        printList();
        int index = SafeInput.getRangedInt(input, "Enter the item number to delete: ", 1, myArrList.size()) - 1; // Pass Scanner
        myArrList.remove(index);
        System.out.println("Item deleted.");
    }

    private static void insertItem() {
        if (myArrList.isEmpty()) {
            System.out.println("List is empty. Inserting at the start.");
            addItem();
            return;
        }
        printList();
        int index = SafeInput.getRangedInt(input, "Enter the location to insert the item (1 to " + (myArrList.size() + 1) + "): ", 1, myArrList.size() + 1) - 1; // Pass Scanner
        String item = SafeInput.getRegExString(input, "Enter item to insert: ", ".+"); // Pass Scanner
        myArrList.add(index, item);
        System.out.println("Item inserted.");
    }

    private static void printList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Current List:");
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ": " + myArrList.get(i));
            }
        }
    }

    private static boolean quitProgram() {
        boolean confirmQuit = SafeInput.getYNConfirm(input, "Are you sure you want to quit? (Y/N): "); // Pass Scanner
        return confirmQuit; // Return true if user chose to quit (Y), false if they chose not to (N)
    }
}
