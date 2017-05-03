package rml.service;

import java.util.List;

import rml.model.User;
import rml.model.UserWeapp;

public interface MUserServiceI {

	List<User> getAll();
	
	User selectByPrimaryKey(String id);
	
    int insert(User muser);

    User getUserMobile(String mobile);

	int insertWeapp(UserWeapp userWeapp);

}
