package com.toypro.test.toypro.repository.gis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.gis.SchooleCodeEntity;

@Repository
public interface SchooleCodeRepository extends JpaRepository<SchooleCodeEntity, Integer>{
    

}
