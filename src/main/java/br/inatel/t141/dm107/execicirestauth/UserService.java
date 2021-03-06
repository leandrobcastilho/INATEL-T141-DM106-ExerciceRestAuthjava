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

import br.inatel.t141.dm107.execicirestauth.data.user.UserDAO;
import br.inatel.t141.dm107.execicirestauth.data.user.UserEntity;

@Path("/user")
public class UserService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response users() {

		List<UserEntity> users = new UserDAO().getUsers();

		if (users == null || users.isEmpty())
			return Response.status(Status.NOT_FOUND).build();

		GenericEntity<List<UserEntity>> entity = new GenericEntity<List<UserEntity>>(users) {
		};

		// return Response.ok().entity(entity).build();
		return Response.ok(entity).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response getUser(@PathParam("id") Long id) {

		UserEntity User = new UserDAO().getUserById(id);
		if (User == null)
			return Response.status(Status.NOT_FOUND).build();

		GenericEntity<UserEntity> entity = new GenericEntity<UserEntity>(User) {
		};

		return Response.ok(entity).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(UserEntity User) {

		if (User == null)
			return Response.status(Status.BAD_REQUEST).build();

		new UserDAO().createUser(User);

		GenericEntity<UserEntity> entity = new GenericEntity<UserEntity>(User) {
		};

		try {
			URI location = new URI(String.format("%s/%s", uriInfo.getAbsolutePath(), User.getId()));

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
	public Response deleteUser(@PathParam("id") Long id) {

		if (new UserDAO().delete(id))
			return Response.ok().build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response updateUser(@PathParam("id") Long id, UserEntity User) {

		UserDAO userDAO = new UserDAO();
		UserEntity UserExiste = userDAO.getUserById(id);
		if (UserExiste == null)
			return Response.status(Status.NOT_FOUND).build();

		if (!UserExiste.getId().equals(User.getId()))
			return Response.status(Status.BAD_REQUEST).build();

		UserExiste.setLogin(User.getLogin());
		UserExiste.setPassword(User.getPassword());

		userDAO.updateUser(UserExiste);

		GenericEntity<UserEntity> entity = new GenericEntity<UserEntity>(UserExiste) {
		};

		try {
			URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
			return Response.ok(entity).location(location).build();

		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}
