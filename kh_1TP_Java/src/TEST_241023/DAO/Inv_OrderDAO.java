package TEST_241023.DAO;

import TEST_241023.Common.Common;
import TEST_241023.VO.Inv_OrderVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inv_OrderDAO {

    // 기본 인스턴스필드
    // Connection : 자바와 오라클 DB 연결 설정
    // PreparedStatement : SQL문 수행 객체
    // ResultSet : PreparedStatement 동작에 대한 결과로 전달되는 DB 내용
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    Scanner sc = new Scanner(System.in);

    // 메뉴 확인 (Select)
    // 로우데이터를 받아내기 위해 ArrayList 생성
    // Inv_Order테이블과 똑같이 만들어둔 Inv_OrderVO 클래스
    public List<Inv_OrderVO> Inv_OrderSelect() {
        List<Inv_OrderVO> list = new ArrayList<>();

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT * FROM INV_ORDER";

            // 실행 결과 받기
            // 결과값이 여러 개의 레코드로 반환되어 executeQuery 사용
            rs = stmt.executeQuery(query);

            // 읽을 게 있으면 true
            // DB에 있는 내용을 rs가 받아 옴
            while (rs.next()) {
                String menuName = rs.getString("MENU_NAME");
                int price = rs.getInt("PRICE");
                String category = rs.getString("CATEGORY");

                // Inv_OrderVO 객체를 만들어서 생성자 불러오기
                // 하나로 모아서 리스트에 담기
                Inv_OrderVO vo = new Inv_OrderVO(menuName, price, category);
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
            return list;
        }
    }

    public void Inv_OrderSelectResult(List<Inv_OrderVO> list) {
        System.out.println("----- -----------------");
        System.out.println("------ 메뉴 정보 -------");
        System.out.println("-----------------------");

        for (Inv_OrderVO e : list) {
            System.out.print(e.getMenuName() + " ");
            System.out.print(e.getPrice() + " ");
            System.out.print(e.getCategory());
            System.out.println();
        }
        System.out.println("-----------------------");
    }


    // 메뉴 추가 (Insert)
    public boolean Inv_OrderInsert(Inv_OrderVO vo) {

        String sql = "INSERT INTO INV_ORDER(MENU_NAME, PRICE, CATEGORY VALUES (?, ?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getMenuName());
            pstmt.setInt(2, vo.getPrice());
            pstmt.setString(3, vo.getCategory());

            // INSERT 해당 함수
            pstmt.executeUpdate();

            return true;

            // 예외 처리
        } catch (Exception e) {
            e.printStackTrace();
            return false;

            // 무조건 불려짐
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 메뉴 수정 (Update)
    public boolean Inv_OrderUpdate(Inv_OrderVO vo) {

        String sql = "UPDATE INV_ORDER SET PRICE = ?, CATEGORY = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getPrice());
            pstmt.setString(2, vo.getCategory());

            // UPDATE 해당 함수
            pstmt.executeUpdate();

            return true;

            // 예외 처리
        } catch (Exception e) {
            e.printStackTrace();
            return false;

            // 무조건 불려짐
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 메뉴 삭제 (Delete)
    public boolean Inv_OrderDelete(Inv_OrderVO vo) {

        String sql = "DELETE FROM INV_ORDER WHERE MENU_NAME = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getMenuName());

            // DELETE 해당 함수
            pstmt.executeUpdate();

            return true;

            // 예외 처리
        } catch (Exception e) {
            e.printStackTrace();
            return false;

            // 무조건 불려짐
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }


}