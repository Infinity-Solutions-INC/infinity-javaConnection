package school.sptech;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
//import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        LogSistema log = new LogSistema();

        log.mandarMensagemParaLog("Procurando novos arquivos para leitura no bucket s3");

        ConexaoBD conexaoBd = new ConexaoBD();

        conexaoBd.criarBancoDeDados();

        QuerysBD query = new QuerysBD();


        query.criarTabelas();

        /* tirar o comentario quando usar o s3*/

//        S3Provider s3 = new S3Provider();
//        s3.getS3file();

        /*comentar quando NAO for testar com um arquivo da maquina pessoal*/
<<<<<<< HEAD
        String nomeArquivo = "C:\\Users\\2\\Desktop\\faculdade-matogrosso.xlsx";
=======
        String nomeArquivo = "C:\\Users\\ggame\\Desktop\\indicadores_trajetoria_educacao_superior_2014_2023.xlsx";
>>>>>>> aa2f9fdce87e263cd187fb6fab0af47cbd88d117

        Path caminho = Path.of(nomeArquivo);
        InputStream arquivo = Files.newInputStream(caminho);

        LeitorArquivo leitorArquivo = new LeitorArquivo();

        List<Registro> resposta = leitorArquivo.extrairRegistros(nomeArquivo, arquivo);

        arquivo.close();
//        /*comentar até aqui*/

    }
}
