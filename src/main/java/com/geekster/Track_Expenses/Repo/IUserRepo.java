package com.geekster.Track_Expenses.Repo;

import com.geekster.Track_Expenses.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String email);
}
