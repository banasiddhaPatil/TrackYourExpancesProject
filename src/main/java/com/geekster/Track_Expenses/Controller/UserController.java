package com.geekster.Track_Expenses.Controller;

import com.geekster.Track_Expenses.Model.User;
import com.geekster.Track_Expenses.Model.UserExpanse;
import com.geekster.Track_Expenses.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    //user sign up
    @PostMapping("SignUp")
    public String signUp(@RequestBody User user) throws NoSuchAlgorithmException {
        return userServices.signUp(user);
    }

    @PostMapping("SignIn/{email}/{password}")
    public String signIn(@PathVariable String email,@PathVariable String password){
        return userServices.signIn(email,password);
    }

    @DeleteMapping("signOut/{email}/{tokenValue}")
    public String signOut(@PathVariable String email,@PathVariable String tokenValue){
        return userServices.signOut(email,tokenValue);
    }

    @PostMapping("userExpanse")
    public String userExpances(@RequestParam String email, @RequestParam String tokenValue, @RequestBody UserExpanse userExpanse){
        return userServices.addUserExpanse(email,tokenValue,userExpanse);
    }

    @GetMapping("Get/All/Expanse")
    public List<UserExpanse> getAllExpances(@RequestParam String email,@RequestParam String tokenValue){
        return userServices.getAllExpances(email,tokenValue);
    }
}
