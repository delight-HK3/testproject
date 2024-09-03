package com.toypro.test.toypro.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.account.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

    // 회원가입 - 중복 아이디 찾기
    @Query(value="select "+
                 " IF( USER_ID = :userId , 'T', 'F' ) from t_toy_user where USER_ID = :userId", nativeQuery = true)
    String searchUser(String userId);

    // 회원가입 - 중복 닉네임 찾기
    @Query(value="select "+
                 " IF( NICK_NAME = :nickName , 'T', 'F' ) from t_toy_user where NICK_NAME = :nickName" , nativeQuery = true)
    String searchNickName(String nickName);

    // 회원가입 - 유저번호 찾기
    @Query(value="select "+
                 " NO from t_toy_user where USER_ID = :userId", nativeQuery = true)
    int searchUserNo(String userId);
} 
