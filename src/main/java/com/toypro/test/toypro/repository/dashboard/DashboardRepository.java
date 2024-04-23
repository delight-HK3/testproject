package com.toypro.test.toypro.repository.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.dashboard.DashboardCatgEntity;
import com.toypro.test.toypro.entity.dashboard.DashboardEntity;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardEntity, Integer>{

    // 게시글 리스트 조회
    @Query(value="SELECT "+
                "   a.SEQ"+ 
                "   , a.BOARD_CD"+
                "   , b.NICK_NAME"+
                "   , b.USER_ID"+
                "   , a.BOARD_TITLE"+
                "   , a.BOARD_SUBJECT"+
                "   , c.CATG_NM"+
                "   , a.BOARD_CNT"+
                "   , a.REG_DATE "+
                "   , a.UPDT_DATE "+
                "FROM t_bd_table a "+ 
                "inner join t_toy_user b "+ 
                "inner join t_bd_catg c "+ 
                "where 1=1 " +
                "and a.BOARD_USER_ID = b.USER_ID " +
                "and a.BOARD_CATG_CD = c.CATG_CD " +
                "ORDER BY a.BOARD_CATG_CD asc" , nativeQuery = true)
    List<DashboardEntity> searchList();

    // 게시글 상세내용 조회
    @Query(value="SELECT "+
                "   a.SEQ"+ 
                "   , a.BOARD_CD"+
                "   , b.NICK_NAME"+
                "   , b.USER_ID"+
                "   , a.BOARD_TITLE"+
                "   , a.BOARD_SUBJECT"+
                "   , c.CATG_NM"+
                "   , a.BOARD_CNT"+
                "   , a.REG_DATE "+
                "   , a.UPDT_DATE "+
                "FROM t_bd_table a "+ 
                "inner join t_toy_user b "+ 
                "inner join t_bd_catg c "+ 
                "where 1=1 " +
                "and a.BOARD_USER_ID = b.USER_ID " +
                "and a.BOARD_CATG_CD = c.CATG_CD " +
                "and a.BOARD_CD = :boardCd" , nativeQuery = true)
    DashboardEntity searchdetail(String boardCd);

    // 게시글 조회수 조회
    @Query(value="SELECT a.BOARD_CNT from t_bd_table a where 1=1 and a.BOARD_CD = :boardCd", nativeQuery = true)
    int searchViewCnt(String boardCd);

    // 게시글 조회수 1증가
    @Query(value="UPDATE t_bd_table SET BOARD_CNT = :cnt WHERE 1=1 and BOARD_CD = :boardCd", nativeQuery = true)
    void detailCntUp(String boardCd, int cnt);
    
} 