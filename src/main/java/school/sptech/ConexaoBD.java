package school.sptech;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class ConexaoBD {
    private final DataSource dataSource;
    private String url = "jdbc:mysql://mysql-app:3306/infinity_solutions?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8";

    private String username = System.getenv("DB_USER");
    private String passwd = System.getenv("DB_PASSWORD");
//    private String username = "root";
//    private String passwd = "rootpassword";


    LogSistema log = new LogSistema();

    public ConexaoBD() {
        criarBancoDeDados();
        BasicDataSource basicDataSource = new BasicDataSource();
      
        basicDataSource.setUrl("jdbc:mysql://mysql-app:3306/infinity_solutions?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8");

        basicDataSource.setUsername(username);
        basicDataSource.setPassword(passwd);
        this.dataSource = basicDataSource;
    }

    public void criarBancoDeDados() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); // Carregar o driver explicitamente
            try (Connection con = DriverManager.getConnection(url, username, passwd);
                 Statement statement = con.createStatement()) {
                String sql = "CREATE DATABASE IF NOT EXISTS infinity_solutions;";
                statement.executeUpdate(sql);
            }

        } catch (SQLException | ClassNotFoundException e) {
            log.mandarMensagemParaLog("Erro ao criar banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public JdbcTemplate getConnection() {
        return new JdbcTemplate(dataSource);
    }
}
