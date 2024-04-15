package com.toypro.test.toypro.repository.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardEntity, Integer>{

    // 게시글 리스트 조회
    @Query(value="SELECT "+
                "   a.SEQ"+ 
                "   , a.BOARD_CD"+
                "   , a.BOARD_TITLE"+
                "   , (select CATG_NM from t_bd_catg where CATG_CD = a.BOARD_CATG_CD) as CATG_NM "+
                "   , a.BOARD_SUBJECT"+
                "   , a.BOARD_SUBJECT_NM"+
                "   , a.BOARD_CNT "+
                "   , DATE_FORMAT(a.REG_DATE, '%Y-%m-%d') AS REG_DATE "+
                "FROM t_bd_table a " + 
                "ORDER BY " +
                "a.BOARD_CATG_CD asc", nativeQuery = true)
    List<DashboardEntity> searchList();

    // 게시글 상세내용 조회
    @Query(value="SELECT "+
                 "   a.SEQ" +
                 "   , a.BOARD_CD"+
                 "   , a.BOARD_TITLE"+
                 "   , (select CATG_NM from t_bd_catg where CATG_CD = a.BOARD_CATG_CD) as CATG_NM"+
                 "   , a.BOARD_SUBJECT"+
                 "   , a.BOARD_SUBJECT_NM"+
                 "   , a.BOARD_CNT"+
                 "   , DATE_FORMAT(a.REG_DATE, '%Y-%m-%d') AS REG_DATE "+
                 "from t_bd_table a "+
                 "where 1=1 "+
                 "and a.BOARD_CD = :boardCd", nativeQuery = true)
    DashboardEntity searchdetail(String boardCd);

    // 게시글 조회수 조회
    @Query(value="SELECT a.BOARD_CNT from t_bd_table a where 1=1 and a.BOARD_CD = :boardCd", nativeQuery = true)
    int searchViewCnt(String boardCd);

    // 게시글 조회수 1증가
    @Query(value="UPDATE t_bd_table SET BOARD_CNT = :cnt WHERE 1=1 and BOARD_CD = :boardCd", nativeQuery = true)
    void detailCntUp(String boardCd, int cnt);
} 