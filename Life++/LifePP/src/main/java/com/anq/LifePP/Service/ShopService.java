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

	public List<ShopEntity> getallShop() {
		return repo.findAll();
	}

	public ShopEntity updateShop(int id, ShopEntity c) {
		ShopEntity e = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Shop " + id + "doesn't exist."));

		e.setName(c.getName());
		e.setDescription(c.getDescription());

		return repo.save(e);
	}

	public String deleteShop(int id) {
		ShopEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Shop " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Shop #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Shop #" + id + "has been deleted";
		}
	}
}
