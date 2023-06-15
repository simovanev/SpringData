import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner= new Scanner(System.in);

        Properties prop = new Properties();
        String user="root";
        prop.setProperty("user",user);
        String password= "mysqljava18!";
        prop.setProperty("password",password );
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", prop);

        PreparedStatement stm = connection.prepareStatement("SELECT first_name, last_name FROM employees WHERE salary > ?");
        String salary = scanner.nextLine();
        stm.setDouble(1,Double.parseDouble(salary));
        ResultSet rs= stm.executeQuery();

        while(rs.next()){
            System.out.printf("%s  %s\n", rs.getString("first_name"), rs.getString("last_name"));
        }

    }
}