
package dao;

import java.sql.*;
import java.util.*;
import model.Product;

/**
 * 商品資料存取物件（DAO）
 * 專責處理與商品相關的所有資料庫操作。
 */
public class ProductDAO {
    /** 資料庫連線物件 */
    private Connection conn;

    /**
     * 建構子，初始化 DAO 並注入資料庫連線
     * 
     * @param conn 資料庫連線
     */
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 查詢所有商品
     * 
     * @return 商品物件清單
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.idProducts, p.CategoriesID, p.ProductName, p.Price, c.categoryname " +
                "FROM products p " +
                "LEFT JOIN category c ON p.CategoriesID = c.idcategories";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("idProducts"),
                        rs.getInt("CategoriesID"),
                        rs.getString("ProductName"),
                        rs.getInt("Price"),
                        rs.getString("categoryname")));
            }
        }
        return products;
    }

    /**
     * 新增商品資料
     * 
     * @param product    要儲存的商品物件
     * @param categoryId 商品所屬分類編號
     * @return 成功則回傳 true，失敗則回傳 false
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public boolean save(Product product, int categoryId) throws SQLException {
        String sql = "INSERT INTO products (idProducts, ProductName, Price, CategoriesID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getIdProducts());
            stmt.setString(2, product.getProductName());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, categoryId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * 取得下一個可用的商品編號（idProducts）
     */
    public int getNextProductId() throws SQLException {
        String sql = "SELECT IFNULL(MAX(idProducts), 0) + 1 AS nextId FROM products";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("nextId");
            }
        }
        return 1;
    }

    /**
     * 更新商品資料
     * 
     * @param id       商品編號
     * @param name     商品名稱
     * @param price    商品價格
     * @param quantity 商品數量
     * @return 成功則回傳 true，失敗則回傳 false
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public boolean update(int id, String name, Integer price, Integer quantity) throws SQLException {
        String sql = "UPDATE products SET ProductName = ?, Price = ?, Quantity = ? WHERE idProducts = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * 刪除商品資料
     * 
     * @param id 商品編號
     * @return 成功則回傳 true，失敗則回傳 false
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE idProducts = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
