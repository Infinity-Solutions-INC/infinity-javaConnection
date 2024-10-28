package school.sptech;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.InputStream;
import java.util.List;

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
        S3Client s3Client = new S3Provider().getS3Client();
        String bucketName = "nomeDoBucket";

        String nomeArquivoExcel = "";

        try {
            ListObjectsRequest listObjects = ListObjectsRequest.builder()
                    .bucket(bucketName)
                    .build();

            List<S3Object> objects = s3Client.listObjects(listObjects).contents();


            for (S3Object object : objects) {
                Boolean objetoExcel = object.key().endsWith(".xlsx") || object.key().endsWith(".xls");

                if (objetoExcel) {
                    nomeArquivoExcel = object.key();
                }
            }
        } catch (S3Exception e) {
            System.err.println("Erro ao listar objetos no bucket: " + e.getMessage());
        }

        try {
            GetObjectRequest getS3file = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(nomeArquivoExcel)
                    .build();

            InputStream arquivo = getS3Client().getObject(getS3file);

            LeitorArquivo leitorArquivo = new LeitorArquivo();
            QuerysBD querys = new QuerysBD();
            Boolean arquivoJaLido = querys.alterarStatusArquivo(nomeArquivoExcel);

            if (arquivoJaLido) {
                System.out.println("Arquivo ja foi lido!");
            } else {
                leitorArquivo.extrairRegistros(getS3file.key(), arquivo);
            }

        } catch (S3Exception e) {
            System.out.println("Erro ao obter arquivo do bucket: " + e.getMessage());
        }
    }
}

