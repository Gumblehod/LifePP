package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.CoachEntity;
import com.anq.LifePP.Repository.CoachRepository;
import com.anq.LifePP.Repository.CourseRepository;

@Service
public class CoachService {

	@Autowired
	CoachRepository repo;
    @Autowired
    CourseRepository courseRepo;

	public CoachEntity insertCoach(CoachEntity e) {
		return repo.save(e);
	}

	public List<CoachEntity> getallCoach() {
		return repo.findAll();
	}

	public CoachEntity updateCoach(int id, CoachEntity c) {
		CoachEntity e = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Coach " + id + "doesn't exist."));

		if (c.getEmail() != null && c.getName() != null) {
			e.setEmail(c.getEmail());
			e.setName(c.getName());
			e.setUsername(c.getUsername());
		}

		return repo.save(e);
	}

	public String deleteCoach(int id) {
		CoachEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Coach " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Coach #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Coach #" + id + "has been deleted";
		}
	}

}
