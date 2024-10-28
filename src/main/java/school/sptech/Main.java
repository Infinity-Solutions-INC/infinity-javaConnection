package school.sptech;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
//import java.util.List;

//import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) throws IOException {
        ConexaoBD conexaoBd = new ConexaoBD();

        conexaoBd.criarBancoDeDados();

        String nomeArquivo = "C:\\Users\\2\\Desktop\\indicadores_trajetoria_educacao_superior_2014_2023.xlsx";

        Path caminho = Path.of(nomeArquivo);
        InputStream arquivo = Files.newInputStream(caminho);

        LeitorArquivo leitorArquivo = new LeitorArquivo();
        List<Registro> resposta = leitorArquivo.extrairRegistros(nomeArquivo, arquivo);

        arquivo.close();

        System.out.println(resposta);

        QuerysBD query = new QuerysBD();

        query.criarTabelas();
        query.inserirDados(resposta);

    }
}
