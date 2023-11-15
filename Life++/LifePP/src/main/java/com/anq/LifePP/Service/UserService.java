package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.UserEntity;
import com.anq.LifePP.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	public UserEntity insertUser(UserEntity e) {
		return repo.save(e);
	}
	
	public List<UserEntity> getallUser(){
		return repo.findAll();
	}
	
	public UserEntity updateUser(int id, UserEntity c) {
		UserEntity e = new UserEntity();
			try {
				e = repo.findById(id).get();
				e.setBirthdate(c.getBirthdate());
				e.setEmail(c.getEmail());
				e.setFname(c.getFname());
				e.setGender(c.getGender());
				e.setLname(c.getLname());
				e.setPassword(c.getPassword());
				e.setPnum(c.getPnum());
				e.setType(c.getType());
				e.setUsername(c.getUsername());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("User "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteUser(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				repo.deleteById(id);
				msg = "User " + id + "has been deleted";
			}
			else {msg = "User " + id + "doesnt't exist";}
			
			return msg;
	}
}
