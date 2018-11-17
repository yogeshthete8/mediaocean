package com.ors.retailshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/swagger-ui.html")
        .antMatchers("/v2/api-docs")
        .antMatchers("/webjars/springfox-swagger-ui/**")
        .antMatchers("/h2-console/**")
        .antMatchers("/configuration/*")
        .antMatchers("/swagger-resources*")
        .antMatchers("/health");
  }		

  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
     auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("yogesh").password("password")
       .roles("USER").and().withUser("admin").password("admin")
       .roles("USER", "ADMIN");
    }

    // Authorization : Role -> Access
    protected void configure(HttpSecurity http) throws Exception {
     http.httpBasic().and().authorizeRequests().antMatchers("/bill/**")
       .hasRole("USER").antMatchers("/product/**")
       .hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
       .csrf().disable().headers().frameOptions().disable();
    }

}
