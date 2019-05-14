
public class Produto {
	
	private int id;
	private int categoria;
	private String nome;
	
	public Produto(int id, int categoria, String nome) {
		this.id = id;
		this.categoria = categoria;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
