import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Produto> produtos = new ArrayList();
		ArrayList<ClienteInfo> clInfo = new ArrayList();
		ControlDados cd = new ControlDados();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\workspace_biggy\\Proj_CSV\\products - Sheet1.csv"));
		try {
			br.readLine();
			while(br.ready()) {
				String produto = br.readLine();
				String[] infoProd = produto.split(",");
				produtos.add(new Produto(Integer.parseInt(infoProd[0]), Integer.parseInt(infoProd[1]), infoProd[2]));
				//System.out.println(linha);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		br = new BufferedReader(new FileReader("C:\\workspace_biggy\\Proj_CSV\\user_behavior - Sheet1.csv"));
		try {
			br.readLine();
			while(br.ready()) {
				ClienteInfo cl = new ClienteInfo();
				String linha = br.readLine();
				String[] infoClienteCompra = linha.split(",");
				cl.setId(Integer.parseInt(infoClienteCompra[0]));
				cl.preencheClickedProductsIds(infoClienteCompra[1].split(";"));
				if(infoClienteCompra.length >= 3) {
					cl.preencheBoughtProductsIds(infoClienteCompra[2].split(";"));
				}
				clInfo.add(cl);
				cl.listIdProducts(cl.getBoughtProductsIds(), "bought");
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		cd.topN(10, clInfo);

	}

}
