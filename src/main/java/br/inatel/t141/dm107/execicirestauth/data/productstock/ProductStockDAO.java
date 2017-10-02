package br.inatel.t141.dm107.execicirestauth.data.productstock;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.t141.dm107.execicirestauth.data.Database;
import br.inatel.t141.dm107.execicirestauth.data.product.ProductEntity;
import br.inatel.t141.dm107.execicirestauth.data.stock.StockEntity;
import br.inatel.t141.dm107.execicirestauth.debug.LogDebug;

public class ProductStockDAO {

	public ProductStockDAO() {

	}

	public ProductStockEntity getProductStock(ProductEntity productEntity, StockEntity stockEntity) {
		return getProductStock(productEntity.getId(), stockEntity.getId());
	}

	public ProductStockEntity getProductStock(Long productEntityId, Long stockEntityId) {

		LogDebug.log("getProductStock(" + productEntityId + "," + stockEntityId + ") [in]");

		ProductStockEntity Product = null;
		for (ProductStockEntity productStock : Database.getInstance().getProductStocks()) {
			if (productStock.getProductId().equals(productEntityId)
					&& productStock.getStockId().equals(stockEntityId)) {
				Product = productStock;
				break;
			}
		}

		LogDebug.log("getProductStock(" + productEntityId + "," + stockEntityId + ") [out]");
		return Product;
	}

	public List<ProductStockEntity> getProductStocks() {
		return Database.getInstance().getProductStocks();
	}

	public List<ProductStockEntity> getProductStocksByStockId(StockEntity stockEntity) {
		return getProductStocksByStockId(stockEntity.getId());
	}

	public List<ProductStockEntity> getProductStocksByStockId(Long stockId) {

		LogDebug.log("getProductStocksByStockId(" + stockId + ") [in]");
		List<ProductStockEntity> resultadoProductStocks = Database.getInstance().getProductStocks().stream()
				.filter(c -> c.getStockId().equals(stockId)).collect(Collectors.toList());

		LogDebug.log("resultadoProductStocks.size() " + resultadoProductStocks.size());
		LogDebug.log("getProductStocksByStockId(" + stockId + ") [in]");
		return resultadoProductStocks;
	}

	public List<ProductStockEntity> getProductStocksByStockIdAndStatus(StockEntity stockEntity, StatusProductStock statusProductStock) {
		return getProductStocksByStockIdAndStatus(stockEntity.getId(), statusProductStock);
	}

	public List<ProductStockEntity> getProductStocksByStockIdAndStatus(Long stockId, StatusProductStock statusProductStock) {

		LogDebug.log("getProductStocksByStockIdAndStatus(" + stockId + ", " + statusProductStock + ") [in]");
		List<ProductStockEntity> resultadoProductStocks = Database.getInstance().getProductStocks().stream()
				.filter(c -> c.getStockId().equals(stockId) && c.getStatus().equals(statusProductStock)).collect(Collectors.toList());

		LogDebug.log("resultadoProductStocks.size() " + resultadoProductStocks.size());
		LogDebug.log("getProductStocksByStockIdAndStatus(" + stockId + ", " + statusProductStock + ") [in]");
		return resultadoProductStocks;
	}
	
	public List<ProductStockEntity> getProductStocksByProductId(ProductEntity productEntity) {
		return getProductStocksByProductId(productEntity.getId());
	}

	public List<ProductStockEntity> getProductStocksByProductId(Long productId) {

		LogDebug.log("getProductStocksByProductId(" + productId + ") [in]");

		List<ProductStockEntity> resultadoProductStocks = Database.getInstance().getProductStocks().stream()
				.filter(c -> c.getProductId().equals(productId)).collect(Collectors.toList());

		LogDebug.log("resultadoProductStocks.size() " + resultadoProductStocks.size());
		LogDebug.log("getProductStocksByProductId(" + productId + ") [out]");
		return resultadoProductStocks;
	}
	
	public List<ProductStockEntity> getProductStocksByProductIdAndStatus(ProductEntity productEntity, StatusProductStock statusProductStock) {
		return getProductStocksByProductIdAndStatus(productEntity.getId(), statusProductStock);
	}

	public List<ProductStockEntity> getProductStocksByProductIdAndStatus(Long productId, StatusProductStock statusProductStock) {

		LogDebug.log("getProductStocksByProductIdAndStatus(" + productId + ", " + statusProductStock + ") [in]");

		List<ProductStockEntity> resultadoProductStocks = Database.getInstance().getProductStocks().stream()
				.filter(c -> c.getProductId().equals(productId) && c.getStatus().equals(statusProductStock)).collect(Collectors.toList());

		LogDebug.log("resultadoProductStocks.size() " + resultadoProductStocks.size());
		LogDebug.log("getProductStocksByProductIdAndStatus(" + productId + ", " + statusProductStock + ") [out]");
		return resultadoProductStocks;
	}
	
	public ProductStockEntity getProductStocksByProductIdStockId(ProductEntity productEntity, StockEntity stockEntity) {
		return getProductStocksByProductIdStockId(productEntity.getId(), stockEntity.getId());
	}

	public ProductStockEntity getProductStocksByProductIdStockId(Long productId, Long stockId) {

		ProductStockEntity productStockEntity = null;
		LogDebug.log("getProductStocksByProductIdStockId(" + productId + ", " + stockId + ") [in]");

		List<ProductStockEntity> resultadoProductStocks = Database.getInstance().getProductStocks().stream()
				.filter(c -> c.getProductId().equals(productId) && c.getStockId().equals(stockId))
				.collect(Collectors.toList());
		LogDebug.log("resultadoProductStocks.size() " + resultadoProductStocks.size());

		if (resultadoProductStocks.isEmpty()) {
			LogDebug.log("getProductStocksByProductIdStockId(" + productId + ", " + stockId + ") [out]");
			return productStockEntity;
		}

		productStockEntity = resultadoProductStocks.get(0);

		LogDebug.log("getProductStocksByProductIdStockId(" + productId + ", " + stockId + ") [out]");
		return productStockEntity;
	}


	public ProductStockEntity getProductStocksByProductIdStockIdAndStatus(ProductEntity productEntity, StockEntity stockEntity, StatusProductStock statusProductStock) {
		return getProductStocksByProductIdStockIdAndStatus(productEntity.getId(), stockEntity.getId(), statusProductStock);
	}

	public ProductStockEntity getProductStocksByProductIdStockIdAndStatus(Long productId, Long stockId, StatusProductStock statusProductStock) {

		ProductStockEntity productStockEntity = null;
		LogDebug.log("getProductStocksByProductIdStockIdAndStatus(" + productId + ", " + stockId + ", " + statusProductStock + ") [in]");

		List<ProductStockEntity> resultadoProductStocks = Database.getInstance().getProductStocks().stream()
				.filter(c -> c.getProductId().equals(productId) && c.getStockId().equals(stockId) && c.getStatus().equals(statusProductStock))
				.collect(Collectors.toList());
		LogDebug.log("resultadoProductStocks.size() " + resultadoProductStocks.size());

		if (resultadoProductStocks.isEmpty()) {
			LogDebug.log("getProductStocksByProductIdStockIdAndStatus(" + productId + ", " + stockId + ", " + statusProductStock + ") [out]");
			return productStockEntity;
		}

		productStockEntity = resultadoProductStocks.get(0);

		LogDebug.log("getProductStocksByProductIdStockIdAndStatus(" + productId + ", " + stockId + ", " + statusProductStock + ") [out]");
		return productStockEntity;
	}

	
	public ProductStockEntity createProductStock(ProductStockEntity entity) {

		LogDebug.log("createProductStock(" + entity + ") [out]");
		Database.getInstance().getProductStocks().add(entity);

		LogDebug.log("getProductStocks().size() " + Database.getInstance().getProductStocks().size());
		LogDebug.log("createProductStock(" + entity + ") [out]");
		return entity;
	}

	public ProductStockEntity updateProductStock(ProductStockEntity entityToUpdate) {

		LogDebug.log("updateProductStock(" + entityToUpdate + ") [in]");
		int index = Database.getInstance().getProductStocks()
				.indexOf(getProductStock(entityToUpdate.getProductId(), entityToUpdate.getStockId()));

		LogDebug.log("index " + index);
		Database.getInstance().getProductStocks().remove(index);
		Database.getInstance().getProductStocks().add(index, entityToUpdate);

		LogDebug.log("getProductStocks().size() " + Database.getInstance().getProductStocks().size());
		LogDebug.log("updateProductStock(" + entityToUpdate + ") [out]");
		return entityToUpdate;
	}

	public boolean delete(Long productEntityId, Long stockEntityId) {

		LogDebug.log("updateProduct(" + productEntityId + "," + stockEntityId + ") [in]");
		ProductStockEntity entity = getProductStock(productEntityId, stockEntityId);
		if (entity != null) {
			Database.getInstance().getProductStocks().remove(entity);
			LogDebug.log("getProductStocks().size() " + Database.getInstance().getProductStocks().size());
			LogDebug.log("updateProduct(" + productEntityId + "," + stockEntityId + ") [out]");
			return true;
		} else {
			LogDebug.log("getProductStocks().size() " + Database.getInstance().getProductStocks().size());
			LogDebug.log("updateProduct(" + productEntityId + "," + stockEntityId + ") [out]");
			return false;
		}
	}
}
