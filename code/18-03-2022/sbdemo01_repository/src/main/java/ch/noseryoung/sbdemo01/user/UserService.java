package ch.noseryoung.sbdemo01.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }
    public UserEntity getUser(String id) {
        return userRepo.getById(Long.parseLong(id));
    }
    public boolean postUser(String id, String username, String password) {
        try {
            UserEntity newUser = new UserEntity(Long.parseLong(id), username, password);
            userRepo.save(newUser);
        } catch (Exception e) {return false;}
        return true;
    }
    public boolean deleteUser(String id) {
        try {
            userRepo.deleteById(Long.parseLong(id));
        } catch (Exception e) {return false;}
        return true;
    }
}
