package TEST_241025_취합_수정.Customer;

public class SingleMenu {
    String name;
    int price;
    String descr;
    int count;

    public SingleMenu(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    // 세트용
    public SingleMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }



    public void increaseCount(int count) {
        this.count += count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescr() {
        return descr;
    }

    public int getCount() {
        return count;
    }
}