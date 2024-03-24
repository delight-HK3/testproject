package com.toypro.test.toypro.service.gis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toypro.test.toypro.entity.gis.SchooleCodeEntity;
import com.toypro.test.toypro.repository.gis.SchooleCodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GisService {

    @Autowired
    private final SchooleCodeRepository schooleCodeRepository; 

    public List<SchooleCodeEntity> getCodeList(){
        return schooleCodeRepository.findAll();
    }
}
