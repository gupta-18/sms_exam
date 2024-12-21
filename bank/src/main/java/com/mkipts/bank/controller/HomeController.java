package com.mkipts.bank.controller;

import com.mkipts.bank.entity.UserCredential;
import com.mkipts.bank.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

@GetMapping("/")
    public  String index(){
    return "index";
}

@GetMapping("/service")
    public  String service(){
    return "service";
}

@GetMapping("/about")
    public  String about(){
    return "about";
}

@GetMapping("/contact")
    public  String contact(){
    return "contact";
}
@GetMapping("/login")
    public  String login(){
    return "/user/login";
}

@GetMapping("/registration")
    public  String registration(){
    return "/user/registration";
}

@GetMapping("/administration")
    public  String administration(){
    return "administration";
}
@GetMapping("/forgetpassword")
    public  String forgotPassword(){
    return "forgetPassword";
}




    @Autowired
    private IUserService iUserService; // Service to fetch user details

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String userid, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        UserCredential userCredential = iUserService.findUserByUsername(userid);

        if (userCredential != null && iUserService.isPasswordValid(password, userCredential.getPassword(), userCredential.getPasswordSalt())) {
            String role = userCredential.getUserRole();
            String redirectUrl = determineRedirectUrl(role, Long.valueOf(userCredential.getId()));
            response.put("redirectUrl", redirectUrl);
            return ResponseEntity.ok(response);
        }

        response.put("message", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    private String determineRedirectUrl(String role, Long userId) {
        switch (role) {
            case "Admin":
                return "/admin/dashboard";
            case "Employee":
                return "/employee/dashboard";
            case "Customer":
                return "/user/dashboard?userid=" + userId; // Include the user ID in the URL
            default:
                return "/";
        }
    }


}





