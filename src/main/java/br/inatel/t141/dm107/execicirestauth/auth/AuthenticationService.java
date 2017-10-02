package br.inatel.t141.dm107.execicirestauth.auth;

import java.io.IOException;
import java.util.Base64;

import br.inatel.t141.dm107.execicirestauth.data.user.UserDAO;
import br.inatel.t141.dm107.execicirestauth.data.user.UserEntity;

public class AuthenticationService {
	public boolean authenticate(String credentials) {

		System.out.println(credentials);

		if (null == credentials)
			return false;
		// extraindo o valor vindo do header "Basic encodedstring" for Basic
		// Exemplo: "Basic YWRtaW46YWRtaW4="
		final String encodedLoginPassword = credentials.replaceFirst("Basic" + " ", "");
		String userLoginAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedLoginPassword);
			userLoginAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String userLoginAndPassSplit[] = userLoginAndPassword.split(":");
		final String userLogin = userLoginAndPassSplit[0];
		final String password = userLoginAndPassSplit[1];

		
		boolean authenticationStatus = "eu".equals(userLogin) && "eu".equals(password);
		if( !authenticationStatus ){
			UserEntity user = new UserDAO().getUserByLogin(userLogin);
			authenticationStatus = user.getLogin().equals(userLogin) &&
					user.getPassword().equals(password);
		}
		return authenticationStatus;
	}

}
