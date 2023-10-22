package com.geekster.Track_Expenses.Services;

import com.geekster.Track_Expenses.Model.User;
import com.geekster.Track_Expenses.Model.UserExpanse;
import com.geekster.Track_Expenses.Repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExpanseServices {
    @Autowired
    IProductRepo userExpanse;
    public void addUserExpanse(UserExpanse userExpanse1) {
        userExpanse.save(userExpanse1);
    }


}
