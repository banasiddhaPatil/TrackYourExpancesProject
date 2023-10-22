package com.geekster.Track_Expenses.Repo;

import com.geekster.Track_Expenses.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationToken extends JpaRepository<AuthenticationToken,Integer> {

    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
