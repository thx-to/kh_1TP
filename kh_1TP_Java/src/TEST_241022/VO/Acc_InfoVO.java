package TEST_241022.VO;

import java.sql.Date;

public class Acc_InfoVO {
    private String userId;
    private String userPw;
    private String userName;
    private String userPhone;// DB에서 char(13)라는 고정 값을 쓰므로 JDBC에선 유연성을 위해 문자열 처리
    private Date joinDate;
    private final int authLv = 3; // 회원가입할 아이디는 소비자로 한정되므로 authLv을 3으로 final & 하드코딩 처리.
    //세터에서도 제거, 생성자에서도 제거.
    private String storeId;

    public Acc_InfoVO(String userId, String userPw, String userName, String userPhone, Date joinDate, String storeId) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPhone = userPhone;
        this.joinDate = joinDate;
        this.storeId = storeId;
    }

    public Acc_InfoVO() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public int getAuthLv() {
        return authLv;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        if (userPhone != null && userPhone.length() == 13) { //13자리 입력시 문제 없이 통과.
            this.userPhone = userPhone;
        } else {    // 데이터베이스와의 일관성을 위해 제약 조건 부과.
            throw new IllegalArgumentException("연락처는 정확히 '-' 을 포함해 13자여야 합니다.");
        }
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}