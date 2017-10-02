package br.inatel.t141.dm107.execicirestauth.data.productstock;

import javax.xml.bind.annotation.XmlRootElement;

import br.inatel.t141.dm107.execicirestauth.data.product.ProductEntity;
import br.inatel.t141.dm107.execicirestauth.data.stock.StockEntity;

@XmlRootElement
public class ProductStockEntity {

	public ProductStockEntity() {
	}

	private Long productId;
	private Long stockId;
	private int qtd;
	private StatusProductStock status;

	public ProductStockEntity(ProductEntity productEntity, StockEntity stockEntity) {
		this.productId = productEntity.getId();
		this.stockId = stockEntity.getId();
		this.qtd = 0;
		this.status = StatusProductStock.UNAVAILABLE;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public StatusProductStock getStatus() {
		return status;
	}

	public void setStatus(StatusProductStock status) {
		this.status = status;
	}

}
