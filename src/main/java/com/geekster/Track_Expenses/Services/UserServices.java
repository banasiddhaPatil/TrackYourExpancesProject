package com.geekster.Track_Expenses.Services;

import com.geekster.Track_Expenses.Model.AuthenticationToken;
import com.geekster.Track_Expenses.Model.User;
import com.geekster.Track_Expenses.Model.UserExpanse;
import com.geekster.Track_Expenses.Repo.IUserRepo;
import com.geekster.Track_Expenses.Services.EmailServices.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationTokenServices authenticationTokenServices;

    @Autowired
    UserExpanseServices userExpanseServices;

    public String signUp(User user) throws NoSuchAlgorithmException {
        String email=user.getUserEmail();
        User existingUser=userRepo.findFirstByUserEmail(email);
        if(existingUser==null){
            String password=user.getUserPassword();
            String encryptedPassword= PasswordEncrypt.encrypt(password);
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return "SignUp successfully";
        }
        else{
            return "UserAlreadyExist";
        }
    }

    public String signIn(String email, String password) {
        User existingUser=userRepo.findFirstByUserEmail(email);
        if(existingUser == null){
            return "user Not found !!!";
        }
        try{
            String encryptedPassword=PasswordEncrypt.encrypt(password);
            String userPassword=existingUser.getUserPassword();
            if(encryptedPassword.equals(userPassword)){
                AuthenticationToken authenticationToken=new AuthenticationToken(existingUser);
                if(EmailServices.sendEmail(email," to get OTP ",authenticationToken.getTokenValue())) {
                    authenticationTokenServices.createToken(authenticationToken);
                    return "user sign In";
                }
                else{
                    return "server error";
                }
            }
            else{
                return "Password does not match";
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String signOut(String email, String tokenValue) {
        if(authenticationTokenServices.authenticate(email,tokenValue)){
            authenticationTokenServices.deleteToken(tokenValue);
            return "Sign Out";
        }
        else{
            return "UnAuthenticated User";
        }
    }

    public String addUserExpanse(String email, String tokenValue, UserExpanse userExpanse) {
        if(authenticationTokenServices.authenticate(email,tokenValue)){
            User existingUser=userRepo.findFirstByUserEmail(email);
            UserExpanse userExpanse1=new UserExpanse(userExpanse);
            userExpanse1.setUser(existingUser);
            userExpanseServices.addUserExpanse(userExpanse1);
            return userExpanse.getTitle()+" Added";
        }
        return "Unauthenticated User";
    }


    public List<UserExpanse> getAllExpances(String email, String tokenValue) {
        if(authenticationTokenServices.authenticate(email,tokenValue)){
            User user=userRepo.findFirstByUserEmail(email);
            return user.getUserExpanse();
        }
        return null;
    }
}
