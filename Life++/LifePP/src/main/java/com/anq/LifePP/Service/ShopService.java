package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.ShopEntity;
import com.anq.LifePP.Repository.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	ShopRepository repo;
	
	public ShopEntity insertShop(ShopEntity e) {
		return repo.save(e);
	}
	
	public List<ShopEntity> getallShop(){
		return repo.findAll();
	}
	
	public ShopEntity updateShop(int id, ShopEntity c) {
		ShopEntity e = new ShopEntity();
			try {
				e = repo.findById(id).get();
				e.setName(c.getName());
				e.setDescription(c.getDescription());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Shop "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteShop(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				repo.deleteById(id);
				msg = "Shop " + id + "has been deleted";
			}
			else {msg = "Shop " + id + "doesnt't exist";}
			
			return msg;
	}
}
