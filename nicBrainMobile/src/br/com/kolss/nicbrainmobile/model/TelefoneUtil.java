package br.com.kolss.nicbrainmobile.model;

import java.io.Serializable;

public class TelefoneUtil implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2711013805148934676L;
	
	private String idTelefoneUtil;
	private String nrTelefoneUtil;
	private String nomeTelefoneUtil;
	
	public TelefoneUtil(String idTelefoneUtil, String nrTelefoneUtil, String nomeTelefoneUtil) {
		this.idTelefoneUtil = idTelefoneUtil;
		this.nrTelefoneUtil = nrTelefoneUtil;
		this.nomeTelefoneUtil = nomeTelefoneUtil;
	}

	public String getIdTelefoneUtil() {
		return idTelefoneUtil;
	}

	public void setIdTelefoneUtil(String idTelefoneUtil) {
		this.idTelefoneUtil = idTelefoneUtil;
	}

	public String getNrTelefoneUtil() {
		return nrTelefoneUtil;
	}

	public void setNrTelefoneUtil(String nrTelefoneUtil) {
		this.nrTelefoneUtil = nrTelefoneUtil;
	}

	public String getNomeTelefoneUtil() {
		return nomeTelefoneUtil;
	}

	public void setNomeTelefoneUtil(String nomeTelefoneUtil) {
		this.nomeTelefoneUtil = nomeTelefoneUtil;
	}

}
