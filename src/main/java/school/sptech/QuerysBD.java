package school.sptech;

import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuerysBD {
    ConexaoBD dbConnectionProvider = new ConexaoBD();
    JdbcTemplate connection = dbConnectionProvider.getConnection();

    public void criarTabelas() {

        connection.execute("""
                        create table IF NOT EXISTS error_logs (
                        	id int primary key auto_increment,
                            mensagem_error text,
                            dt_hr_captacao_error datetime
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS cargo (
                        	codigo_cargo int primary key auto_increment,
                            nome_cargo varchar(30) not null
                        );
                """);

        connection.execute("""
                         create table IF NOT EXISTS arquivoLido(
                         id int primary key auto_increment,
                         nome_arquivo varchar(200),
                         status_arquivo varchar(30),
                         qtdTurmasInseridas_arquivo int,
                         constraint chk_status check (status_arquivo in("Lido"))
                         );
                """);

        connection.execute("""
                        create table IF NOT EXISTS prompt_ia (
                        	codigo_prompt int primary key auto_increment,
                            descricao_prompt varchar(100)
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS area_curso (
                        	codigo_area int primary key auto_increment,
                            nome_area varchar(120)
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS instituicao (
                        	codigo_instituicao int primary key auto_increment,	
                            nome_instituicao varchar(60) not null,
                            cnpj_instituicao varchar(14)
                        ) auto_increment = 100;
                """);

        connection.execute("""
                        create table IF NOT EXISTS curso (
                        	codigo_curso int primary key auto_increment,
                            nome_curso varchar(120),
                            ano_curso int,
                            fkcodigo_instituicao int,
                            fkcodigo_area int,
                           \s
                            constraint fk_curso_instituicao foreign key (fkcodigo_instituicao) references instituicao(codigo_instituicao),
                            constraint fk_curso_area foreign key (fkcodigo_area) references area_curso(codigo_area)
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS funcionario (
                        	codigo_funcionario char(6) primary key not null,
                            nome_funcionario varchar(60) not null,
                            fkcodigo_cargo int not null,
                            cpf_funcionario char(11) not null,
                            email_funcionario varchar(60) ,
                            senha_funcionario varchar(200) ,
                            status_funcionario varchar(30),
                            fkcodigo_instituicao int not null,
                           \s
                            constraint fk_funcionario_instituicao foreign key (fkcodigo_instituicao) references instituicao(codigo_instituicao),
                            constraint fk_funcionario_cargo foreign key (fkcodigo_cargo) references cargo(codigo_cargo),
                            constraint chk_funcionario_status check (status_funcionario in("ativo", "bloqueado", "aguardando verificacao"))
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS turma (
                        	codigo_turma int primary key auto_increment,
                            ano_turma int not null,
                        	qtd_ingressantes int not null,
                            qtd_alunos_permanencia int not null,\s
                            fkcodigo_curso int,
                           \s
                            constraint fk_turma_curso foreign key (fkcodigo_curso) references curso(codigo_curso)
                        ) auto_increment = 100;
                """);

        connection.execute("""
                        create table IF NOT EXISTS recomendacao_recebida (
                        	codigo_recomendacao_recebida int primary key auto_increment,
                            fkcodigo_turma int not null,
                            fkcodigo_prompt int not null,
                            descricao_recomendacao_recebida text not null,
                            dt_hr_recomendacao_recebida datetime not null,
                           \s
                            constraint fk_recEnv_prompt_ia_codigo foreign key (fkcodigo_prompt) references prompt_ia (codigo_prompt),
                            constraint fk_recEnv_turma_codigo foreign key (fkcodigo_turma) references turma (codigo_turma)
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS motivo_evasao (
                        	codigo_motivo_evasao int primary key auto_increment,
                            descricao_motivo_evasao varchar(50) not null,
                            dt_hr_registro_motivo_evasao datetime not null,
                            fkcodigo_turma int not null,
                           \s
                            constraint fk_motEvas_turma_codigo foreign key (fkcodigo_turma) references turma(codigo_turma)
                        );
                """);
    }

    public void inserirDados(List<Registro> listaDeRegistros) {
        inserirAreasCursos(listaDeRegistros);
        inserirCursos(listaDeRegistros);
        inserirTurmas(listaDeRegistros);
    }

    public Boolean alterarStatusArquivo(String nomeArquivo) {
        List<String> resultados = jdbcTemplate.query(
                """
                        SELECT status_arquivo FROM arquivoLido WHERE nome_arquivo = ? LIMIT 1
                        """,
                new Object[]{nomeArquivo},
                (rs, rowNum) -> rs.getString("status_arquivo")
        );

        if (resultados.isEmpty()) {
            connection.update("INSERT INTO arquivoLido (nome_arquivo, status_arquivo, dataLeitura_arquivo) values (?, ?, ?)",
                    nomeArquivo, "Lido", LocalDate.now());
            return false;

        } else {
            String statusArquivo = resultados.get(0);
            return true; // ou alguma lógica que você deseja implementar
        }

    }

    public void inserirAreasCursos(List<Registro> listaDeRegistros) {
        List<String> listaNomesAreas = new ArrayList<>();

        for (Registro registro : listaDeRegistros) {
            if (!listaNomesAreas.contains(registro.getNomeArea())) {
                connection.update("INSERT INTO area_curso (nome_area) values (?)", registro.getNomeArea());
                listaNomesAreas.add(registro.getNomeArea());
            }
        }
    }

    ConexaoBD conexaoBD = new ConexaoBD();
    JdbcTemplate jdbcTemplate = conexaoBD.getConnection();

    public void inserirCursos(List<Registro> listaDeRegistros) {

        for (Registro registro : listaDeRegistros) {

            try {
                Integer codigoArea = jdbcTemplate.queryForObject(
                        """
                                SELECT codigo_area FROM area_curso WHERE nome_area = ? limit 1""",
                        Integer.class,
                        registro.getNomeArea()
                );

                Boolean cursoExiste = verificarCursoExiste(registro.getNomeCurso());

                if (!cursoExiste) {
                    connection.update("INSERT INTO curso (nome_curso, fkcodigo_instituicao, fkcodigo_area) values (?, ?, ?)",
                            registro.getNomeCurso(), 100, codigoArea);
                }

            } catch (Exception e) {
                System.out.println("Erro ao buscar o código da área, da instituicao ou no insert: " + e.getMessage());
                inserirMensagemErro(e.getMessage());
            }
        }
    }

    public void inserirTurmas(List<Registro> listaDeRegistros) {
        Integer qtdTurmasInseridas = 0;

        try {
            for (Registro registro : listaDeRegistros) {
                Integer codigoCurso = jdbcTemplate.queryForObject(
                        """
                                SELECT codigo_curso FROM curso WHERE nome_curso = ? limit 1""",
                        Integer.class,
                        registro.getNomeCurso()
                );

                connection.update("""
                                INSERT INTO turma (ano_turma, qtd_ingressantes, qtd_alunos_permanencia, fkcodigo_curso)
                                values (?, ?, ?, ?)
                                """, registro.getAnoTurma(), registro.getQtdIngressantes(),
                        registro.getQtdAlunosPermanencia(), codigoCurso);

                qtdTurmasInseridas++;
            }

            inserirQtdTurmasInseridas(qtdTurmasInseridas);

        } catch (Exception e) {
            System.out.println("Erro ao buscar o código da área: " + e.getMessage());
            inserirMensagemErro(e.getMessage());
        }
    }

    private void inserirQtdTurmasInseridas(Integer qtdTurmasInseridas) {

        String arquivoLidoAgora = jdbcTemplate.queryForObject("""
                        SELECT nome_arquivo from arquivoLido where qtdTurmasInseridas_arquivo is null;
                        """,
                String.class
        );

        connection.update("""
                UPDATE arquivoLido set qtdTurmasInseridas_arquivo = ? where nome_arquivo = ?;
                """, qtdTurmasInseridas, arquivoLidoAgora);
    }

    public void inserirMensagemErro(String mensagemErro) {
        connection.update("""
                INSERT INTO error_logs (mensagem_error, dt_hr_captacao_error)
                values (?, ?)
                """, mensagemErro, LocalDateTime.now());
    }

    public Boolean verificarCursoExiste(String nomeCurso) {
        Integer qtdCurso = jdbcTemplate.queryForObject("""
                        SELECT count(nome_curso) from curso where nome_curso = ?;
                        """,
                Integer.class,
                nomeCurso
        );

        if (qtdCurso == 0) {
            return false;
        }

        return true;
    }
}