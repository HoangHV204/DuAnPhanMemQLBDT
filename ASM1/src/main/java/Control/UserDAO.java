package Control;

import Model.User;
import com.vn.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static User getUser(String username) {
        User us = new User();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()) {
                us.setUserName(resultSet.getString(1));
                us.setPassWord(resultSet.getString(2));
                us.setRole(resultSet.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return us;
    }
    
    public static String getUserRole(String username) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT role FROM Users WHERE username = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet resultSet = stm.executeQuery();
            
            if(resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkLogin(String username, String password) {
        User user = getUser(username);
        if (user != null) {
            if (user.getPassWord().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
