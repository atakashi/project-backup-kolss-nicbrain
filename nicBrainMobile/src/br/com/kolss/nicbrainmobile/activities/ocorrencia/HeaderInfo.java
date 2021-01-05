package br.com.kolss.nicbrainmobile.activities.ocorrencia;

import java.util.ArrayList;

public class HeaderInfo {

	private String name;
	private ArrayList<DetailInfo> procList = new ArrayList<DetailInfo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<DetailInfo> getProcList() {
		return procList;
	}

	public void setProcList(ArrayList<DetailInfo> procList) {
		this.procList = procList;
	}

}