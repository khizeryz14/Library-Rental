package libraryrental.libraryrental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginModel {
    protected static boolean validate(String username, String seatNo,
                                      String libID, String password) throws IOException {
        String filePath = "C:\\Khizer Projects (CPP)\\Java OOP\\LibraryRental\\src\\main\\resources\\libraryrental\\libraryrental\\userData.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while(line != null) {
            String[] parts = line.split(";");

            String stUsername = parts[0].trim();
            String stSeatNo = parts[1].trim();
            String stLibID = parts[2].trim();
            String stPassword = parts[3].trim();

            if(stUsername.equals(username) && stPassword.equals(password) &&
                    stSeatNo.equals(seatNo) && stLibID.equals(libID)) {
                MenuController.sessionUserPicture = parts[4].trim();
                reader.close();
                return true;
            }
            line = reader.readLine();
        }
        reader.close();
        return false;
    }
}
