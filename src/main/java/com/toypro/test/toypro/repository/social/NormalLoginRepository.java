package com.toypro.test.toypro.repository.social;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.account.AccountEntity;

@Repository
public interface NormalLoginRepository extends JpaRepository<AccountEntity, Integer>{

    // 회원가입 - 중복 아이디 찾기
    @Query(value="select "+
                "NO "+
                ", USER_ID "+
                ", USER_PWD "+
                ", USER_NAME "+
                ", NICK_NAME "+
                ", USER_EMAIL "+
                ", PHONE_NO "+
                ", USER_TYPE "+
                ", REG_DATE "+
                ", UPDT_DATE "+
                "from t_toy_user "+ 
                "where USER_ID = :userId", nativeQuery = true)
    AccountEntity searchUser(String userId);

}
