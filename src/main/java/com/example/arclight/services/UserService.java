package com.example.arclight.services;

import com.example.arclight.configurations.JwtService;
import com.example.arclight.entities.User;
import com.example.arclight.entities.datatypes.Role;
import com.example.arclight.models.UserModel;
import com.example.arclight.models.auth.AuthenticationRequest;
import com.example.arclight.models.auth.AuthenticationResponse;
import com.example.arclight.models.auth.RegisterRequest;
import com.example.arclight.models.auth.RegisterResponse;
import com.example.arclight.models.users.ProfileRequest;
import com.example.arclight.models.users.ProfileResponse;
import com.example.arclight.repositories.UserRepository;
import com.example.arclight.shared.exceptions.ArclightException;
import com.example.arclight.shared.helpers.HttpHelper;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    private  final UserRepository userRepository;
    private  final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;
    private  final PasswordEncoder passwordEncoder;

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    public  UserService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    public List<UserModel> GetUsers(){

        List<User> users= userRepository.findAll();
        List<UserModel> result = users.stream()
                .map(u -> new UserModel(u.Id,u.firstName, u.lastName,u.birthDay,u.email, u.Age))
                .toList();
        return result;
    }

    public  UserModel GetProfile(Long id) throws ArclightException {
        logger.info("Getting user {} profile",id);

        var user= userRepository.findById(id).orElseThrow(()-> new ArclightException("User not found"));
        return new UserModel(user.Id,user.firstName, user.lastName,user.birthDay,user.email, user.Age);
    }

   public ProfileResponse UpdateProfile(ProfileRequest profileRequest) throws ArclightException {
       if(StringIsNullOrEmpty(profileRequest.getFirstName()))
           throw new ArclightException("First name is empty");
       if(StringIsNullOrEmpty(profileRequest.getFirstName()))
           throw new ArclightException("Last name is empty");
       if(profileRequest.getBirthDay()==null)
           throw new ArclightException("Birthday is null");

       logger.info("Getting user profile from context");
       var auth= SecurityContextHolder.getContext().getAuthentication();
       if(!auth.isAuthenticated()){
           var details= auth.getDetails();
           logger.error("User {} is not authenticated", details);
           throw new ArclightException("user is not authenticated");
       }
       var user = userRepository.findByEmail(auth.getName()).orElseThrow(()-> new ArclightException("User not found")); // name should contain the enail
       //var user= (User)auth.getPrincipal();//var user= userRepository.findById(((User)auth.getPrincipal()))

       user.birthDay= profileRequest.getBirthDay();
       user.firstName= profileRequest.getFirstName();
       user.lastName= profileRequest.getLastName();
       user.secondaryLanguage= profileRequest.getSecondaryLanguage();
         return new ProfileResponse(user.firstName, user.lastName,"/api/v1/files/{id}", user.birthDay);
     }

    public  UserModel GetUser() throws ArclightException {
        logger.info("Getting user profile");
       var auth= SecurityContextHolder.getContext().getAuthentication();
       if(!auth.isAuthenticated()){ // toggle this
           var details= auth.getDetails();
           logger.error("User {} is not authenticated", details);
           throw  new ArclightException("user is not authenticated");
       }

        var u= (User)auth.getPrincipal();
//        var u= userRepository.findById(user.Id)
//                .orElseThrow(()-> new ArclightException("User not found"));
        if(u== null)
            throw  new ArclightException("User not found");

        var result= new UserModel(u);
        return result;
    }

    public RegisterResponse register(RegisterRequest registerRequest) throws ArclightException {

        logger.info("User is registering with email {} and name {}", registerRequest.getEmail(), registerRequest.getName());
        // Check if auto mapper exist in Java
        if(StringIsNullOrEmpty(registerRequest.getEmail()))
            throw  new ArclightException("Email is empty");
        if(emailIsValid(registerRequest.getEmail()))
            throw  new ArclightException("Email is not valid");
        if(StringIsNullOrEmpty(registerRequest.getPassword()))
            throw new ArclightException("Password is empty");

        if(StringIsNullOrEmpty(registerRequest.getName()))
            throw new ArclightException("Name is empty");
        if(StringIsNullOrEmpty(registerRequest.getSurname()))
            throw new ArclightException("Surname is empty");

        var existinguser= userRepository.findByEmail(registerRequest.getEmail());

        if(!existinguser.isEmpty())
        {
            logger.error("User {} already exits", existinguser.get().email );
            throw new ArclightException("User exists already");
        }
        var hashedPassword= //hashWith256(registerRequest.getPassword());
        passwordEncoder.encode(registerRequest.getPassword());
        var user= new User.UserBuilder(registerRequest.getName(),registerRequest.getSurname())
                .setEmail(registerRequest.getEmail())
                .setPassword(hashedPassword)
                .setRole(Role.User)
                .build();

        userRepository.save(user);

        var response= new RegisterResponse(user.Id,user.email,user.firstName, user.lastName);
        return  response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws ArclightException {

        var auth= SecurityContextHolder.getContext().getAuthentication();
        var details =auth.getDetails().toString();
        logger.info("User {} is logging in with email {}", details,authenticationRequest.getEmail());

        if(StringIsNullOrEmpty(authenticationRequest.getEmail()))
          throw  new ArclightException("Email is empty");
        if(StringIsNullOrEmpty(authenticationRequest.getPassword()))
            throw new ArclightException("Password is empty");

        var user= userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(()-> new ArclightException("User not found"));

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        var extraClaims = new HashMap<String, Object>();
        var jwtToken= jwtService.GenerateToken(extraClaims, user);
        return new AuthenticationResponse.Builder()
                //.builder()
                .setToken(jwtToken)
                .build();
    }

    private  void authenticate(String email, String password) throws ArclightException {

        try{
            var result =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email,
                    password
            ));
            logger.info("User authenticated with the right credentials");
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw  new ArclightException("Wrong email or password");
        }

    }

    // Helper method
    private static boolean StringIsNullOrEmpty(String str){
        return (str == null && str.trim().isEmpty());
    }

    public static String hashWith256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
            // return  String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private  static boolean emailIsValid(String s){

        var EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return EMAIL.matcher(s).matches();
    }
}
