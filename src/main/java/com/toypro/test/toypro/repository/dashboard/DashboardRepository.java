package com.toypro.test.toypro.repository.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardEntity, Integer>{

    @Query(value="SELECT "+
                "   a.SEQ"+ 
                "   , a.BOARD_CD"+
                "   , a.BOARD_TITLE"+
                "   , (select CATG_NM from t_bd_catg where CATG_CD = a.BOARD_CATG_CD) as CATG_NM "+
                "   , a.BOARD_CNT "+
                "   , DATE_FORMAT(a.REG_DATE, '%Y-%m-%d') AS REG_DATE "+
                "FROM t_bd_table a " + 
                "ORDER BY " +
                "a.BOARD_CATG_CD asc", nativeQuery = true)
    List<DashboardEntity> searchList();
} 