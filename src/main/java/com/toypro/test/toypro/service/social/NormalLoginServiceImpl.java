package com.toypro.test.toypro.service.social;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.dto.social.SocialAuthResponse;
import com.toypro.test.toypro.dto.social.SocialUserResponse;
import com.toypro.test.toypro.entity.account.AccountEntity;
import com.toypro.test.toypro.repository.social.NormalLoginRepository;
import com.toypro.test.toypro.type.UserType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Qualifier("LoginService")
public class NormalLoginServiceImpl implements SocialLoginService {
    
    private final NormalLoginRepository normalLoginRepository;

    @Override
    public UserType getServiceName() {
        return UserType.NORMAL;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccessToken'");
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {

        AccountEntity accountEntity = normalLoginRepository.searchUser(accessToken);
        SocialUserResponse socialUserResponse = SocialUserResponse.toLoginDTO(accountEntity);
        
        return socialUserResponse;
    }

    @Override
    public String getOauthRedirectURL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOauthRedirectURL'");
    }

}
