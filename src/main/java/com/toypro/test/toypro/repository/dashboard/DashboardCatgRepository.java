package com.toypro.test.toypro.repository.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.dashboard.DashboardCatgEntity;

@Repository
public interface DashboardCatgRepository extends JpaRepository<DashboardCatgEntity, Integer>{
    
    // 카테고리 목록 출력 (일반 사용자)
    @Query(value="select * from t_bd_catg where CATG_CD != '0100'", nativeQuery = true)
    List<DashboardCatgEntity> userCatg();
    
}
