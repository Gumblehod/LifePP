package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.AchievementEntity;
import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Entity.RewardEntity;
import com.anq.LifePP.Entity.UserEntity;
import com.anq.LifePP.Repository.CourseRepository;
import com.anq.LifePP.Repository.QuestRepository;
import com.anq.LifePP.Repository.RewardRepository;
import com.anq.LifePP.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	@Autowired
	CourseRepository crepo;
	@Autowired
	QuestRepository q;
	@Autowired
	RewardRepository r;

	public UserEntity insertUser(UserEntity e) {
		return repo.save(e);
	}

	public List<UserEntity> getallUser() {
		return repo.findAll();
	}

	public UserEntity updateUser(int id, UserEntity updatedUser) {
		UserEntity existingUser = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User " + id + " doesn't exist."));

		if (updatedUser.getBirthdate() != null) {
			existingUser.setBirthdate(updatedUser.getBirthdate());
		}
		if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		if (updatedUser.getFname() != null && !updatedUser.getFname().isEmpty()) {
			existingUser.setFname(updatedUser.getFname());
		}
		if (updatedUser.getLname() != null && !updatedUser.getLname().isEmpty()) {
			existingUser.setLname(updatedUser.getLname());
		}
		if (updatedUser.getGender() != null && !updatedUser.getGender().isEmpty()) {
			existingUser.setGender(updatedUser.getGender());
		}
		if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
			existingUser.setPassword(updatedUser.getPassword());
		}
		if (updatedUser.getPnum() != null && !updatedUser.getPnum().isEmpty()) {
			existingUser.setPnum(updatedUser.getPnum());
		}
		if (updatedUser.getType() != 0) {
			existingUser.setType(updatedUser.getType());
		}
		if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
			existingUser.setUsername(updatedUser.getUsername());
		}
		if (updatedUser.getAchievementPoint() != 0) {
			existingUser.setAchievementPoint(updatedUser.getAchievementPoint());
		}
		
		return repo.save(existingUser);
	}

	public String deleteUser(int id) {
		UserEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User " + id + "does not exist"));

		if (c.isDeleted()) {
			return "User #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "User #" + id + "has been deleted";
		}
	}

	public String joinCourse(int userId, int courseId) {
		UserEntity user = repo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		CourseEntity course = crepo.findById(courseId)
				.orElseThrow(() -> new NoSuchElementException("Course not found"));

		List<CourseEntity> userCourses = user.getJoinedCourses();
		if (!userCourses.contains(course)) {
			int currentParticipants = course.getParticipants();
			int maxParticipants = course.getMax();

			if (currentParticipants < maxParticipants) {
				userCourses.add(course);
				user.setJoinedCourses(userCourses);

				course.setParticipants(currentParticipants + 1);

				repo.save(user);
				crepo.save(course);

				return "User #" + userId + " successfully joined Course #" + courseId + ". Participants: "
						+ (currentParticipants + 1) + "/" + maxParticipants;
			} else {
				return "Course #" + courseId + " is already full. Cannot enroll more participants.";
			}
		} else {
			return "User #" + userId + " is already enrolled in Course #" + courseId;
		}
	}

	 public UserEntity forgotPassword(String usernameOrEmail, String newPassword) {
        UserEntity user = repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setPassword(newPassword);
        return repo.save(user);
    }

	public String attemptQuest(int userId, int questId, boolean isCompleted) {
        UserEntity user = repo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

		QuestEntity quest = q.findById(questId).get();

        if (quest != null) {
            if (!isCompleted) {
                if (!user.getOngoingQuests().contains(quest)) {
                    user.getOngoingQuests().add(quest);
                    quest.getOngoingUsers().add(user);

                    repo.save(user);

                    return "User #" + userId + " has started Quest #" + questId;
                } else {
                    return "User #" + userId + " is already attempting Quest #" + questId;
                }
            } else {
                if (user.getOngoingQuests().contains(quest)) {
                    user.getOngoingQuests().remove(quest);
                    quest.getOngoingUsers().remove(user);
                    
                    user.getCompletedQuests().add(quest);
                    quest.getCompletedByUsers().add(user);

                    AchievementEntity a = quest.getAchievement();
					int points = a.getPoint();
                    user.setAchievementPoint(user.getAchievementPoint() + points);
					user.getAchievements().add(a);
					
                    repo.save(user);

                    return "User #" + userId + " has completed Quest #" + questId + ". Points added: " + points;
                } else {
                    return "User #" + userId + " is not attempting Quest #" + questId;
                }
            }
        } else {
            return "Quest #" + questId + " not found";
        }
    }
	public String leaveCourse(int userId, int courseId) {
		UserEntity user = repo.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		CourseEntity course = crepo.findById(courseId)
				.orElseThrow(() -> new NoSuchElementException("Course not found"));
	
		if (user.getJoinedCourses().contains(course)) {
			user.getJoinedCourses().remove(course);
			course.getEnrolledUsers().remove(user);
	
			int currentParticipants = course.getParticipants();
			course.setParticipants(currentParticipants - 1);
	
			repo.save(user);
			crepo.save(course);
	
			return "User #" + userId + " has left Course #" + courseId + ". Participants: "
					+ (currentParticipants - 1) + "/" + course.getMax();
		} else {
			return "User #" + userId + " is not enrolled in Course #" + courseId;
		}
	}
	public String buyReward(int userId, int rewardId) {
		UserEntity user = repo.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		RewardEntity reward = r.findById(rewardId)
				.orElseThrow(() -> new NoSuchElementException("Reward not found"));
		int userPoints = user.getAchievementPoint();
		int rewardPoints = reward.getPoints();
		
		if (userPoints >= rewardPoints) {
			List<RewardEntity> userItems = user.getItems();
			userItems.add(reward);
			user.setItems(userItems);
			
			user.setAchievementPoint(userPoints - rewardPoints);
			repo.save(user);
			
			return "User #" + userId + " successfully bought Reward #" + rewardId;
		} else {
			return "Insufficient achievement points to buy Reward #" + rewardId;
		}
	}
	
	public boolean isEnrolled(int userId, int courseId) {
        UserEntity user = repo.findById(userId).orElse(null);
        if (user != null) {
            for (CourseEntity course : user.getJoinedCourses()) {
                if (course.getCourseID() == courseId) {
                    return true;
                }
            }
        }
        return false;
    }

		public List<CourseEntity> enrolledCourses(int userId) {
       	UserEntity user = repo.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
        return user.getJoinedCourses();
    }

}
