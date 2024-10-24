package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {
        ConexaoBD dbConnectionProvider = new ConexaoBD();
        JdbcTemplate connection = dbConnectionProvider.getConnection();

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
                    ano_turma varchar(4) not null,
                	qtd_ingressantes int not null,
                    taxa_desistencia double(4,1) not null,\s
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
}
