
package dao;

import java.sql.*;
import java.util.*;

/**
 * 運送方式資料存取物件（DAO）
 * 專責處理與運送方式相關的所有資料庫操作。
 */
public class ShippingMethodDAO {
    /** 資料庫連線物件 */
    private Connection conn;

    /**
     * 建構子，初始化 DAO 並注入資料庫連線
     * 
     * @param conn 資料庫連線
     */
    public ShippingMethodDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 取得所有運送方式名稱
     * 
     * @return 運送方式名稱字串清單
     * @throws SQLException 資料庫存取發生錯誤時拋出
     */
    public List<String> getAll() throws SQLException {
        List<String> methods = new ArrayList<>();
        String sql = "SELECT methodName FROM shippingmethod";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                methods.add(rs.getString("methodName"));
            }
        }
        return methods;
    }
}
