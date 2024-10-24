package TEST_241024.DAO;

import TEST_241024.Common.Common;
import TEST_241024.VO.StoreVO;

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

    // 메뉴 선택
    public void runCapitalInfo() {

        Scanner sc = new Scanner(System.in);
        StoreDAO dao = new StoreDAO();

        while (true) {
            System.out.println("STORE TEST");
            System.out.print("[1]계좌송금 [2] 계좌 잔액 [3]매출현황  :");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean isSuccess = dao.cpCharge(StoreDAO.cpChargeInput());
                    if (isSuccess) System.out.println("계좌에 금액이 송금되었습니다..");
                    else System.out.println("계좌에 송금이 실패했습니다.");
                    break;
                case 2:
                    dao.cpSelectResult(dao.cpSelect());
                    break;
                case 3:
                    dao.slSelectResult(dao.slSelect());
                    break;
                case 5:
                    System.out.println("프로그램을 종료 합니다.");
                    return;
            }
        }


    }




    // 지점 가용 계좌(자본금) 입금
    public boolean cpCharge(StoreVO vo) { // capital Charge
        String sql = "UPDATE STORE SET capital = capital + ? WHERE store_id = ?"; // 계좌 충전
        try {
            conn = Common.getConnection(); // 오라클 DB연결
            psmt = conn.prepareStatement(sql); // 동적인 데이터로 받을때 사용 (?)

            psmt.setInt(1, vo.getCapital()); // capital 설정
            psmt.setString(2, vo.getStoreId()); // store_id 설정

            psmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print("계좌 입금 전송 실패");
            return false;
        } finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }

    // 가용금액 표시
    public List<StoreVO> cpSelect() { // capitalSelect
        List<StoreVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 오라클 DB연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT store_id, capital FROM Store";
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

    // 매출현황
    public List<StoreVO> slSelect() { // capitalSelect
        List<StoreVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 오라클 DB연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT sales FROM Store";
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

    public static StoreVO cpChargeInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("계좌에 송금받을 지점 입력 : ");
        String storeId = sc.next();
        System.out.print("계좌에 송금할 금액 입력 : ");
        int capital = sc.nextInt();
        return new StoreVO(storeId, capital);
    }

    public List<StoreVO> cpSelectResult(List<StoreVO> list ) {
        System.out.println("-------------------------------------------------");
        System.out.println("                지점 계좌 잔액 현황");
        System.out.println("-------------------------------------------------");
        for (StoreVO e : list) {
            System.out.print(e.getStoreId() + ":" + e.getCapital() + "원");
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
        return list;
    }

    public void slSelectResult(List<StoreVO> list ) {
        System.out.println("-------------------------------------------------");
        System.out.println("                  총 매출 현황");
        System.out.println("-------------------------------------------------");
        for (StoreVO e : list) {
            System.out.print(e.getSales() + "원");
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }
}
