package sg.edu.nus.iss;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String dirPath = "";

        if (args.length > 0) {
            dirPath = args[0];

            File newDirectory = new File(dirPath);
    
            if (!newDirectory.exists()) {
                newDirectory.mkdir();
            } else {
                System.out.println("Directory " + newDirectory + " already exists");
            }
        } else {
            dirPath = "db";
            File newDirectory = new File(dirPath);

            if (!newDirectory.exists()) {
                newDirectory.mkdir();
            } else {
                System.out.println("Directory " + newDirectory + " already exists");
            }
        }

        System.out.println("Welcome to your shopping cart");

        List<String> shoppingCart = new ArrayList<>();
        ShoppingCartDB db = new ShoppingCartDB();

        Scanner scan = new Scanner(System.in);
        String option = scan.next();
        String buffer = scan.nextLine();

        String currentUser = "";
        
        while (!option.equalsIgnoreCase("quit")) {
            switch (option) {
                case "login":
                    currentUser = buffer.trim();
                    db.userLogin(dirPath, currentUser, shoppingCart);


                    break;
                case "save":
                    db.saveShoppingCart(dirPath, currentUser, shoppingCart);
                    break;
                case "users":
                    db.listUsers(dirPath);
                    break;
                case "add":
                    List<String> bufferCart = new ArrayList<>();
                    bufferCart = Arrays.asList(buffer.split(","));

                    for (int i = 0; i < bufferCart.size(); i++) {
                        bufferCart.set(i, bufferCart.get(i).trim());
                        
                        if (!shoppingCart.contains(bufferCart.get(i))) {
                            shoppingCart.add(bufferCart.get(i));
                            System.out.println(bufferCart.get(i) + " added to your cart");
                        } else {
                            System.out.println(bufferCart.get(i) + " is already in your cart");
                        }
                    }
                    break;
                case "delete":
                    int indexToDelete = Integer.parseInt(buffer) - 1;

                    if ((indexToDelete >= shoppingCart.size()) || indexToDelete < 0) {
                        System.out.println(shoppingCart.get(indexToDelete) + " removed from your cart");
                        shoppingCart.remove(indexToDelete);
                    } else {
                        System.out.println("Incorrect item index");
                    }
                    break;
                case "list":
                    if (shoppingCart.size() > 0) {
                        for (int i = 0; i < shoppingCart.size(); i++) {
                            System.out.println((i +1) + ". " + shoppingCart.get(i));
                        }
                    } else {
                        System.out.println("Your shopping cart is empty");
                    }
                    break;
                default:
                    System.out.println("Available commands are \"login\", \"save\", \"users\", \"add\", \"delete\", \"list\"");
                    break;
            }
            option = scan.next();
            buffer = scan.nextLine();
        }
        scan.close();        
    }
}
