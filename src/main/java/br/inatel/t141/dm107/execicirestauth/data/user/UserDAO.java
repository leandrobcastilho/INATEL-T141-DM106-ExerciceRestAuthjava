package br.inatel.t141.dm107.execicirestauth.data.user;

import java.util.List;

import br.inatel.t141.dm107.execicirestauth.data.Database;

public class UserDAO {

	public UserDAO() {
	}

	public UserEntity getUserByLogin(String login) {
		UserEntity User = null;
		for (UserEntity e : Database.getInstance().getUsers()) {
			if (e.getLogin().equalsIgnoreCase(login)) {
				User = e;
				break;
			}
		}
		return User;
	}

	public UserEntity getUserById(Long id) {
		UserEntity User = null;
		for (UserEntity e : Database.getInstance().getUsers()) {
			if (e.getId().equals(id)) {
				User = e;
				break;
			}
		}
		return User;
	}

	public UserEntity createUser(UserEntity entity) {
		entity.setId(Database.getInstance().getNextUserKey());
		Database.getInstance().getUsers().add(entity);
		return entity;
	}

	public UserEntity updateUser(UserEntity entityToUpdate) {
		int index = Database.getInstance().getUsers().indexOf(getUserById(entityToUpdate.getId()));
		Database.getInstance().getUsers().remove(index);
		Database.getInstance().getUsers().add(index, entityToUpdate);

		return entityToUpdate;
	}

	public List<UserEntity> getUsers() {
		return Database.getInstance().getUsers();
	}

	public boolean delete(Long id) {
		UserEntity entity = getUserById(id);
		if (entity != null) {
			Database.getInstance().getUsers().remove(entity);
			return true;
		} else {
			return false;
		}
	}
}
