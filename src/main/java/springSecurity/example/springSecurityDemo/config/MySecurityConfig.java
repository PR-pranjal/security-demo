package springSecurity.example.springSecurityDemo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import springSecurity.example.springSecurityDemo.service.UserService;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class MySecurityConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
//                        .antMatchers("/home", "/login", "/register").permitAll()
                               // .dispatcherTypeMatchers(HttpMethod.valueOf("/public/**")).hasRole("USER")
                                .requestMatchers("/public/**").hasRole("USER")
                                .requestMatchers("/users/**").hasRole("ADMIN")
                                //.dispatcherTypeMatchers(HttpMethod.valueOf("/users/**")).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                //.httpBasic(withDefaults());
                .formLogin();
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails userDetails= User.builder()
                .username("username")
                .password(this.passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin=User.builder()
                .username("admin")
                .password(this.passwordEncoder().encode("Admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails,admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
