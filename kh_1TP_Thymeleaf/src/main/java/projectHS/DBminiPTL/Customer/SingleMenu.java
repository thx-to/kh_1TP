package projectHS.DBminiPTL.Customer;



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

    public SingleMenu(String name, int price, String descr ,int count) {
        this.name = name;
        this.price = price;
        this.descr = descr;
        this.count = count;
    }

    // 세트용
    public SingleMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void changeCount(int count) {
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
