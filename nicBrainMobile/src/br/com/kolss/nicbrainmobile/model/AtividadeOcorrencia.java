package br.com.kolss.nicbrainmobile.model;

import java.io.Serializable;

public class AtividadeOcorrencia implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3088624066290343596L;
	
	private String idAtividadeOcorrencia;
	private String idOcorrencia;
	private String dtInicioAtividade;
	private String dtFimExecucaoAtividade;
	private String observacao;
	private String descricaoStatus;
	private String descricaoProcedimentoOcorrencia;
	private String grResponsavel;
	
	public AtividadeOcorrencia(String idAtividadeOcorrencia,
			String idOcorrencia, String dtInicioAtividade,
			String dtFimExecucaoAtividade, String observacao,
			String descricaoStatus, String descricaoProcedimentoOcorrencia,
			String grResponsavel) {
		this.idAtividadeOcorrencia = idAtividadeOcorrencia;
		this.idOcorrencia = idOcorrencia;
		this.dtInicioAtividade = dtInicioAtividade;
		this.dtFimExecucaoAtividade = dtFimExecucaoAtividade;
		this.observacao = observacao;
		this.descricaoStatus = descricaoStatus;
		this.descricaoProcedimentoOcorrencia = descricaoProcedimentoOcorrencia;
		this.grResponsavel = grResponsavel;
	}

	public String getIdAtividadeOcorrencia() {
		return idAtividadeOcorrencia;
	}

	public void setIdAtividadeOcorrencia(String idAtividadeOcorrencia) {
		this.idAtividadeOcorrencia = idAtividadeOcorrencia;
	}

	public String getIdOcorrencia() {
		return idOcorrencia;
	}

	public void setIdOcorrencia(String idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
	}

	public String getDtInicioAtividade() {
		return dtInicioAtividade;
	}

	public void setDtInicioAtividade(String dtInicioAtividade) {
		this.dtInicioAtividade = dtInicioAtividade;
	}

	public String getDtFimExecucaoAtividade() {
		return dtFimExecucaoAtividade;
	}

	public void setDtFimExecucaoAtividade(String dtFimExecucaoAtividade) {
		this.dtFimExecucaoAtividade = dtFimExecucaoAtividade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	public String getDescricaoProcedimentoOcorrencia() {
		return descricaoProcedimentoOcorrencia;
	}

	public void setDescricaoProcedimentoOcorrencia(
			String descricaoProcedimentoOcorrencia) {
		this.descricaoProcedimentoOcorrencia = descricaoProcedimentoOcorrencia;
	}

	public String getGrResponsavel() {
		return grResponsavel;
	}

	public void setGrResponsavel(String grResponsavel) {
		this.grResponsavel = grResponsavel;
	}

}