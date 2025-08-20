package model;

/**
 * 商品資料模型
 * 封裝商品的基本資訊（如編號、名稱、價格、分類）
 */
public class Product {
    /** 商品分類名稱（對應資料表 category.categoryname 欄位） */
    private String categoryName;
    /** 商品編號（對應資料表 idProducts 欄位） */
    private int idProducts;
    /** 商品分類編號（對應資料表 CategoriesID 欄位） */
    private int categoriesID;
    /** 商品名稱（對應資料表 ProductName 欄位） */
    private String productName;
    /** 商品價格（對應資料表 Price 欄位） */
    private int price;

    /**
     * 建構子，建立商品物件
     * 
     * @param idProducts   商品編號
     * @param categoriesID 商品分類編號
     * @param productName  商品名稱
     * @param price        商品價格
     */
    public Product(int idProducts, int categoriesID, String productName, int price) {
        this.idProducts = idProducts;
        this.categoriesID = categoriesID;
        this.productName = productName;
        this.price = price;
        this.categoryName = null;
    }

    /**
     * 建構子，建立商品物件（含分類名稱）
     * 
     * @param idProducts   商品編號
     * @param categoriesID 商品分類編號
     * @param productName  商品名稱
     * @param price        商品價格
     * @param categoryName 商品分類名稱
     */
    public Product(int idProducts, int categoriesID, String productName, int price, String categoryName) {
        this.idProducts = idProducts;
        this.categoriesID = categoriesID;
        this.productName = productName;
        this.price = price;
        this.categoryName = categoryName;
    }

    // --- Getter 和 Setter 方法 ---

    /** 取得商品編號（idProducts） */
    public int getIdProducts() {
        return idProducts;
    }

    /** 取得商品分類編號（categoriesID） */
    public int getCategoriesID() {
        return categoriesID;
    }

    /** 取得商品名稱（productName） */
    public String getProductName() {
        return productName;
    }

    /** 取得商品價格（price） */
    public int getPrice() {
        return price;
    }

    /** 取得商品分類名稱（categoryName） */
    public String getCategoryName() {
        return categoryName;
    }

    /** 設定商品分類名稱（categoryName） */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** 設定商品編號（idProducts） */
    public void setIdProducts(int idProducts) {
        this.idProducts = idProducts;
    }

    /** 設定商品分類編號（categoriesID） */
    public void setCategoriesID(int categoriesID) {
        this.categoriesID = categoriesID;
    }

    /** 設定商品名稱（productName） */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /** 設定商品價格（price） */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s（價格：%d）", productName, price);
    }
}
