package br.inatel.t141.dm107.execicirestauth.data.stock;

import java.util.List;

import br.inatel.t141.dm107.execicirestauth.data.Database;

public class StockDAO {

	public StockDAO() {
	}

	public StockEntity getStockById(Long id) {
		StockEntity Stock = null;
		for (StockEntity e : Database.getInstance().getStocks()) {
			if (e.getId().equals(id)) {
				Stock = e;
				break;
			}
		}
		return Stock;
	}

	public StockEntity createStock(StockEntity entity) {
		entity.setId(Database.getInstance().getNextStockKey());
		Database.getInstance().getStocks().add(entity);
		return entity;
	}

	public StockEntity updateStock(StockEntity entityToUpdate) {
		int index = Database.getInstance().getStocks().indexOf(getStockById(entityToUpdate.getId()));
		Database.getInstance().getStocks().remove(index);
		Database.getInstance().getStocks().add(index, entityToUpdate);

		return entityToUpdate;
	}

	public List<StockEntity> getStocks() {
		return Database.getInstance().getStocks();
	}

	public boolean delete(Long id) {
		StockEntity entity = getStockById(id);
		if (entity != null) {
			Database.getInstance().getStocks().remove(entity);
			return true;
		} else {
			return false;
		}
	}
}
