package User;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    List<User> userList;

    public UserController() {
        this.userList = new ArrayList<>();
    }

    public UserController(List<User> userList) {
        this.userList = userList;
    }

    // add user
    public void addUser(User user) {
        userList.add(user);
    }

    // remove user
    public void removeUser(User user) {
        userList.remove(user);
    }

    // get particular user
    public User getUser(int userId) {
        for (User user : userList) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}
