package com.senac.darley_johnny.entity.dto;

public class  FuncionarioLoginDTORequest {
  private String matricula;
  private String chaveAcesso;

		public String getMatricula() {
				return matricula;
		}

		public void setMatricula(String matricula) {
				this.matricula = matricula;
		}

		public String getChaveAcesso() {
				return chaveAcesso;
		}

		public void setChaveAcesso(String chaveAcesso) {
				this.chaveAcesso = chaveAcesso;
		}
}