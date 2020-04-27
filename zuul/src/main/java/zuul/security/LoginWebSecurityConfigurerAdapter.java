package zuul.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import common.security.JwtConfig;

@Configuration
@EnableWebSecurity
public class LoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setUserDetailsService(userDetailsService);
	provider.setPasswordEncoder(new BCryptPasswordEncoder());
	return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//	http
//		// disables csrf
//		.csrf().disable()
//		//add jwt filter
//		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
//		// authorization requests config
//		.authorizeRequests()
//		// allow all who are accessing "auth" service
//		.antMatchers(HttpMethod.POST, "/auth").permitAll().antMatchers("/login").permitAll()
//		// Any other request must be authenticated
//		.anyRequest().authenticated();
//		//sets login page
//		//.and().formLogin().loginPage("/login").permitAll();
	http.cors().and().csrf().disable()
		// make sure we use stateless session; session won't be used to store user's
		// state.
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// handle an authorized attempts
		.exceptionHandling()
		.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
		// Add a filter to validate the tokens with every request
		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		// authorization requests config
		.authorizeRequests()
		// allow all who are accessing "auth" service
		.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
		// must be an admin if trying to access admin area (authentication is also
		// required here)
		.antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
		// Any other request must be authenticated
		.anyRequest().authenticated();

    }

    @Bean
    public JwtConfig jwtConfig() {
	return new JwtConfig();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
	configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", configuration);
	return source;
    }
}
