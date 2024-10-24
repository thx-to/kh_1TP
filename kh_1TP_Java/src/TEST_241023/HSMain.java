package TEST_241023;

import TEST_241024.DAO.Inv_OrderDAO;
import TEST_241024.VO.Inv_OrderVO;

import java.util.List;
import java.util.Scanner;

/*
public class HSMain {
    public static void main(String[] args) {
        menuSelect();
    }

    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);

        Inv_OrderDAO dao = new Inv_OrderDAO();

        while (true) {
            System.out.println("INV_ORDER TEST");
            System.out.print("[1]메뉴조회 [2]메뉴추가 [3]메뉴수정 [4]메뉴삭제 [5]종료 : ");
            int sel = sc.nextInt();
            boolean isSuccess = false;

            switch (sel) {
                case 1 :
                    List<Inv_OrderVO> list = dao.Inv_OrderSelect();
                    dao.Inv_OrderSelectResult(list);
                    break;
                case 2 :
                    isSuccess = dao.Inv_OrderInsert(menuInsertInput());
                    if (isSuccess) System.out.println("메뉴 등록 성공");
                    else System.out.println("메뉴 등록 실패");
                    break;
                case 3 :
                    isSuccess = dao.Inv_OrderUpdate(menuUpdateInput());
                    if (isSuccess) System.out.println("메뉴 수정 성공");
                    else System.out.println("메뉴 수정 실패");
                    break;
                case 4 :
                    isSuccess = dao.Inv_OrderDelete(menuDeleteInput());
                    if (isSuccess) System.out.println("메뉴 삭제 성공");
                    else System.out.println("메뉴 삭제 실패");
                    break;
                case 5 :
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    public static Inv_OrderVO menuInsertInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("추가하실 메뉴 정보를 입력하세요.");
        System.out.print("메뉴 이름 : ");
        String menuName = sc.next();
        System.out.print("메뉴 가격 : ");
        int price = sc.nextInt();
        System.out.print("카테고리(버거, 사이드, 음료) : ");
        String category = sc.next();

        return new Inv_OrderVO(menuName, price, category);
    }

    public static Inv_OrderVO menuUpdateInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정하실 메뉴 정보를 입력하세요, 이름은 수정할 수 없습니다.");
        System.out.print("메뉴 이름 : ");
        String menuName = sc.next();
        System.out.print("변경할 가격 : ");
        int price = sc.nextInt();
        System.out.print("변경할 카테고리(버거, 사이드, 음료) : ");
        String category = sc.next();

        return new Inv_OrderVO(menuName, price, category);
    }

    public static Inv_OrderVO menuDeleteInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제하실 메뉴 정보를 입력하세요.");
        System.out.print("삭제할 메뉴 이름 : ");
        String menuName = sc.next();

        return new Inv_OrderVO();
    }

}

 */