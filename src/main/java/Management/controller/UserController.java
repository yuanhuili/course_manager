package Management.controller;

import Management.controller.vo.UserVOExt;
import Management.domain.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Management.entity.User;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@CrossOrigin
@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/password")
    public Boolean lostPassword(@RequestParam("account")String account){
        Boolean aBoolean=userService.sendMailPassword(account);
        return aBoolean;
    }

    @PutMapping(value = "/password")
    public Boolean changePassword(@RequestBody User user,@RequestAttribute("user") User user1){
        user.setId(user1.getId());
        user.setRole(user1.getRole());
        Boolean aBoolean=userService.changePassword(user);
        return aBoolean;
    }

    @GetMapping(value = "/information")
    public UserVOExt getUserInfo(@RequestAttribute("user")  User user1){
        UserVOExt userVOExt =new UserVOExt();
        User user=userService.getInfo(user1);
        BeanUtils.copyProperties(user, userVOExt);
        return userVOExt;
    }

    @PutMapping(value = "/email")
    public Boolean modifyEmail( @RequestBody User user,@RequestAttribute("user") User user1)  {
        user1.setEmail(user.getEmail());
        Boolean aBoolean=userService.updateEmail(user1);
        return aBoolean;
    }




}






