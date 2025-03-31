package uaedunung.se.dao;

import uaedunung.se.entity.User;

public class UserDAO extends BaseDAO<User>{
    public UserDAO() {
        super(User.class);
    }
}
