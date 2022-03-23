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

    public List<UserEntity> getAllUsers() {return userRepo.findAll();}
    public UserEntity getUser(int id) {
        try {return userRepo.getById((long) id);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
    public boolean postUser(int id, String username, String password) {
        try {
            UserEntity newUser = new UserEntity(id, username, password);
            userRepo.save(newUser);
            return true;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
    public boolean putUser(int id, String username, String password) {
        try {return getUser(id) != null && postUser(id, username, password);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
    public boolean deleteUser(int id) {
        try {
            userRepo.deleteById((long)id);
            return true;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
}
