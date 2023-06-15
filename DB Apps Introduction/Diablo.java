import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) throws SQLException {
        Scanner scanner= new Scanner( System.in);

        Properties prop= new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password","mysqljava18!");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo",prop);

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        PreparedStatement query= connection.prepareStatement( "SELECT user_name, first_name, last_name, " +
                "(SELECT COUNT(*) FROM users_games WHERE user_id = u.id) AS games_count " +
                "FROM users AS u " +
                "WHERE user_name = ?");
        query.setString(1,username);
        ResultSet rs= query.executeQuery();
        if (rs.next()){
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int gameCount = rs.getInt("games_count");
            System.out.printf("User: %s%n%s %s has played %d games\n",username,firstName,lastName,gameCount);
        }else System.out.println("No such user exists");
    }
}
