package springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootdemo.model.User;
import springbootdemo.repository.UserRepository;

import java.util.Collection;

/**
 * Created by 81046 on 2018-06-16
 */
@RestController
@RequestMapping("/user")
//RestController 等价于controller和responseBody
public class UserController {

    private final UserRepository userRepository;

    /**
     *  这里是采用构造器的注入方式注入userRepository
     *  好处是提前初始化，不能修改
     *  @Autowired 可写可不写
     */
    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    /**
     *  http://localhost:8080/user/save?name=小石潭记
     * @param name
     * @return
     */
    @PostMapping("/save")
    public User save(@RequestParam String name){
        User user = new User();
        user.setName(name);
        if (userRepository.save(user)){
            System.out.println("保存对象成功：" + user);
        }
        return user;
    }

    @GetMapping("/list")
    public Collection<User> list(){
        return userRepository.findAll();
    }

}
