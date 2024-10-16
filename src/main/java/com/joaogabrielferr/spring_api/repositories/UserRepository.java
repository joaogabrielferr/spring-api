package com.joaogabrielferr.spring_api.repositories;

import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    UserDetails findByUsername(String username);



}
