package TEST_241022.VO;

public class Inv_OrderVO {
    private String menuName;
    private int price;
    private String category;

    // 생성자
    public Inv_OrderVO(String menuName, int price, String category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    // 빈 생성자
    public Inv_OrderVO() {
    }


    // Getter
    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }


    // Setter
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}