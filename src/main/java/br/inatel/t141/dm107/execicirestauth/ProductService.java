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

import br.inatel.t141.dm107.execicirestauth.data.product.ProductDAO;
import br.inatel.t141.dm107.execicirestauth.data.product.ProductEntity;

@Path("/product")
public class ProductService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response products() {

		List<ProductEntity> products = new ProductDAO().getProducts();

		if (products == null || products.isEmpty())
			return Response.status(Status.NO_CONTENT).build();

		GenericEntity<List<ProductEntity>> entity = new GenericEntity<List<ProductEntity>>(products) {
		};

		// return Response.ok().entity(entity).build();
		return Response.ok(entity).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response getProduct(@PathParam("id") Long id) {

		ProductEntity Product = new ProductDAO().getProductById(id);
		if (Product == null)
			return Response.status(Status.NOT_FOUND).build();

		GenericEntity<ProductEntity> entity = new GenericEntity<ProductEntity>(Product) {
		};

		return Response.ok(entity).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createProduct(ProductEntity Product) {

		if (Product == null)
			return Response.status(Status.BAD_REQUEST).build();

		new ProductDAO().createProduct(Product);

		GenericEntity<ProductEntity> entity = new GenericEntity<ProductEntity>(Product) {
		};

		try {
			URI location = new URI(String.format("%s/%s", uriInfo.getAbsolutePath(), Product.getId()));

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
	public Response deleteProduct(@PathParam("id") Long id) {

		if (new ProductDAO().delete(id))
			return Response.ok().build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response updateProduct(@PathParam("id") Long id, ProductEntity Product) {

		ProductDAO productDAO = new ProductDAO();
		ProductEntity ProductExiste = productDAO.getProductById(id);
		if (ProductExiste == null)
			return Response.status(Status.NOT_FOUND).build();

		if (!ProductExiste.getId().equals(Product.getId()))
			return Response.status(Status.BAD_REQUEST).build();

		ProductExiste.setName(Product.getName());

		productDAO.updateProduct(ProductExiste);

		GenericEntity<ProductEntity> entity = new GenericEntity<ProductEntity>(ProductExiste) {
		};

		try {
			URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
			return Response.ok(entity).location(location).build();

		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}
