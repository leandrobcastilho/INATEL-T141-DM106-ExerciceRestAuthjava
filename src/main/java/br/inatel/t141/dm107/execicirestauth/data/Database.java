package br.inatel.t141.dm107.execicirestauth.data;

import java.util.ArrayList;
import java.util.List;

import br.inatel.t141.dm107.execicirestauth.data.product.ProductEntity;
import br.inatel.t141.dm107.execicirestauth.data.productstock.ProductStockEntity;
import br.inatel.t141.dm107.execicirestauth.data.productstock.StatusProductStock;
import br.inatel.t141.dm107.execicirestauth.data.stock.StockEntity;
import br.inatel.t141.dm107.execicirestauth.data.user.UserEntity;
import br.inatel.t141.dm107.execicirestauth.debug.LogDebug;

public class Database {

	private static Database database = null;

	private List<UserEntity> users;
	private List<ProductEntity> products;
	private List<StockEntity> estoques;
	private List<ProductStockEntity> productStocks;

	public List<UserEntity> getUsers() {
		if (users == null)
			users = new ArrayList<>();
		return users;
	}

	public List<ProductEntity> getProducts() {
		if (products == null)
			products = new ArrayList<>();
		return products;
	}

	public List<StockEntity> getStocks() {
		if (estoques == null)
			estoques = new ArrayList<>();
		return estoques;
	}

	public List<ProductStockEntity> getProductStocks() {
		if (productStocks == null)
			productStocks = new ArrayList<>();
		return productStocks;
	}

	public static Database getInstance() {
		if (database == null)
			database = new Database();
		return database;
	}

	private Database() {
		initUsers();
		initProducts();
		initStocks();
		initProductStocks();
	}

	private void initUsers() {
		for (int i = 1; i <= 5; i++) {
			getUsers().add(new UserEntity(new Long(i), "User" + i, "Password" + i));
		}
	}

	public Long getNextUserKey() {
		return new Long(getUsers().size() + 1);
	}

	private void initProducts() {
		for (int i = 1; i <= 10; i++) {
			getProducts().add(new ProductEntity(new Long(i), "Product:" + i));
		}
	}

	public Long getNextProductKey() {
		return new Long(getProducts().size() + 1);
	}

	private void initStocks() {
		for (int i = 1; i <= 5; i++) {
			getStocks().add(new StockEntity(new Long(i), "Stock:" + i));
		}
	}

	public Long getNextStockKey() {
		return new Long(getStocks().size() + 1);
	}

	private void initProductStocks() {

		LogDebug.log("initProductStocks [in]");
		int qtd = getStocks().size();
		int countItens = getProducts().size();
		boolean isAvailable = true;
		LogDebug.log("getStocks().size() " + getStocks().size());
		LogDebug.log("getProducts().size() " + getProducts().size());
		for (int indexStock = 0; indexStock < getStocks().size(); indexStock++) {

			StockEntity estoqueEntity = getStocks().get(indexStock);

			for (int indexProduct = 0; indexProduct < countItens; indexProduct++) {

				ProductEntity productEntity = getProducts().get(indexProduct);

				ProductStockEntity productStockEntity = new ProductStockEntity(productEntity, estoqueEntity);
				productStockEntity.setQtd(qtd);
				if(isAvailable){
					isAvailable = false;
					productStockEntity.setStatus(StatusProductStock.AVAILABLE);
				}else{
					isAvailable = true;
					productStockEntity.setStatus(StatusProductStock.UNAVAILABLE);
				}

				LogDebug.log("productStockEntity.getProductId() " + productStockEntity.getProductId());
				LogDebug.log("productStockEntity.getStockId() " + productStockEntity.getStockId());
				LogDebug.log("productStockEntity.getQtd() " + productStockEntity.getQtd());
				LogDebug.log("productStockEntity.getStatus() " + productStockEntity.getStatus());
				getProductStocks().add(productStockEntity);
			}
			countItens--;
			qtd--;
		}
		LogDebug.log("getProductStocks().size() " + getProductStocks().size());
		LogDebug.log("initProductStocks [out]");
	}
}
