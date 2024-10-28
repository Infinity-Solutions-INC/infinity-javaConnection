package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuerysBD {
    ConexaoBD dbConnectionProvider = new ConexaoBD();
    JdbcTemplate connection = dbConnectionProvider.getConnection();

    public void criarTabelas() {


        connection.execute("""
                         create table IF NOT EXISTS prompt_ia (
                                 	codigo_prompt int primary key auto_increment,
                                     descricao_prompt varchar(100)
                                 );
                """);

        connection.execute("""
                        create table IF NOT EXISTS area_curso (
                        	codigo_area int primary key auto_increment,
                            nome_area varchar(60)
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS instituicao (
                        	codigo_instituicao int primary key auto_increment,
                            nome_instituicao varchar(60) not null
                        ) auto_increment = 100;
                """);

        connection.execute("""
                        create table IF NOT EXISTS curso (
                        	codigo_curso int primary key auto_increment,
                            nome_curso varchar(60),
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
                            cargo_funcionario varchar(40) not null,
                            cpf_funcionario char(11) not null,
                            email_funcionario varchar(60) ,
                            senha_funcionario varchar(200) ,
                            status_funcionario varchar(30),
                            fkcodigo_instituicao int not null,
                           \s
                            constraint fk_funcionario_instituicao foreign key (fkcodigo_instituicao) references instituicao(codigo_instituicao),
                            constraint chk_funcionario_status check (status_funcionario in("ativo", "bloqueado"))
                        );
                """);

        connection.execute("""
                        create table IF NOT EXISTS turma (
                        	codigo_turma int primary key auto_increment,
                            ano_turma int not null,
                        	qtd_ingressantes int not null,
                            qtd_alunos_permanencia not null,\s
                            fkcodigo_curso int,
                           \s
                            constraint fk_turma_curso foreign key (fkcodigo_curso) references curso(codigo_curso)
                        ) auto_increment = 100;
                """);

        connection.execute("""
                        create table IF NOT EXISTS recomendacao_enviada (
                        	codigo_recomendacao_enviada int primary key auto_increment,
                            fkcodigo_turma int not null,
                            fkcodigo_prompt int not null,
                            descricao_recomendacao_enviada text not null,
                            dt_hr_recomendacao_enviada datetime not null,
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
    }

//    public void inserirInstituicao() {
//        connection.update("""
//                INSERT INTO instituicao (nome_instituicao)
//                values
//                ("Faculdade Saúde"),
//                ("Faculdade TI"),
//                ("Faculdade Humanas")
//                 """);
//    }

    public void inserirAreasCursos(List<Registro> listaDeRegistros) {
        List<String> listaNomesAreas = new ArrayList<>();

        for (Registro registro : listaDeRegistros) {
            if (!listaNomesAreas.contains(registro.getNomeArea())) {
                connection.update("INSERT INTO area_curso (codigo_area, nome_area) values (?)", registro.getNomeArea());
                listaNomesAreas.add(registro.getNomeArea());
            }
        }
    }

    public void inserirCursos(List<Registro> listaDeRegistros) {
        List<String> listaCursos = new ArrayList<>();

        ConexaoBD conexaoBD = new ConexaoBD();
        JdbcTemplate jdbcTemplate = conexaoBD.getConnection();

        for (Registro registro : listaDeRegistros) {

            try {
                Integer codigoArea = jdbcTemplate.queryForObject(
                        """
                                SELECT codigo_area FROM area_curso WHERE nome_area = ? limit 1""",
                        Integer.class,
                        registro.getNomeArea()
                );

                if (!listaCursos.contains(registro.getNomeCurso())) {
                    connection.update("INSERT INTO curso (nome_curso, fkcodigo_area) values (?, ?)",
                            registro.getNomeCurso(), codigoArea);

                    listaCursos.add(registro.getNomeCurso());
                }

            } catch (Exception e) {
                System.out.println("Erro ao buscar o código da área: " + e.getMessage());
            }
        }
    }
}
