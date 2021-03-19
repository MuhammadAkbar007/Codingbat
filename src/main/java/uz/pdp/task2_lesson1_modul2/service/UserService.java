package uz.pdp.task2_lesson1_modul2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task2_lesson1_modul2.entity.StarBadge;
import uz.pdp.task2_lesson1_modul2.entity.User;
import uz.pdp.task2_lesson1_modul2.payload.ApiResponse;
import uz.pdp.task2_lesson1_modul2.payload.UserDto;
import uz.pdp.task2_lesson1_modul2.repository.StarBadgeRepository;
import uz.pdp.task2_lesson1_modul2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StarBadgeRepository starBadgeRepository;

    public ResponseEntity<?> add(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Such user already exists !", false));
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!optionalStarBadge.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Star badge by this id not found !", false));
        userRepository.save(new User(userDto.getEmail(), userDto.getPassword(), optionalStarBadge.get()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User successfully added !", true));
    }

    public ResponseEntity<?> getAll() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No user to show !", false));
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User by this email not found !", false));
        return ResponseEntity.ok(optionalUser.get());
    }

    public ResponseEntity<?> edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return ResponseEntity.status(404).body(new ApiResponse("User by this id not found !", false));
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!optionalStarBadge.isPresent())
            return ResponseEntity.status(404).body(new ApiResponse("Star badge not found !", false));
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStarBadge(optionalStarBadge.get());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("User successfully updated !", true));
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("User successfully deleted !", true));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse("User by this id not found !", false));
        }
    }
}
