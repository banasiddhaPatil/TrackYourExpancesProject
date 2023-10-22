package com.geekster.Track_Expenses.Services;

import com.geekster.Track_Expenses.Model.AuthenticationToken;
import com.geekster.Track_Expenses.Repo.IAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationTokenServices {

    @Autowired
    IAuthenticationToken authenticationToken;

    public void createToken(AuthenticationToken newauthenticationToken) {
        authenticationToken.save(newauthenticationToken);
    }

    public boolean authenticate(String email, String tokenValue) {
    AuthenticationToken auth=authenticationToken.findFirstByTokenValue(tokenValue);
    if(auth !=null){
        return auth.getUser().getUserEmail().equals(email);
    }
    else{
        return false;
    }
    }

    public void deleteToken(String tokenValue) {
        AuthenticationToken auth=authenticationToken.findFirstByTokenValue(tokenValue);
        if(auth!= null){
            authenticationToken.delete(auth);
        }
    }
}
