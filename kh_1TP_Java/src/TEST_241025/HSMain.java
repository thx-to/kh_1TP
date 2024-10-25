package TEST_241025;

import TEST_241025.Common.Session;
import TEST_241025.DAO.*;
import TEST_241025.VO.Acc_InfoVO;
import TEST_241025.VO.Inv_OrderVO;
import TEST_241025.VO.Order_RecordVO;

import java.util.List;
import java.util.Scanner;

import static TEST_241025.Common.Session.storeId;

public class HSMain {

    private static String userId = ""; // 유저 id
    private static String adminId = "";
    private static String hqId = "";

    public static void main(String[] args) {
        menuSelect();
    }


    public static void menuSelect() {

        Scanner sc = new Scanner(System.in);
        Acc_InfoDAO aiDAO = new Acc_InfoDAO();

        boolean isLoggedIn = false;
        boolean isCustomerLoggedIn = false;
        boolean isAdminLoggedIn = false;
        boolean isHQLoggedIn = false;
        boolean storeCapital = false;

        System.out.println("버거집에 오신 것을 환영합니다.");
        System.out.println("이용을 위해서는 로그인을 해야합니다. 회원이 아니라면 가입해주세요 :)");

        while (true) {

            System.out.println("== 메인 페이지 접속 ==");
            System.out.print("[1]로그인 [2]회원가입 [3]프로그램 종료 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:

                    System.out.print("아이디 : ");
                    userId = sc.next();
                    System.out.print("비밀번호 : ");
                    String userPw = sc.next();
                    int authLevel = aiDAO.checkUserAuthLevel(userId, userPw);

                    if (authLevel == 3) {
                        System.out.println("CUSTOMER 로그인 성공!");
                        isLoggedIn = true;
                        isCustomerLoggedIn = true;
                        customerMenu();
                    } else if (authLevel == 1) {
                        System.out.println("ADMIN 로그인 성공!");
                        isLoggedIn = true;
                        isAdminLoggedIn = true;
                        adminMenu();
                    } else if (authLevel == 2) {
                        System.out.println("HQ 로그인 성공!");
                        isLoggedIn = true;
                        isHQLoggedIn = true;
                        hqMenu();
                    } else {
                        System.out.println("아이디 또는 비밀번호를 확인해주세요.");
                        }
                        break;

                case 2:
                    Acc_InfoDAO.Acc_InfoInsert();
                    break;

                case 3:
                    System.out.println("프로그램을 종료합니다");
                    return;

                default:
                    System.out.println("메뉴를 잘못 선택하셨습니다.");
                }
            }
        }


    // CUSTOMER 접속 메뉴
    public static void customerMenu() {

        Scanner sc = new Scanner(System.in);
        boolean isCustomerLoggedIn = true;
        InvDAO invDAO = new InvDAO();
        boolean customerMyPage = false;

        while (isCustomerLoggedIn) {
            System.out.println("==== CUSTOMER 페이지 접속 ====");
            System.out.print("[1]주문하기 [2]주문내역 확인 [3]장바구니 [4]마이페이지 [9]로그아웃 : ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    invDAO.choiceStore();
                    invDAO.cusOrder();
                    break;
                case 2:
                    List<Order_RecordVO> list = Order_RecordDAO.Order_RecordSelect(userId);
                    Order_RecordDAO.Order_RecordSelectResult(list);
                    break;
                case 3:
                    invDAO.inCart(userId);
                    break;
                case 4:
                    customerMyPage = true;
                    break;
                case 9:
                    System.out.println("로그아웃 합니다");
                    isCustomerLoggedIn = false;
                    menuSelect();
                    break;
                default:
                    System.out.println("메뉴를 잘못 선택하셨습니다.");
            }
            if (customerMyPage) break;
        }

        while (customerMyPage) {
            System.out.println("==== CUSTOMER 마이페이지 접속 ====");
            System.out.print("[1]회원정보 수정 [2]회원탈퇴 [3]뒤로가기 [9]로그아웃 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    MyPageDAO.membUpdate(new Acc_InfoVO(), userId);
                    break;
                case 2:
                    MyPageDAO.membDelete(new Acc_InfoVO());
                    System.out.println("회원탈퇴 처리 되었습니다");
                    isCustomerLoggedIn = false;
                    break;
                case 3:
                    customerMenu();
                    break;
                case 9:
                    System.out.println("로그아웃 합니다");
                    isCustomerLoggedIn = false;
                    break;
                default:
                    System.out.println("메뉴를 잘못 선택하셨습니다.");
            }
        }

    }

    // ADMIN 접속 메뉴
    public static void adminMenu() {

        Scanner sc = new Scanner(System.in);
        boolean isAdminLoggedIn = true;
        StoreDAO sDAO = new StoreDAO();
        Order_RecordDAO orDAO = new Order_RecordDAO();
        InvDAO iDAO = new InvDAO();
        boolean storeCapital = false;

        while (isAdminLoggedIn) {
            System.out.println("==== ADMIN 페이지 접속 ====");
            System.out.print("[1]발주 [2]재고확인 [3]매출현황 [4]매장계좌 [5]로그아웃 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    iDAO.ownerOrder();
                    break;
                case 2:
                    iDAO.invCheck(Session.storeId);
                    break;
                case 3:
                    sDAO.slSelectResult(sDAO.slSelect(sDAO.getSlStoreId(adminId)));
                    break;
                case 4:
                    storeCapital = true;
                    break;
                case 5:
                    System.out.println("로그아웃 합니다");
                    Session.isAdminLoggedIn = false;
                    Session.loggedInUserId = null;
                    isAdminLoggedIn = false;
                    menuSelect();
                    break;
                default:
                    System.out.println("메뉴를 잘못 선택하셨습니다.");
            }
            if (storeCapital) break;
        }
        while (storeCapital) {
            System.out.println("==== ADMIN 매장 계좌 페이지 접속 ====");
            System.out.print("[1]계좌 입금 [2]계좌 잔액 현황 [3]뒤로가기 : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean isSuccess = sDAO.cpCharge(sDAO.cpChargeInput(), sDAO.getCpStoreId(adminId));
                    if (isSuccess) System.out.println("계좌에 금액이 송금되었습니다..");
                    else System.out.println("계좌에 송금이 실패했습니다.");
                    break;
                case 2:
                    sDAO.cpSelectResult(sDAO.cpSelect(sDAO.getCpStoreId(adminId)));
                    break;
                case 3:
                    storeCapital = false;
                    adminMenu();
                    break;
                default:
                    System.out.println("메뉴를 잘못 선택하셨습니다.");
            }
        }

    }

    // HQ 접속 메뉴
    public static void hqMenu() {

        Scanner sc = new Scanner(System.in);
        boolean isHQLoggedIn = true;

        while (isHQLoggedIn) {
            System.out.println("==== HQ 페이지 접속 ====");
            System.out.print("[1]메뉴조회 [2]메뉴추가 [3]메뉴수정 [4]메뉴삭제 [5]로그아웃 : ");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    List<Inv_OrderVO> list = Inv_OrderDAO.Inv_OrderSelect();
                    Inv_OrderDAO.Inv_OrderSelectResult(list);
                    break;
                case 2:
                    boolean isSuccess = Inv_OrderDAO.Inv_OrderInsert();
                    if (isSuccess) System.out.println("메뉴 등록 성공");
                    else System.out.println("메뉴 등록 실패");
                    break;
                case 3:
                    isSuccess = Inv_OrderDAO.Inv_OrderUpdate();
                    if (isSuccess) System.out.println("메뉴 수정 성공");
                    else System.out.println("메뉴 수정 실패");
                    break;
                case 4:
                    isSuccess = Inv_OrderDAO.Inv_OrderDelete();
                    if (isSuccess) System.out.println("메뉴 삭제 성공");
                    else System.out.println("메뉴 삭제 실패");
                    break;
                case 5:
                    System.out.println("로그아웃 합니다");
                    isHQLoggedIn = false;
                    menuSelect();
                    break;
                default:
                    System.out.println("메뉴를 잘못 선택하셨습니다.");
            }
        }
    }
}