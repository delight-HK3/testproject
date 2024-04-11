package com.toypro.test.toypro.repository.signin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.signin.SigninEntity;

@Repository
public interface SignupRepository extends JpaRepository<SigninEntity, Integer>{

    @Query(value="select IF( USER_ID = :userId , 'T', 'F' ) from t_toy_user where USER_ID = :userId", nativeQuery = true)
    String searchUser(String userId);

} 
