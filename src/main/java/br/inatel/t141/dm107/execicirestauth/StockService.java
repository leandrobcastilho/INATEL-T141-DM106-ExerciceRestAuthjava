package br.inatel.t141.dm107.execicirestauth;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import br.inatel.t141.dm107.execicirestauth.data.stock.StockDAO;
import br.inatel.t141.dm107.execicirestauth.data.stock.StockEntity;

@Path("/stock")
public class StockService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response stocks() {

		List<StockEntity> stocks = new StockDAO().getStocks();

		if (stocks == null || stocks.isEmpty())
			return Response.status(Status.NO_CONTENT).build();

		GenericEntity<List<StockEntity>> entity = new GenericEntity<List<StockEntity>>(stocks) {
		};

		// return Response.ok().entity(entity).build();
		return Response.ok(entity).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response getStock(@PathParam("id") Long id) {

		StockEntity Stock = new StockDAO().getStockById(id);
		if (Stock == null)
			return Response.status(Status.NOT_FOUND).build();

		GenericEntity<StockEntity> entity = new GenericEntity<StockEntity>(Stock) {
		};

		return Response.ok(entity).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createStock(StockEntity Stock) {

		if (Stock == null)
			return Response.status(Status.BAD_REQUEST).build();

		new StockDAO().createStock(Stock);

		GenericEntity<StockEntity> entity = new GenericEntity<StockEntity>(Stock) {
		};

		try {
			URI location = new URI(String.format("%s/%s", uriInfo.getAbsolutePath(), Stock.getId()));

			// return Response
			// .status(Status.CREATED)
			// .location(location)
			// .entity(entity)
			// .build();

			return Response.created(location).entity(entity).build();

		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response deleteStock(@PathParam("id") Long id) {

		if (new StockDAO().delete(id))
			return Response.ok().build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response updateStock(@PathParam("id") Long id, StockEntity Stock) {

		StockDAO stockDAO = new StockDAO();
		StockEntity StockExiste = stockDAO.getStockById(id);
		if (StockExiste == null)
			return Response.status(Status.NOT_FOUND).build();

		if (!StockExiste.getId().equals(Stock.getId()))
			return Response.status(Status.BAD_REQUEST).build();

		StockExiste.setName(Stock.getName());

		stockDAO.updateStock(StockExiste);

		GenericEntity<StockEntity> entity = new GenericEntity<StockEntity>(StockExiste) {
		};

		try {
			URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
			return Response.ok(entity).location(location).build();

		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}
