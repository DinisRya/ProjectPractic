package ru.projectpractic.config;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.projectpractic.utils.UserRoleEnum;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final static Logger LOGGER = LogManager.getLogger();
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/", "/register/**", "/swagger-ui", "/registration").permitAll()
                        .requestMatchers("/admin").hasRole(UserRoleEnum.ADMIN.getValue())
                        .requestMatchers("/students/**").hasRole(UserRoleEnum.STUDENT.getValue())
                        .requestMatchers("/employees/**", "/check").hasRole(UserRoleEnum.EMPLOYEE.getValue())
                        .anyRequest().authenticated()
                )

                .formLogin(login -> {
                    login.loginPage("/login")
                            .permitAll();
//                    login.successHandler((request, response, authentication) ->
//                            LOGGER.info("login successful")
//                    ).permitAll();
//                    login.failureHandler(((request, response, exception) ->
//                            response.sendError(HttpStatus.UNAUTHORIZED.value())));
                })
//                .httpBasic(Customizer.withDefaults())
//                .logout(logout -> logout.permitAll()
//                        .logoutSuccessHandler((request, response, authentication) -> {
//                            response.setStatus(HttpServletResponse.SC_OK);
//                            LOGGER.info("User logged out");
//                        }))
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
