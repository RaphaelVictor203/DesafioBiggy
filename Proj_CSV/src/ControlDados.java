import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ControlDados {

	public void topN(int n, ArrayList<ClienteInfo> clInfo){
		ArrayList<Integer> idsProdsTopN = new ArrayList();
		ArrayList<ProdutoCompra> prods = new ArrayList();
		HashMap<Integer, Integer> hpTodosVendidos = new HashMap<>();
		int idProd = 0;
		int value = 0;
		/*for(ClienteInfo cl : clInfo) {
			idProd = returnId(cl.getBoughtProductsIds());
			value = count(idProd, cl.getBoughtProductsIds());
			if(hpTodosVendidos.containsKey(idProd)) {
				hpTodosVendidos.put(idProd, (hpTodosVendidos.get(idProd) + value));
			}else {
				hpTodosVendidos.put(idProd, value);
			}
		}
		for (Map.Entry<Integer, Integer> entrada : hpTodosVendidos.entrySet()) {
		   System.out.println(entrada);
		}*/
		for(ClienteInfo cl : clInfo) {
			idProd = returnId(cl.getBoughtProductsIds());
			value = count(idProd, cl.getBoughtProductsIds());
			if(exists(idProd, prods)) {
				ProdutoCompra prc = getPc(idProd, prods);
				prc.setQntd(prc.getQntd() + value);
				//hpTodosVendidos.put(idProd, (hpTodosVendidos.get(idProd) + value));
			}else {
				ProdutoCompra prc = new ProdutoCompra();
				prc.setIdProd(idProd);
				prc.setQntd(value);
				prods.add(prc);
				//hpTodosVendidos.put(idProd, value);
			}
		}
		for(ProdutoCompra pc : prods) {
			System.out.println(pc.getIdProd() + " - " + pc.getQntd());
		}
		
	}
	
	private ProdutoCompra getPc(int id, ArrayList<ProdutoCompra> pc) {
		for(ProdutoCompra p : pc) {
			if(id == p.getIdProd()) {
				return p;
			}
		}
		return null;
	}
	
	private boolean exists(int id, ArrayList<ProdutoCompra> pc) {
		for(ProdutoCompra p : pc) {
			if(id == p.getIdProd()) {
				return true;
			}
		}
		return false;
	}
	
	private int count(int id, ArrayList<Integer> ids) {
		int count = 0;
		for(int i : ids) {
			if(i == id) {
				count++;
			}
		}
		return count;
	}
	
	private int returnId(ArrayList<Integer> ids) {
		int max = 0;
		int idMax = 0;
		for(int id : ids) {
			if(count(id, ids) > max) {
				max = count(id, ids);
				idMax = id;
			}
		}
		return idMax;
	}
	
}
