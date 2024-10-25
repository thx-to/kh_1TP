package TEST_241025_취합_수정.VO;

public class StoreVO {
    private String storeId;
    private int sales; //
    private int capital;

    public StoreVO(String storeId,int sales,int capital) {
        this.storeId = storeId;
        this.sales = sales;
        this.capital = capital;
    }

    public StoreVO(String storeId, int capital) {
        this.storeId = storeId;
        this.capital = capital;
    }

    public StoreVO(int sales) {
        this.sales = sales;

    }

    public String getStoreId() {
        return storeId;
    }

    public int getSales() {
        return sales;
    }

    public int getCapital() {
        return capital;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }
}