package pl.tdelektro.workshop.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class WorkshopSecurityService implements UserDetailsService {


    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> userList = userRepository.findByIdNotNull();
        if(userList.isEmpty()){
            throw new UsernameNotFoundException("User does not exists. Please register yourself in workshop");
        }else{
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (int i = 0 ; i<userList.size(); i++) {
                if(userList.get(i).getUsername().equals(username)) {
                    var userName = userList.get(i).getUsername();
                    var password = userList.get(i).getPassword();
                    //authorities.add(new SimpleGrantedAuthority(userList.get(i).getAuthority()));
                    authorities.add(new SimpleGrantedAuthority(userList.get(i).getRoles()));

                    return new org.springframework.security.core.userdetails.User(userName, password, authorities);
                }
            }
        }throw new UsernameNotFoundException("User does not exists. Please register yourself in workshop");
    }
}
