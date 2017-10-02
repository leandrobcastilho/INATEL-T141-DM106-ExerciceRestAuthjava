package br.inatel.t141.dm107.execicirestauth;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.inatel.t141.dm107.execicirestauth.data.product.ProductDAO;
import br.inatel.t141.dm107.execicirestauth.data.product.ProductEntity;
import br.inatel.t141.dm107.execicirestauth.data.productstock.ProductStockDAO;
import br.inatel.t141.dm107.execicirestauth.data.productstock.ProductStockEntity;
import br.inatel.t141.dm107.execicirestauth.data.productstock.StatusProductStock;
import br.inatel.t141.dm107.execicirestauth.data.stock.StockDAO;
import br.inatel.t141.dm107.execicirestauth.data.stock.StockEntity;
import br.inatel.t141.dm107.execicirestauth.debug.LogDebug;

@Path("/productStock")
public class ProductStockService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("/isMinimumQuantityProduct/{productId}")
	public Response isMinimumQuantityByProductId(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductId(productId);
		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		int sum = productStocks.stream().filter(c -> c.getQtd() > 0).mapToInt(ProductStockEntity::getQtd).sum();
		LogDebug.log("Sum " + sum);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		if(sum <= 10)
			return Response.ok(true).build();
		else
			return Response.ok(false).build();
	}

	
	@GET
	@Path("/quantityProduct/{productId}")
	public Response getQuantityByProductId(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductId(productId);
		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		int sum = productStocks.stream().filter(c -> c.getQtd() > 0).mapToInt(ProductStockEntity::getQtd).sum();
		LogDebug.log("Sum " + sum);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(sum).build();
	}

	@GET
	@Path("/quantityProductAvailable/{productId}")
	public Response getQuantityAvailableByProductId(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductIdAndStatus(productId, StatusProductStock.AVAILABLE);
		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		int sum = productStocks.stream().filter(c -> c.getQtd() > 0).mapToInt(ProductStockEntity::getQtd).sum();
		LogDebug.log("Sum " + sum);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(sum).build();
	}

	@GET
	@Path("/quantityProductUnavailable/{productId}")
	public Response getQuantityUnavailableByProductId(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductIdAndStatus(productId, StatusProductStock.UNAVAILABLE);
		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		int sum = productStocks.stream().filter(c -> c.getQtd() > 0).mapToInt(ProductStockEntity::getQtd).sum();
		LogDebug.log("Sum " + sum);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(sum).build();
	}

	
	
	@GET
	@Path("/quantityProductStock/{productId}/{stockId}")
	public Response getQuantityByProductIdAndStockId(@PathParam("productId") Long productId, @PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		ProductStockEntity productStock = new ProductStockDAO().getProductStocksByProductIdStockId(productId, stockId);
		if (productStock == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		int qtd = productStock.getQtd();
		LogDebug.log("Sum " + qtd);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(qtd).build();
	}

	@GET
	@Path("/quantityProductStockAvailable/{productId}/{stockId}")
	public Response getQuantityAvailableByProductIdAndStockId(@PathParam("productId") Long productId, @PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		ProductStockEntity productStock = new ProductStockDAO().getProductStocksByProductIdStockIdAndStatus(productId, stockId, StatusProductStock.AVAILABLE);
		if (productStock == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		int qtd = productStock.getQtd();
		LogDebug.log("Sum " + qtd);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(qtd).build();
	}

	@GET
	@Path("/quantityProductStockUnavailable/{productId}/{stockId}")
	public Response getQuantityUnavailableByProductIdAndStockId(@PathParam("productId") Long productId, @PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");

		ProductStockEntity productStock = new ProductStockDAO().getProductStocksByProductIdStockIdAndStatus(productId, stockId, StatusProductStock.UNAVAILABLE);
		if (productStock == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		int qtd = productStock.getQtd();
		LogDebug.log("Sum " + qtd);
		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(qtd).build();
	}

	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/productsByStock/{stockId}")
	public Response getListProductsByStock(@PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByStockId(stockId);

		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		List<ProductEntity> products = new ArrayList<>();
		for (ProductStockEntity productStockEntity : productStocks) {
			if( productStockEntity.getQtd() > 0 ){
				LogDebug.log("productStockEntity " + productStockEntity.getStockId() + " - " + productStockEntity.getProductId());
				ProductEntity productEntity = new ProductDAO().getProductById(productStockEntity.getProductId());
				LogDebug.log("productEntity " + productEntity.getId() + " - " + productEntity.getName());
				products.add(productEntity);
			}
		}

		LogDebug.log("products.size() " + products.size());
		GenericEntity<List<ProductEntity>> entitys = new GenericEntity<List<ProductEntity>>(products) {
		};

		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(entitys).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/productsAvailableByStock/{stockId}")
	public Response getListProductsAvailableByStock(@PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByStockIdAndStatus(stockId, StatusProductStock.AVAILABLE);

		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		List<ProductEntity> products = new ArrayList<>();
		for (ProductStockEntity productStockEntity : productStocks) {
			if( productStockEntity.getQtd() > 0 ){
				LogDebug.log("productStockEntity " + productStockEntity.getStockId() + " - " + productStockEntity.getProductId());
				ProductEntity productEntity = new ProductDAO().getProductById(productStockEntity.getProductId());
				LogDebug.log("productEntity " + productEntity.getId() + " - " + productEntity.getName());
				products.add(productEntity);
			}
		}

		LogDebug.log("products.size() " + products.size());
		GenericEntity<List<ProductEntity>> entitys = new GenericEntity<List<ProductEntity>>(products) {
		};

		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(entitys).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/productsUnavailableByStock/{stockId}")
	public Response getListProductsUnavailableByStock(@PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByStockIdAndStatus(stockId, StatusProductStock.UNAVAILABLE);

		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		List<ProductEntity> products = new ArrayList<>();
		for (ProductStockEntity productStockEntity : productStocks) {
			if( productStockEntity.getQtd() > 0 ){
				LogDebug.log("productStockEntity " + productStockEntity.getStockId() + " - " + productStockEntity.getProductId());
				ProductEntity productEntity = new ProductDAO().getProductById(productStockEntity.getProductId());
				LogDebug.log("productEntity " + productEntity.getId() + " - " + productEntity.getName());
				products.add(productEntity);
			}
		}

		LogDebug.log("products.size() " + products.size());
		GenericEntity<List<ProductEntity>> entitys = new GenericEntity<List<ProductEntity>>(products) {
		};

		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(entitys).build();
	}
	
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/stocksByProduct/{productId}")
	public Response getListStocksByProduct(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductId(productId);

		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		List<StockEntity> stocks = new ArrayList<>();
		for (ProductStockEntity productStockEntity : productStocks) {
			if( productStockEntity.getQtd() > 0 ){
				StockEntity stockEntity = new StockDAO().getStockById(productStockEntity.getStockId());
				LogDebug.log("stockEntity " + stockEntity.getId() + " - " + stockEntity.getName());
				stocks.add(stockEntity);
			}
		}

		LogDebug.log("stocks.size() " + stocks.size());
		GenericEntity<List<StockEntity>> entitys = new GenericEntity<List<StockEntity>>(stocks) {
		};

		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(entitys).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/stocksAvailableByProduct/{productId}")
	public Response getListStocksAvailableByProduct(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductIdAndStatus(productId, StatusProductStock.AVAILABLE);

		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		List<StockEntity> stocks = new ArrayList<>();
		for (ProductStockEntity productStockEntity : productStocks) {
			if( productStockEntity.getQtd() > 0 ){
				StockEntity stockEntity = new StockDAO().getStockById(productStockEntity.getStockId());
				LogDebug.log("stockEntity " + stockEntity.getId() + " - " + stockEntity.getName());
				stocks.add(stockEntity);
			}
		}

		LogDebug.log("stocks.size() " + stocks.size());
		GenericEntity<List<StockEntity>> entitys = new GenericEntity<List<StockEntity>>(stocks) {
		};

		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(entitys).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/stocksUnavailableByProduct/{productId}")
	public Response getListStocksUnavailableByProduct(@PathParam("productId") Long productId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		List<ProductStockEntity> productStocks = new ProductStockDAO().getProductStocksByProductIdAndStatus(productId, StatusProductStock.UNAVAILABLE);

		if (productStocks == null || productStocks.isEmpty()) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		LogDebug.log("productStocks.size() " + productStocks.size());
		List<StockEntity> stocks = new ArrayList<>();
		for (ProductStockEntity productStockEntity : productStocks) {
			if( productStockEntity.getQtd() > 0 ){
				StockEntity stockEntity = new StockDAO().getStockById(productStockEntity.getStockId());
				LogDebug.log("stockEntity " + stockEntity.getId() + " - " + stockEntity.getName());
				stocks.add(stockEntity);
			}
		}

		LogDebug.log("stocks.size() " + stocks.size());
		GenericEntity<List<StockEntity>> entitys = new GenericEntity<List<StockEntity>>(stocks) {
		};

		LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
		return Response.ok(entitys).build();
	}

	
	
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/addProductStock/{productId}/{stockId}/{qtd}")
	public Response addProductStock(@PathParam("productId") Long productId, @PathParam("stockId") Long stockId, @PathParam("qtd") int qtd) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		ProductEntity productExiste = new ProductDAO().getProductById(productId);
		if (productExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).entity("Product not register").build();
		}

		StockEntity stockExiste = new StockDAO().getStockById(stockId);
		if (stockExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).entity("Stock not register").build();
		}

		ProductStockDAO productStockDAO = new ProductStockDAO();

		try {
			URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));

			ProductStockEntity productStockExiste = productStockDAO.getProductStocksByProductIdStockId(productId, stockId);
			if (productStockExiste == null) {
				LogDebug.log("create");
				productStockExiste = new ProductStockEntity(productExiste, stockExiste);
				productStockExiste.setQtd(qtd);
				if (qtd > 0)
					productStockExiste.setStatus(StatusProductStock.AVAILABLE);
				
				LogDebug.log("productStock.getQtd()"+ productStockExiste.getQtd());
				productStockDAO.createProductStock(productStockExiste);
				
				GenericEntity<ProductStockEntity> entity = new GenericEntity<ProductStockEntity>(productStockExiste) {
				};
				LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
				return Response.created(location).entity(entity).build();

			} else {
				LogDebug.log("Update");
				LogDebug.log("productStock.getQtd() " + productStockExiste.getQtd());
				int newQtd = productStockExiste.getQtd() + qtd;
				LogDebug.log("newQtd " + newQtd);
				productStockExiste.setQtd(newQtd);
				LogDebug.log("productStock.getQtd() " + productStockExiste.getQtd());
				productStockDAO.updateProductStock(productStockExiste);
				
				GenericEntity<ProductStockEntity> entity = new GenericEntity<ProductStockEntity>(productStockExiste) {
				};
				LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
				return Response.ok(entity).location(location).build();

			}
			
		} catch (URISyntaxException e) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/availableStatusProductStock/{productId}/{stockId}")
	public Response availableStatusProductStock(@PathParam("productId") Long productId, @PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		ProductEntity productExiste = new ProductDAO().getProductById(productId);
		if (productExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).entity("Product not register").build();
		}

		StockEntity stockExiste = new StockDAO().getStockById(stockId);
		if (stockExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).entity("Stock not register").build();
		}

		ProductStockDAO productStockDAO = new ProductStockDAO();

		ProductStockEntity productStockExiste = productStockDAO.getProductStocksByProductIdStockId(productId, stockId);
		if (productStockExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		productStockExiste.setStatus(StatusProductStock.AVAILABLE);
		productStockDAO.updateProductStock(productStockExiste);

		GenericEntity<ProductStockEntity> entity = new GenericEntity<ProductStockEntity>(productStockExiste) {
		};

		try {
			URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.ok(entity).location(location).build();

		} catch (URISyntaxException e) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/unavailableStatusProductStock/{productId}/{stockId}")
	public Response unavailableStatusProductStock(@PathParam("productId") Long productId, @PathParam("stockId") Long stockId) {

		LogDebug.log(uriInfo.getAbsolutePath() + " [in]");
		ProductEntity productExiste = new ProductDAO().getProductById(productId);
		if (productExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).entity("Product not register").build();
		}

		StockEntity stockExiste = new StockDAO().getStockById(stockId);
		if (stockExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).entity("Stock not register").build();
		}

		ProductStockDAO productStockDAO = new ProductStockDAO();

		ProductStockEntity productStockExiste = productStockDAO.getProductStocksByProductIdStockId(productId, stockId);
		if (productStockExiste == null) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.NOT_FOUND).build();
		}

		productStockExiste.setStatus(StatusProductStock.UNAVAILABLE);
		productStockDAO.updateProductStock(productStockExiste);

		GenericEntity<ProductStockEntity> entity = new GenericEntity<ProductStockEntity>(productStockExiste) {
		};

		try {
			URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.ok(entity).location(location).build();

		} catch (URISyntaxException e) {
			LogDebug.log(uriInfo.getAbsolutePath() + " [out]");
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
