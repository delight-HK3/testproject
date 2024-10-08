package com.toypro.test.toypro.repository.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toypro.test.toypro.entity.dashboard.DashboardListEntity;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardListEntity, Integer>{

    // 게시글 리스트 조회
    @Query(value="SELECT "+
                "   a.SEQ"+ 
                "   , a.BOARD_CD"+
                "   , b.NICK_NAME"+
                "   , b.USER_ID"+
                "   , a.BOARD_TITLE"+
                "   , a.BOARD_SUBJECT"+
                "   , a.BOARD_CATG_CD"+
                "   , a.BOARD_USER_NO"+
                "   , c.CATG_NM"+
                "   , a.BOARD_CNT"+
                "   , a.REG_DATE "+
                "   , a.UPDT_DATE "+
                "FROM t_bd_table a "+ 
                "inner join t_toy_user b "+ 
                "inner join t_bd_catg c "+ 
                "where 1=1 " +
                "and a.BOARD_USER_NO = b.NO " +
                "and a.BOARD_CATG_CD = c.CATG_CD " +
                "ORDER BY a.BOARD_CATG_CD asc" , nativeQuery = true)
    List<DashboardListEntity> searchList();
    
    // 게시글 상세내용 조회
    @Query(value="SELECT "+
                "   a.SEQ"+ 
                "   , a.BOARD_CD"+
                "   , b.NICK_NAME"+
                "   , b.USER_ID"+
                "   , a.BOARD_TITLE"+
                "   , a.BOARD_USER_NO"+
                "   , a.BOARD_CATG_CD"+
                "   , a.BOARD_SUBJECT"+
                "   , c.CATG_NM"+
                "   , a.BOARD_CNT"+
                "   , a.REG_DATE "+
                "   , a.UPDT_DATE "+
                "FROM t_bd_table a "+ 
                "inner join t_toy_user b "+ 
                "inner join t_bd_catg c "+ 
                "where 1=1 " +
                "and a.BOARD_USER_NO = b.NO " +
                "and a.BOARD_CATG_CD = c.CATG_CD " +
                "and a.BOARD_CD = :boardCd" , nativeQuery = true)
    DashboardListEntity searchdetail(String boardCd);

    // 게시글 조회수 조회
    @Query(value="SELECT "+
                 " a.BOARD_CNT from t_bd_table a where 1=1 and a.BOARD_CD = :boardCd", nativeQuery = true)
    int searchViewCnt(String boardCd);

    // 게시글 조회수 1증가
    @Query(value="UPDATE "+
                 "t_bd_table SET BOARD_CNT = BOARD_CNT + 1 WHERE 1=1 and BOARD_CD = :boardCd", nativeQuery = true)
    void detailCntUp(String boardCd);
    
    // 접속한 사용자가 현재 몇개의 게시글을 작성했는지 조회
    @Query(value="select "+
                 "(count(BOARD_USER_NO) + 1) as num from t_bd_table where BOARD_USER_NO = :userNo",nativeQuery = true)
    int boardNum(int userNo);
} 