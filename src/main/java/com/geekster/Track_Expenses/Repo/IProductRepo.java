package com.geekster.Track_Expenses.Repo;

import com.geekster.Track_Expenses.Model.UserExpanse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepo extends JpaRepository<UserExpanse,Integer> {

}
