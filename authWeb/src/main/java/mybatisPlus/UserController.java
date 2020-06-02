package mybatisPlus;

import com.jisheng.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("getAllUser")
    public List<User> getAllUser(){
        List<User> allUser = userMapper.getAllUser();
        return allUser;
    }
}
