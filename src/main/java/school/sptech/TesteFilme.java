package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;

public class TesteFilme {
    public static void main(String[] args) {
        ConexaoBD conexao = new ConexaoBD();
        JdbcTemplate con = conexao.getConnection();

        con.execute("""
        USE testando;
        """);

        con.execute("""
        CREATE TABLE testeConexao (
        id int primary key auto_increment,
        nome varchar(50) not null
        );""");
    }
}
