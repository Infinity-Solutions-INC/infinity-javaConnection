package school.sptech;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.InputStream;

public class S3Provider {

    private final AwsSessionCredentials credentials;

    public S3Provider() {
        this.credentials = AwsSessionCredentials.create(
                System.getenv("AWS_ACCESS_KEY_ID"),
                System.getenv("AWS_SECRET_ACCESS_KEY"),
                System.getenv("AWS_SESSION_TOKEN")
        );
    }

    public S3Client getS3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(() -> credentials)
                .build();
    }

    public void getS3file() {
        try {
            GetObjectRequest getS3file = GetObjectRequest.builder()
                    .bucket("nomeBucket")
                    .key("indicadores_trajetoria_educacao_superior_2014_2023.xlsx")
                    .build();

            InputStream arquivo = getS3Client().getObject(getS3file);

            LeitorArquivo leitorArquivo = new LeitorArquivo();

            leitorArquivo.extrairRegistros(getS3file.key(), arquivo);
        } catch (S3Exception e) {
            System.out.println("Erro ao obter arquivo do bucket: " + e.getMessage());
        }
    }
}

