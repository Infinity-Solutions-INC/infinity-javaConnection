package school.sptech;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogSistema {
    private String nomeArquivo = "";
    private String caminhoArquivo = "";
    private LocalDate dataAtual = LocalDate.now();
    private final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss:: ");
    private final DateTimeFormatter FORMATO_DIA_ARQUIVO = DateTimeFormatter.ofPattern("yyyyMMdd");

    private String gerarCaminhoCompletoArquivo(String diretorioLogs, LocalDate data) {
        return diretorioLogs + "log_" + data.format(FORMATO_DIA_ARQUIVO) + ".log";
    }
    private String gerarNomeArquivo(LocalDate data) {
        return "log_" + data.format(FORMATO_DIA_ARQUIVO) + ".log";
    }

    public void mandarMensagemParaLog (String mensagem){
        LocalDateTime data = LocalDateTime.now();
        caminhoArquivo = gerarCaminhoCompletoArquivo("/app/output/", dataAtual);
        nomeArquivo = gerarNomeArquivo(dataAtual);

        String dataFormatada = data.format(FORMATO_DIA);
        gravarNoArquivo(dataFormatada + mensagem);

    }
    private void gravarNoArquivo(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(mensagem);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
            QuerysBD query = new QuerysBD();
            query.inserirMensagemErro(e.getMessage());
        }
    }
    public void mandarLogParaS3(){
        S3Provider s3 = new S3Provider();
        s3.postS3file("/app/output/", nomeArquivo );
    }
}
