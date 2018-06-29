package by.gsu.epamlab.impl;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.beans.Role;

public class UserImpl {
	public User getUser(String login, String password) {
		Role role;
		if ("sys".equals(login)) {
			if("111".equals(password)) {
				role = Role.ADMIN;
			} else {
				role = Role.FAKER;
			}
		} else {
			if ("boss".equals(login)) {
				if("000".equals(password)) {
					role = Role.USER;
				} else {
					role = Role.FAKER;
				}
			} else {
				role = Role.VISITOR;
			}
		}
		return new User(login, role);
	}
}
