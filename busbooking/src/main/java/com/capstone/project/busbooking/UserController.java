package com.capstone.project.busbooking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Tag(name = "User", description = "User management APIs")
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "register";
    }
    @Operation(
            summary = "User Registration",
            description = "User Registration.",
            tags = { "User Registration", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserEntity userEntity, BindingResult result, Model model){
        UserEntity user = userRepository.findByEmailId(userEntity.getEmailId());
        if(user != null){
            result.rejectValue("emailId", "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userEntity);
            return "/register";

        }
        userService.saveUser(userEntity);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/view-profile")
    public String showUserProfileO(Model model, Principal principal){
        UserEntity userEntity =  userRepository.findByEmailId(principal.getName());
        model.addAttribute("user", userEntity);
        return "user-profile";
    }
    @Operation(
            summary = "Update Profile",
            description = "Update Profile.",
            tags = { "Update Profile", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/user-profile/save")
    public String updateProfile(@ModelAttribute("user") @Valid UserEntity userEntity, BindingResult result, Model model, Principal principal){
        if (result.hasErrors()){
            return "user-profile";
        }else {
            userEntity.setId(userRepository.findByEmailId(principal.getName()).getId());
            userRepository.save(userEntity);
            return "redirect:/view-profile?success";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(){
     return "change-password"  ;
    }
    @Operation(
            summary = "Update Password",
            description = "Update Password.",
            tags = { "Update Password", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/change-password/save")
    public String updatePassword(@RequestParam String currentPassword, String newPassword, String confirmPassword, Principal principal, Model model){
        UserEntity user = userRepository.findByEmailId(principal.getName());
//        String encodedCurrentPassword = passwordEncoder.encode(currentPassword);
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        if(!passwordEncoder.matches(currentPassword, user.getPassword()) ){
            model.addAttribute("errorMsg", "Current password entered is wrong!");
            return "change-password";
        }else if (!newPassword.equals(confirmPassword)){
            model.addAttribute("errorMsg", "New password and Confirm Password does not match!");
            return "change-password";
        }else{
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return "redirect:/change-password?success";
        }

    }
}
