import com.gameshop.Controller.UserController;
import com.gameshop.entity.User;
import com.gameshop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class Test {
   @Autowired
   UserRepo userRepo;

   //UserController userController = new UserController();
    @org.junit.Test
    public void createUser(){
     //  User user = new User("sbc","cssdc","vdsa");
      //  System.out.println(userController.createUser(user));
    }
}
