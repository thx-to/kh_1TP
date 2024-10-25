package TEST_241025_취합.DAO;

import TEST_241025_취합.VO.StoreVO;
import TEST_241025_취합.Common.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;
    private Scanner sc = null;

    public StoreDAO() {
        sc = new Scanner(System.in);
    }

    // 가상 계좌 충전
    public boolean cpCharge(int i, String userId) { // capital Charge
        try {
            conn = Common.getConnection(); // 오라클 DB연결
            String sql = "UPDATE STORE SET capital = capital + ? WHERE store_id = ?"; // 계좌 충전
            psmt = conn.prepareStatement(sql); // 동적인 데이터로 받을때 사용 (?)
            psmt.setInt(1, i); // capital 설정
            psmt.setString(2, userId); // store_id 설정

            if (psmt.executeUpdate() == 0) { // UPDATE
                throw new Exception();
            };
            Common.close(psmt);
            Common.close(conn);
            return true;
        } catch (Exception e) {
            System.out.println("계좌 입금 전송 실패");
            Common.close(psmt);
            Common.close(conn);
            return false;
        }
    }

    // DataBase Table Store에 store_id와 로그인 했을때 지점이 같은지 확인
    public String getCpCStoreId(String userId) {
        String storeId = "";
        try {
            conn = Common.getConnection();
            String sql = "SELECT STORE_ID FROM ACC_INFO WHERE USER_ID = '" + userId + "'";
            psmt = conn.prepareStatement(sql); //createStement 랑 prepareStatement의 차이를 공부해야한다
            rs = psmt.executeQuery();
            if(rs.next()) {
                storeId = rs.getString("STORE_ID");
            }
        } catch(Exception e) {
            System.out.println();
        }
        Common.close(rs);
        Common.close(psmt);
        Common.close(conn);
        return storeId;
    }

    // 가용금액 표시
    public List<StoreVO> cpSelect(String cpStoreId) { // capitalSelect
        List<StoreVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 오라클 DB연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT store_id, capital FROM Store WHERE store_id = '" + cpStoreId + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String storeId =rs.getString("store_id");
                int capital = rs.getInt("capital");
                StoreVO vo = new StoreVO(storeId, capital);
                list.add(vo);
            }
        } catch (Exception e) {
            System.out.println("계좌 조회 실패");
        } finally{
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }
        return list;
    }

    // DataBase Table Store에 store_id와 로그인 했을때 지점이 같은지 확인
    public String getCpSStoreId(String userId) {
        String storeId = "";
        try {
            conn = Common.getConnection();
            String sql = "SELECT STORE_ID FROM ACC_INFO WHERE USER_ID = '" + userId + "'";
            psmt = conn.prepareStatement(sql); //createStement 랑 prepareStatement의 차이를 공부해야한다
            rs = psmt.executeQuery();
            if(rs.next()) {
                storeId = rs.getString("STORE_ID");
            }
        } catch(Exception e) {
            System.out.println();
        }
        Common.close(rs);
        Common.close(psmt);
        Common.close(conn);
        return storeId;
    }

    // 매출현황
    public List<StoreVO> slSelect(String slStoreId) { // capitalSelect
        List<StoreVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 오라클 DB연결
            stmt = conn.createStatement(); // statement 생성
            String query =  "SELECT sales FROM Store WHERE store_id = '" + slStoreId + "'";
            System.out.println(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int sales = rs.getInt("sales");
                StoreVO vo = new StoreVO(sales);
                list.add(vo);
            }
        } catch (Exception e) {
            System.out.println("총 매출 현황 조회 실패");
        } finally{
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }
        return list;
    }
    // DataBase Table Store에 store_id와 로그인 했을때 지점이 같은지 확인
    public String getSlStoreId(String userId) {
        String storeId = "";
        try {
            conn = Common.getConnection();
            String sql = "SELECT STORE_ID FROM ACC_INFO WHERE USER_ID = '" + userId + "'";
            psmt = conn.prepareStatement(sql); //createStement 랑 prepareStatement의 차이를 공부해야한다
            rs = psmt.executeQuery();
            if(rs.next()) {
                storeId = rs.getString("STORE_ID");
            }
        } catch(Exception e) {
            System.out.println();
        }
        Common.close(rs);
        Common.close(psmt);
        Common.close(conn);
        return storeId;
    }

    public int cpChargeInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("계좌에 송금할 금액 입력 : ");
        return sc.nextInt();
    }


    public List<StoreVO> cpSelectResult(List<StoreVO> list ) {
        System.out.println("-------------------------------------------------");
        for (StoreVO e : list) {
            System.out.print("계좌 잔액 현황 : " + e.getCapital() + "원");
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
        return list;
    }

    public void slSelectResult(List<StoreVO> list ) {
        System.out.println("-------------------------------------------------");
        for (StoreVO e : list) {
            System.out.print("총 매출 현황 : " + e.getSales() + "원");
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }
}