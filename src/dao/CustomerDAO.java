package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 * 顧客資料存取物件（DAO）
 * 專責處理與顧客相關的所有資料庫操作。
 */
public class CustomerDAO {
    /** 資料庫連線物件 */
    private Connection conn;

    /**
     * 建構子，初始化 DAO 並注入資料庫連線
     * 
     * @param conn 資料庫連線
     */
    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 依帳號與密碼（電話）查詢顧客
     * 
     * @param account  顧客帳號
     * @param password 顧客密碼（電話）
     * @return 查到則回傳 Customer 物件，否則回傳 null
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public Customer findByAccountAndPassword(String account, String password) throws SQLException {
        String sql = "SELECT idCustomers, CustomerName, Account, Phone FROM customers WHERE Account = ? AND Phone = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCustomer(rs);
                }
            }
        }
        return null; // 查無資料
    }

    /**
     * 查詢所有顧客
     * 
     * @return 顧客物件清單
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT idCustomers, CustomerName, Account, Phone FROM customers";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }
        }
        return customers;
    }

    /**
     * 新增顧客資料
     * 
     * @param customer 要儲存的顧客物件
     * @return 成功則回傳 true，失敗則回傳 false
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (idCustomers, CustomerName, Account, Phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAccount());
            stmt.setString(4, customer.getPhone());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * 更新顧客密碼（電話）
     * 
     * @param customer 包含新電話的顧客物件
     * @return 成功則回傳 true，失敗則回傳 false
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public boolean updatePassword(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET Phone = ? WHERE idCustomers = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getPhone());
            stmt.setInt(2, customer.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * 依顧客編號刪除顧客
     * 
     * @param customerId 顧客編號
     * @return 成功則回傳 true，失敗則回傳 false
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public boolean delete(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE idCustomers = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * 將查詢結果 ResultSet 轉換為 Customer 物件（避免重複程式碼）
     * 
     * @param rs 查詢結果集
     * @return Customer 物件
     * @throws SQLException 資料庫欄位不存在時拋出
     */
    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("idCustomers"),
                rs.getString("CustomerName"),
                rs.getString("Account"),
                rs.getString("Phone"));
    }
}
