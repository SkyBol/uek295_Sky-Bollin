package ch.noseryoung.sbdemo01.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {return userRepo.findAll();}
    public User getUser(int userId) {
        try {return userRepo.getById((long) userId);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
    public User postUser(String username, String password) {
        try {
            User newUser = new User(username, password);
            userRepo.save(newUser);
            return newUser;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
    public User putUser(int userId, String username, String password) {
        try {
            if (getUser(userId) != null) {
                User updatedUser = new User(userId, username, password);
                userRepo.save(updatedUser);
                return updatedUser;
            }
        } catch (Exception e) {log.error(e.getStackTrace());}
        return null;
    }
    public boolean deleteUser(int userId) {
        try {
            userRepo.deleteById((long)userId);
            return true;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
}
