/**
 * 顧客資料模型
 * 封裝顧客的基本資訊（如編號、姓名、帳號、電話）
 */
package model;

public class Customer {
    /** 顧客編號 */
    private int id;
    /** 顧客姓名 */
    private String name;
    /** 顧客帳號 */
    private String account;
    /** 顧客電話 */
    private String phone;

    /**
     * 建構子，建立顧客物件
     * 
     * @param id      顧客編號
     * @param name    顧客姓名
     * @param account 顧客帳號
     * @param phone   顧客電話
     */
    public Customer(int id, String name, String account, String phone) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.phone = phone;
    }

    // --- Getter & Setter 方法 ---

    /** 取得顧客編號 */
    public int getId() {
        return id;
    }

    /** 取得顧客姓名 */
    public String getName() {
        return name;
    }

    /** 取得顧客帳號 */
    public String getAccount() {
        return account;
    }

    /** 取得顧客電話 */
    public String getPhone() {
        return phone;
    }

    /** 設定顧客編號 */
    public void setId(int id) {
        this.id = id;
    }

    /** 設定顧客姓名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 設定顧客帳號 */
    public void setAccount(String account) {
        this.account = account;
    }

    /** 設定顧客電話 */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
