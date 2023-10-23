package ra.model;


import java.io.Serializable;

public class Product implements Serializable {

    private int productId ;
    private String productName ;
    private String describe ;
    private Catalog catalog;
    private double importPrice ;
    private double exportPrice ;
    private double profit ;
    private boolean productStatus;
    private int quantity ;

    public Product(){

    }

    public Product(int productId, String productName, String describe, double importPrice, double exportPrice, double profit, boolean productStatus, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.describe = describe;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice * 1.5;
        this.profit = exportPrice - importPrice;
        this.productStatus = productStatus;
        this.quantity = quantity;
    }

    public Product(int productId, String productName, String describe, Catalog catalog, double importPrice, double exportPrice, double profit, boolean productStatus, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.describe = describe;
        this.catalog = catalog;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.productStatus = productStatus;
        this.quantity = quantity;
    }



    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void displayProduct() {
        System.out.println("");
        System.out.println("Id : " + productId +" ➖ Ten san pham : "+ productName+" ➖ Mo ta san pham : " +describe+ " ➖ Danh muc " + catalog.getCatalogName() +" ➖ Gia ban : " + exportPrice+" USD ➖ Tinh trang : " + (productStatus?"Mo ban":"Het hang"));
        System.out.println("");
    }
}
