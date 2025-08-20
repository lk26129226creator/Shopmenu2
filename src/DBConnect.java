import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 資料庫連線工具類別 DBConnect
 * 提供取得 MySQL 資料庫連線的方法，並於類別載入時自動載入驅動程式。
 * 也可作為連線測試用。
 */
public class DBConnect {
    // MySQL 連線資訊
    // 加上 ?autoReconnect=true&serverTimezone=UTC 參數，避免連線逾時問題並設定時區
    private static final String URL = "jdbc:mysql://localhost:3306/test0310?autoReconnect=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "lkjh890612";

    // 使用靜態區塊 (static block) 在類別被載入時，就嘗試載入驅動程式。
    // 這樣可以確保任何地方呼叫 getConnection() 之前，驅動都已經被載入。
    // 如果驅動不存在 (JAR 不在 classpath)，程式會立即因 ClassNotFoundException 而啟動失敗，
    // 這樣能更快地發現問題，並提供更明確的錯誤訊息。
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("FATAL: 找不到 MySQL JDBC 驅動程式。請將 mysql-connector-j-....jar 加入 classpath。", e);
        }
    }

    // 提供外部取得資料庫連線的方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        // 這個 main 方法是一個簡單的連線測試。
        // 它應該使用這個類別自己提供的 getConnection() 方法，並用 try-with-resources 確保連線被關閉。
        try (Connection conn = DBConnect.getConnection()) {
            System.out.println("✅ 成功連接 MySQL！");

            // 執行一個簡單的查詢來驗證連線是否正常工作
            String sql = "SELECT * FROM products";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                System.out.println("--- 測試查詢 products 表 ---");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("idProducts") + ", Name: " + rs.getString("ProductName"));
                }
                System.out.println("--------------------------");
            }
        } catch (Exception e) {
            System.err.println("❌ 連線測試失敗。");
            e.printStackTrace();
        }
    }
}
