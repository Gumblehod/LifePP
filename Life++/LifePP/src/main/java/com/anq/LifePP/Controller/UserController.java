package com.anq.LifePP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anq.LifePP.Entity.AchievementEntity;
import com.anq.LifePP.Entity.CourseEntity;
import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Entity.UserEntity;
import com.anq.LifePP.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService s;

    @GetMapping("/print")
    public String hello() {
        return "It works";
    }

    @PostMapping("/insert")
    public UserEntity insertUser(@RequestBody UserEntity e) {
        return s.insertUser(e);
    }

    @GetMapping("/get")
    public List<UserEntity> getAllUsers() {
        return s.getallUser();
    }

    @PutMapping("/update")
    public UserEntity updateUser(@RequestParam int sid, @RequestBody UserEntity n) {
        return s.updateUser(sid, n);
    }

    @DeleteMapping("/delete/{sid}")
    public String updateUser(@PathVariable int sid) {
        return s.deleteUser(sid);
    }

    @PostMapping("/join/{userId}/{courseId}")
    public String joinCourse(@PathVariable int userId, @PathVariable int courseId) {
        return s.joinCourse(userId, courseId);
    }

    @PostMapping("/forgotPassword")
    public UserEntity forgotPassword(@RequestParam("usernameOrEmail") String usernameOrEmail,
            @RequestParam("newPassword") String newPassword) {
        return s.forgotPassword(usernameOrEmail, newPassword);
    }

    @PostMapping("/{userId}/quests/{questId}/attempt")
    public String attemptQuest(
            @PathVariable int userId,
            @PathVariable int questId,
            @RequestParam(required = false) boolean isCompleted) {
        return s.attemptQuest(userId, questId, isCompleted);
    }

    @DeleteMapping("/leave/{userId}/{courseId}")
    public String leaveCourse(@PathVariable int userId, @PathVariable int courseId) {
        return s.leaveCourse(userId, courseId);
    }

    @PostMapping("/buy/{userId}/{rewardId}")
    public String buyReward(@PathVariable int userId, @PathVariable int rewardId) {
        return s.buyReward(userId, rewardId);
    }

    @GetMapping("/isenrolled/{userId}/{courseId}")
    public boolean isEnrolled(@PathVariable int userId, @PathVariable int courseId) {
        return s.isEnrolled(userId, courseId);
    }

    @GetMapping("/enrolledcourses/{userId}")
    public List<CourseEntity> enrolledCourses(@PathVariable int userId){
        return s.enrolledCourses(userId);
    }

    @GetMapping("/ongoingQuests/{userId}")
    public List<QuestEntity> ongoingQuests(@PathVariable int userId){
        return s.ongoingQuests(userId);
    }

    @GetMapping("/completedQuests/{userId}")
    public List<QuestEntity> completedQuest(@PathVariable int userId){
        return s.completeQuests(userId);
    }
    
    @GetMapping("/getAchievements/{userId}")
    public List<AchievementEntity> getAchievements(@PathVariable int userId){
        return s.getAchievements(userId);
    }
}
