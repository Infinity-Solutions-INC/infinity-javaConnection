package school.sptech;

import java.time.LocalDateTime;

public class Registros {

    public static class Prompt_ia {
        private Integer codigoPrompt;
        private String descricaoPrompt;

        public Prompt_ia() {

        }

        public Prompt_ia(Integer codigoPrompt, String descricaoPrompt) {
            this.codigoPrompt = codigoPrompt;
            this.descricaoPrompt = descricaoPrompt;
        }

        public Integer getCodigoPrompt() {
            return codigoPrompt;
        }

        public void setCodigoPrompt(Integer codigoPrompt) {
            this.codigoPrompt = codigoPrompt;
        }

        public String getDescricaoPrompt() {
            return descricaoPrompt;
        }

        public void setDescricaoPrompt(String descricaoPrompt) {
            this.descricaoPrompt = descricaoPrompt;
        }

        @Override
        public String toString() {
            return "Prompt_ia{" +
                    "codigoPrompt=" + codigoPrompt +
                    ", descricaoPrompt='" + descricaoPrompt + '\'' +
                    '}';
        }
    }

    public static class Area_curso {
        private Integer codigoArea;
        private String nomeArea;

        public Area_curso() {

        }

        public Area_curso(Integer codigoArea, String nomeArea) {
            this.codigoArea = codigoArea;
            this.nomeArea = nomeArea;
        }

        public Integer getCodigoArea() {
            return codigoArea;
        }

        public void setCodigoArea(Integer codigoArea) {
            this.codigoArea = codigoArea;
        }

        public String getNomeArea() {
            return nomeArea;
        }

        public void setNomeArea(String nomeArea) {
            this.nomeArea = nomeArea;
        }

        @Override
        public String toString() {
            return "Area_curso{" +
                    "codigoArea=" + codigoArea +
                    ", nomeArea='" + nomeArea + '\'' +
                    '}';
        }
    }

    public static class Instituicao {
        private Integer codigoInstituicao;
        private String nomeInstituicao;

        public Instituicao() {

        }

        public Instituicao(Integer codigoInstituicao, String nomeInstituicao) {
            this.codigoInstituicao = codigoInstituicao;
            this.nomeInstituicao = nomeInstituicao;
        }

        public Integer getCodigoInstituicao() {
            return codigoInstituicao;
        }

        public void setCodigoInstituicao(Integer codigoInstituicao) {
            this.codigoInstituicao = codigoInstituicao;
        }

        public String getNomeInstituicao() {
            return nomeInstituicao;
        }

        public void setNomeInstituicao(String nomeInstituicao) {
            this.nomeInstituicao = nomeInstituicao;
        }

        @Override
        public String toString() {
            return "Instituicao{" +
                    "codigoInstituicao=" + codigoInstituicao +
                    ", nomeInstituicao='" + nomeInstituicao + '\'' +
                    '}';
        }
    }

    public static class Curso {
        private Integer codigoCurso;
        private String nomeCurso;
        private Integer fkcodigoInstituicao;
        private Integer fkcodigoArea;

        public Curso() {

        }

        public Curso(Integer codigoCurso, String nomeCurso, Integer fkcodigoInstituicao, Integer fkcodigoArea) {
            this.codigoCurso = codigoCurso;
            this.nomeCurso = nomeCurso;
            this.fkcodigoInstituicao = fkcodigoInstituicao;
            this.fkcodigoArea = fkcodigoArea;
        }

        public Integer getCodigoCurso() {
            return codigoCurso;
        }

        public void setCodigoCurso(Integer codigoCurso) {
            this.codigoCurso = codigoCurso;
        }

        public String getNomeCurso() {
            return nomeCurso;
        }

        public void setNomeCurso(String nomeCurso) {
            this.nomeCurso = nomeCurso;
        }

        public Integer getFkcodigoInstituicao() {
            return fkcodigoInstituicao;
        }

        public void setFkcodigoInstituicao(Integer fkcodigoInstituicao) {
            this.fkcodigoInstituicao = fkcodigoInstituicao;
        }

        public Integer getFkcodigoArea() {
            return fkcodigoArea;
        }

        public void setFkcodigoArea(Integer fkcodigoArea) {
            this.fkcodigoArea = fkcodigoArea;
        }

        @Override
        public String toString() {
            return "Curso{" +
                    "codigoCurso=" + codigoCurso +
                    ", nomeCurso='" + nomeCurso + '\'' +
                    ", fkcodigoInstituicao=" + fkcodigoInstituicao +
                    ", fkcodigoArea=" + fkcodigoArea +
                    '}';
        }
    }

    public static class Funcionario {
        private String codigoFuncionario;
        private String nomeFuncionario;
        private String cargoFuncionario;
        private String cpfFuncionario;
        private String emailFuncionario;
        private String senhaFuncionario;
        private String statusFuncionario;
        private Integer fkcodigoInstituicao;

        public Funcionario() {

        }

        public Funcionario(String codigoFuncionario, String nomeFuncionario,
                           String cargoFuncionario, String cpfFuncionario, String emailFuncionario,
                           String senhaFuncionario, String statusFuncionario, Integer fkcodigoInstituicao) {
            this.codigoFuncionario = codigoFuncionario;
            this.nomeFuncionario = nomeFuncionario;
            this.cargoFuncionario = cargoFuncionario;
            this.cpfFuncionario = cpfFuncionario;
            this.emailFuncionario = emailFuncionario;
            this.senhaFuncionario = senhaFuncionario;
            this.statusFuncionario = statusFuncionario;
            this.fkcodigoInstituicao = fkcodigoInstituicao;
        }

        public String getCodigoFuncionario() {
            return codigoFuncionario;
        }

        public void setCodigoFuncionario(String codigoFuncionario) {
            this.codigoFuncionario = codigoFuncionario;
        }

        public String getNomeFuncionario() {
            return nomeFuncionario;
        }

        public void setNomeFuncionario(String nomeFuncionario) {
            this.nomeFuncionario = nomeFuncionario;
        }

        public String getCargoFuncionario() {
            return cargoFuncionario;
        }

        public void setCargoFuncionario(String cargoFuncionario) {
            this.cargoFuncionario = cargoFuncionario;
        }

        public String getCpfFuncionario() {
            return cpfFuncionario;
        }

        public void setCpfFuncionario(String cpfFuncionario) {
            this.cpfFuncionario = cpfFuncionario;
        }

        public String getEmailFuncionario() {
            return emailFuncionario;
        }

        public void setEmailFuncionario(String emailFuncionario) {
            this.emailFuncionario = emailFuncionario;
        }

        public String getSenhaFuncionario() {
            return senhaFuncionario;
        }

        public void setSenhaFuncionario(String senhaFuncionario) {
            this.senhaFuncionario = senhaFuncionario;
        }

        public String getStatusFuncionario() {
            return statusFuncionario;
        }

        public void setStatusFuncionario(String statusFuncionario) {
            this.statusFuncionario = statusFuncionario;
        }

        public Integer getFkcodigoInstituicao() {
            return fkcodigoInstituicao;
        }

        public void setFkcodigoInstituicao(Integer fkcodigoInstituicao) {
            this.fkcodigoInstituicao = fkcodigoInstituicao;
        }

        @Override
        public String toString() {
            return "Funcionario{" +
                    "codigoFuncionario='" + codigoFuncionario + '\'' +
                    ", nomeFuncionario='" + nomeFuncionario + '\'' +
                    ", cargoFuncionario='" + cargoFuncionario + '\'' +
                    ", cpfFuncionario='" + cpfFuncionario + '\'' +
                    ", emailFuncionario='" + emailFuncionario + '\'' +
                    ", senhaFuncionario='" + senhaFuncionario + '\'' +
                    ", statusFuncionario='" + statusFuncionario + '\'' +
                    ", fkcodigoInstituicao=" + fkcodigoInstituicao +
                    '}';
        }
    }

    public static class Turma {
        private Integer codigoTurma;
        private Integer anoTurma;
        private Integer qtdIngressantes;
        private Double taxaDesistencia;
        private Integer fkcodigoCurso;

        public Turma() {

        }

        public Turma(Integer fkcodigoCurso, Double taxaDesistencia, Integer qtdIngressantes,
                     Integer anoTurma, Integer codigoTurma) {
            this.fkcodigoCurso = fkcodigoCurso;
            this.taxaDesistencia = taxaDesistencia;
            this.qtdIngressantes = qtdIngressantes;
            this.anoTurma = anoTurma;
            this.codigoTurma = codigoTurma;
        }

        public Integer getCodigoTurma() {
            return codigoTurma;
        }

        public void setCodigoTurma(Integer codigoTurma) {
            this.codigoTurma = codigoTurma;
        }

        public Integer getAnoTurma() {
            return anoTurma;
        }

        public void setAnoTurma(Integer anoTurma) {
            this.anoTurma = anoTurma;
        }

        public Integer getQtdIngressantes() {
            return qtdIngressantes;
        }

        public void setQtdIngressantes(Integer qtdIngressantes) {
            this.qtdIngressantes = qtdIngressantes;
        }

        public Double getTaxaDesistencia() {
            return taxaDesistencia;
        }

        public void setTaxaDesistencia(Double taxaDesistencia) {
            this.taxaDesistencia = taxaDesistencia;
        }

        public Integer getFkcodigoCurso() {
            return fkcodigoCurso;
        }

        public void setFkcodigoCurso(Integer fkcodigoCurso) {
            this.fkcodigoCurso = fkcodigoCurso;
        }

        @Override
        public String toString() {
            return "Turma{" +
                    "codigoTurma=" + codigoTurma +
                    ", anoTurma=" + anoTurma +
                    ", qtdIngressantes=" + qtdIngressantes +
                    ", taxaDesistencia=" + taxaDesistencia +
                    ", fkcodigoCurso=" + fkcodigoCurso +
                    '}';
        }
    }

    public static class recomendacaoEnviada {
        private Integer codigoRecomendacaoEnviada;
        private Integer fkcodigoTurma;
        private Integer fkcodigoPrompt;
        private String descricaoRecomendacaoEnviada;
        private LocalDateTime dtHrRecomendacaoEnviada;

        public recomendacaoEnviada() {

        }

        public recomendacaoEnviada(Integer codigoRecomendacaoEnviada, Integer fkcodigoTurma, Integer fkcodigoPrompt,
                                   String descricaoRecomendacaoEnviada, LocalDateTime dtHrRecomendacaoEnviada) {
            this.codigoRecomendacaoEnviada = codigoRecomendacaoEnviada;
            this.fkcodigoTurma = fkcodigoTurma;
            this.fkcodigoPrompt = fkcodigoPrompt;
            this.descricaoRecomendacaoEnviada = descricaoRecomendacaoEnviada;
            this.dtHrRecomendacaoEnviada = dtHrRecomendacaoEnviada;
        }

        public Integer getCodigoRecomendacaoEnviada() {
            return codigoRecomendacaoEnviada;
        }

        public void setCodigoRecomendacaoEnviada(Integer codigoRecomendacaoEnviada) {
            this.codigoRecomendacaoEnviada = codigoRecomendacaoEnviada;
        }

        public Integer getFkcodigoTurma() {
            return fkcodigoTurma;
        }

        public void setFkcodigoTurma(Integer fkcodigoTurma) {
            this.fkcodigoTurma = fkcodigoTurma;
        }

        public Integer getFkcodigoPrompt() {
            return fkcodigoPrompt;
        }

        public void setFkcodigoPrompt(Integer fkcodigoPrompt) {
            this.fkcodigoPrompt = fkcodigoPrompt;
        }

        public String getDescricaoRecomendacaoEnviada() {
            return descricaoRecomendacaoEnviada;
        }

        public void setDescricaoRecomendacaoEnviada(String descricaoRecomendacaoEnviada) {
            this.descricaoRecomendacaoEnviada = descricaoRecomendacaoEnviada;
        }

        public LocalDateTime getDtHrRecomendacaoEnviada() {
            return dtHrRecomendacaoEnviada;
        }

        public void setDtHrRecomendacaoEnviada(LocalDateTime dtHrRecomendacaoEnviada) {
            this.dtHrRecomendacaoEnviada = dtHrRecomendacaoEnviada;
        }

        @Override
        public String toString() {
            return "recomendacaoEnviada{" +
                    "codigoRecomendacaoEnviada=" + codigoRecomendacaoEnviada +
                    ", fkcodigoTurma=" + fkcodigoTurma +
                    ", fkcodigoPrompt=" + fkcodigoPrompt +
                    ", descricaoRecomendacaoEnviada='" + descricaoRecomendacaoEnviada + '\'' +
                    ", dtHrRecomendacaoEnviada=" + dtHrRecomendacaoEnviada +
                    '}';
        }
    }

    public static class motivoEvasao {
        private Integer codigoMotivoEvasao;
        private String descricaoMotivoEvasao;
        private LocalDateTime dtHrRegistroMotivoEvasao;
        private Integer fkcodigoTurma;

        public motivoEvasao() {

        }

        public motivoEvasao(Integer codigoMotivoEvasao, String descricaoMotivoEvasao,
                            LocalDateTime dtHrRegistroMotivoEvasao, Integer fkcodigoTurma) {
            this.codigoMotivoEvasao = codigoMotivoEvasao;
            this.descricaoMotivoEvasao = descricaoMotivoEvasao;
            this.dtHrRegistroMotivoEvasao = dtHrRegistroMotivoEvasao;
            this.fkcodigoTurma = fkcodigoTurma;
        }

        public Integer getCodigoMotivoEvasao() {
            return codigoMotivoEvasao;
        }

        public void setCodigoMotivoEvasao(Integer codigoMotivoEvasao) {
            this.codigoMotivoEvasao = codigoMotivoEvasao;
        }

        public String getDescricaoMotivoEvasao() {
            return descricaoMotivoEvasao;
        }

        public void setDescricaoMotivoEvasao(String descricaoMotivoEvasao) {
            this.descricaoMotivoEvasao = descricaoMotivoEvasao;
        }

        public LocalDateTime getDtHrRegistroMotivoEvasao() {
            return dtHrRegistroMotivoEvasao;
        }

        public void setDtHrRegistroMotivoEvasao(LocalDateTime dtHrRegistroMotivoEvasao) {
            this.dtHrRegistroMotivoEvasao = dtHrRegistroMotivoEvasao;
        }

        public Integer getFkcodigoTurma() {
            return fkcodigoTurma;
        }

        public void setFkcodigoTurma(Integer fkcodigoTurma) {
            this.fkcodigoTurma = fkcodigoTurma;
        }

        @Override
        public String toString() {
            return "motivoEvasao{" +
                    "codigoMotivoEvasao=" + codigoMotivoEvasao +
                    ", descricaoMotivoEvasao='" + descricaoMotivoEvasao + '\'' +
                    ", dtHrRegistroMotivoEvasao=" + dtHrRegistroMotivoEvasao +
                    ", fkcodigoTurma=" + fkcodigoTurma +
                    '}';
        }
    }

}
