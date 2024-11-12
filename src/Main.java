import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args)
    {
        Scanner pipe = new Scanner(System.in);
        boolean quit = false;

        while (!quit)
        {
            printMenu();
            String command = SafeInput.getRegExString(pipe, "Choose an option", "[AaDdIiPpQq]");

            switch (command.toUpperCase())
            {
                case "A":
                    addItem(pipe);
                    break;
                case "D":
                    deleteItem(pipe);
                    break;
                case "I":
                    insertItem(pipe);
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit = confirmQuit(pipe);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Goodbye!");
        pipe.close();
    }

    private static void printMenu()
    {
        System.out.println("\nCurrent List: ");
        if (list.isEmpty())
        {
            System.out.println("The list is empty.");
        }
        else
        {
            for (int C = 0; C < list.size(); C++)
            {
                System.out.println((C + 1) + ". " + list.get(C));
            }
        }

        System.out.println("\nMenu Options:");
        System.out.println("A - Add an item");
        System.out.println("D - Delete an item");
        System.out.println("I - Insert an item");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItem(Scanner pipe)
    {
        String newItem = SafeInput.getNonZeroLenString(pipe, "Enter the item to add");
        list.add(newItem);
        System.out.println("Item added.");
    }

    private static void deleteItem(Scanner pipe)
    {
        if (list.isEmpty())
        {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        int itemToDelete = SafeInput.getRangedInt(pipe, "Enter the number of the item to delete", 1, list.size());
        list.remove(itemToDelete - 1);
        System.out.println("Item deleted.");
    }

    private static void insertItem(Scanner pipe)
    {
        String newItem = SafeInput.getNonZeroLenString(pipe, "Enter the item to insert");
        int insertAt = SafeInput.getRangedInt(pipe, "Enter the position to insert the item", 1, list.size() + 1);
        list.add(insertAt - 1, newItem);
        System.out.println("Item inserted.");
    }

    private static void printList()
    {
        if (list.isEmpty())
        {
            System.out.println("The list is empty.");
        }
        else
        {
            System.out.println("Current List:");
            for (int i = 0; i < list.size(); i++)
            {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    private static boolean confirmQuit(Scanner pipe)
    {
        return SafeInput.getYNConfirm(pipe, "Are you sure you want to quit?");
    }
}
