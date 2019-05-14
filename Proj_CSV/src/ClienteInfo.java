import java.util.ArrayList;

public class ClienteInfo {
//User ID,Clicked Product Ids,Bought Product Ids
	private int id;
	private ArrayList<Integer> productsIds;
	private ArrayList<Integer> boughtProductsIds;
	
	public ClienteInfo() {
		this.productsIds = new ArrayList();
		this.boughtProductsIds = new ArrayList();
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Integer> getProductsIds() {
		return productsIds;
	}
	public void setProductsIds(ArrayList<Integer> productsIds) {
		this.productsIds = productsIds;
	}
	public ArrayList<Integer> getBoughtProductsIds() {
		return boughtProductsIds;
	}
	public void setBoughtProductsIds(ArrayList<Integer> boughtProductsIds) {
		this.boughtProductsIds = boughtProductsIds;
	}
	
	public void preencheClickedProductsIds(String[] idProducts) {
		for(int i=0; i<idProducts.length; i++) {
			this.productsIds.add(Integer.parseInt(idProducts[i]));
		}
	}
	
	public void preencheBoughtProductsIds(String[] idProducts) {
		for(int i=0; i<idProducts.length; i++) {
			this.boughtProductsIds.add(Integer.parseInt(idProducts[i]));
		}
	}
	
	public void listIdProducts(ArrayList<Integer> list, String title) {
		System.out.println(title + "--------------------------------");
		for(Integer id : list) {
			System.out.println(id);
		}
		System.out.println("--------------------------------------------");
	}
	
	
}
