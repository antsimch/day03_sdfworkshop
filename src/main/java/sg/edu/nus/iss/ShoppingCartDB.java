package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShoppingCartDB {

    private String loginUser = "";
    private File loginFile = null;

    public ShoppingCartDB() {
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public File getLoginFile() {
        return loginFile;
    }

    public void setLoginFile(File loginFile) {
        this.loginFile = loginFile;
    }

    public void userLogin(String dirPath, String loginUser) throws IOException {
        setLoginFile(new File(dirPath + File.separator + loginUser));
        if (!loginFile.exists()) {
            loginFile.createNewFile();
            System.out.println(loginUser + ", your cart is empty");
        } else if (loginFile.length() == 0) {
            System.out.println(loginUser + ", your cart is empty");
        } else if (loginFile.length() > 0) {
            System.out.println(loginUser + ", your cart contains the following items");
            FileReader fr = new FileReader(loginFile);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            int pos = 1;

            while (line != null) {
                System.out.println(pos + ". " + line);
                pos++;
            }
            br.close();
            fr.close();
        }
    }

    public void saveShoppingCart(String dirPath, String loginUser, List<String> shoppingCart) throws IOException {
        setLoginFile(new File(dirPath + File.separator + loginUser));
        FileWriter fw = new FileWriter(dirPath + File.separator + loginUser, true);
        // PrintWriter pw = new PrintWriter(fw);
        
        if (loginFile.exists()) {
            for (int i = 0; i < shoppingCart.size(); i++) {
                fw.append(shoppingCart.get(i));
                fw.append("\n");
            }
            System.out.println("Your cart has been saved");
        } else {
            System.out.println("Please login first");
        }

        fw.flush();
        fw.close();
    }

    public void listUsers(String dirPath) {
        File directoryPath = new File(dirPath);
        String[] listOfUsers = directoryPath.list();
        System.out.println("List of directories and users in the specified folder " + dirPath);
        for (String user : listOfUsers) {
            System.out.println(user);
        }
    }
}
