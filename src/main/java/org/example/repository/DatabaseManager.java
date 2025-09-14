package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:h2:mem:pedidos;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static void setupDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS PRODUTO (" +
                    "id INT PRIMARY KEY, " +
                    "nome VARCHAR(255), " +
                    "descricao VARCHAR(255), " +
                    "preco DECIMAL(10, 2))");

            stmt.execute("CREATE TABLE IF NOT EXISTS PEDIDO (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "idCliente INT, " +
                    "idProduto INT, " +
                    "nomeProduto VARCHAR(255), " +
                    "quantidade INT, " +
                    "precoUnitario DECIMAL(10, 2), " +
                    "precoTotal DECIMAL(10, 2))");

            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (1, 'Notebook', '14\" i5 8GB SSD', 3500.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (2, 'Mouse', 'Wireless USB', 120.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (3, 'Teclado', 'Mecânico RGB', 250.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (4, 'Monitor', '24\" LED Full HD', 899.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (5, 'Headset', 'Gamer com microfone', 320.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (6, 'Webcam', '1080p com foco automático', 210.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (7, 'HD Externo', '1TB USB 3.0', 380.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (8, 'Smartphone', 'Android 128GB', 1899.99)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (9, 'Tablet', '10\" Android com caneta', 1499.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (10, 'Carregador Portátil', '10.000mAh USB-C', 129.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (11, 'Cabo HDMI', '1.5m Alta velocidade', 39.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (12, 'Mousepad', 'Gamer RGB 35cm', 89.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (13, 'Switch de Rede', '8 portas Gigabit', 179.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (14, 'Pen Drive', '64GB USB 3.0', 59.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (15, 'SSD Interno', '512GB NVMe', 499.90)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (16, 'Notebook Gamer', '15.6\" i7 RTX 3050', 6200.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (17, 'Cadeira Gamer', 'Ergonômica com ajuste', 990.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (18, 'Placa de Vídeo', 'GeForce RTX 4060', 2599.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (19, 'Fonte ATX', '650W 80 Plus Bronze', 399.00)");
            stmt.execute("MERGE INTO PRODUTO KEY(id) VALUES (20, 'Teclado sem fio', 'Bluetooth recarregável', 189.90)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao configurar o banco de dados.", e);
        }
    }
}