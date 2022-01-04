package library.dto;

import java.util.Objects;

public class LivroDto {
	
	private String nome;
	private String dataDeLancamento;
	private Integer numPaginas;
	private Long autorId;
	
	public LivroDto() {
		
	}

	public LivroDto(String nome, String dataDeLancamento, Integer numPaginas, Long autorId) {
		super();
		this.nome = nome;
		this.dataDeLancamento = dataDeLancamento;
		this.numPaginas = numPaginas;
		this.autorId = autorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(String dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Integer numPaginas) {
		this.numPaginas = numPaginas;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorId, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivroDto other = (LivroDto) obj;
		return Objects.equals(autorId, other.autorId) && Objects.equals(nome, other.nome);
	}
	
	

}