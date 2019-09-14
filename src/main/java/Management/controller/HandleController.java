package Management.controller;

import Management.dao.HandleDao;
import Management.domain.service.impl.HandleService;
import Management.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@CrossOrigin
@RestController()
@RequestMapping("/handle")
public class HandleController {
    @Autowired
    HandleService handleService;

    @PutMapping(value = "/{shareTeamId}/teamShare/{status}")
    public boolean handleTeamShare(@PathVariable("shareTeamId")BigInteger shareTeamId,
                         @PathVariable("status")Integer status,
                           @RequestAttribute("user") User user){
//        if(user.getRole().equalsIgnoreCase("teacher"))
           handleService.handleTeamShare(shareTeamId,status);
           return true;
    }


}
