package com.projeto.projetoexperiencias.util;

public class FuncoesUteis {

	/**
	 * Pega a data vinda da base de dados e formata para exibição
	 * @param data
	 * @return
	 */
	public static String consertaData(String data){
		
		String dataCorrigida = "";
		String[] arrayData = data.split("-");
		dataCorrigida = arrayData[2] + "/" + arrayData[1] + "/" + arrayData[0];
		return dataCorrigida;
	}
	
}
