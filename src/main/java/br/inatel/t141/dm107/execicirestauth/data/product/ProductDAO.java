package br.inatel.t141.dm107.execicirestauth.data.product;

import java.util.List;

import br.inatel.t141.dm107.execicirestauth.data.Database;

public class ProductDAO {

	public ProductDAO() {
	}

	public ProductEntity getProductById(Long id) {
		ProductEntity Product = null;
		for (ProductEntity e : Database.getInstance().getProducts()) {
			if (e.getId().equals(id)) {
				Product = e;
				break;
			}
		}
		return Product;
	}

	public ProductEntity createProduct(ProductEntity entity) {
		entity.setId(Database.getInstance().getNextProductKey());
		Database.getInstance().getProducts().add(entity);
		return entity;
	}

	public ProductEntity updateProduct(ProductEntity entityToUpdate) {
		int index = Database.getInstance().getProducts().indexOf(getProductById(entityToUpdate.getId()));
		Database.getInstance().getProducts().remove(index);
		Database.getInstance().getProducts().add(index, entityToUpdate);

		return entityToUpdate;
	}

	public List<ProductEntity> getProducts() {
		return Database.getInstance().getProducts();
	}

	public boolean delete(Long id) {
		ProductEntity entity = getProductById(id);
		if (entity != null) {
			Database.getInstance().getProducts().remove(entity);
			return true;
		} else {
			return false;
		}
	}
}
