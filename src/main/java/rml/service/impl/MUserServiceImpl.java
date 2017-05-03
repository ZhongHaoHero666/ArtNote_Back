package rml.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.MUserMapper;
import rml.model.User;
import rml.model.UserWeapp;
import rml.service.MUserServiceI;

@Service("muserService")
public class MUserServiceImpl implements MUserServiceI {

	private MUserMapper muserMapper;
		
	public MUserMapper getMuserMapper() {
		return muserMapper;
	}

	@Autowired
	public void setMuserMapper(MUserMapper muserMapper) {
		this.muserMapper = muserMapper;
	}
	

	@Override
	public int insert(User muser) {
		
		return muserMapper.insert(muser);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserMobile(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertWeapp(UserWeapp userWeapp) {
		// TODO Auto-generated method stub
		return 0;
	}


}
