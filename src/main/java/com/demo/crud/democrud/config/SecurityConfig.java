package com.demo.crud.democrud.config;

import com.demo.crud.democrud.service.CustomUserServiceImp;
import com.demo.crud.democrud.util.JwtAuthenticationFilter;
import com.demo.crud.democrud.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

// @EnableWebSecurity
// @Configuration
// @RequiredArgsConstructor
// public class SecurityConfig {
//
//  private final CustomUserServiceImp customUserServiceImp;
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests(
//            (auth) ->
//                auth.antMatchers("/","/user/**",
// "/auth/**").permitAll().anyRequest().authenticated())
//        .httpBasic(withDefaults());
//    http.headers()
//            .frameOptions().sameOrigin();
//    http.userDetailsService(customUserServiceImp);
//    return http.build();
//  }
//
//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**"));
//  }
// }
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean(); // authenticationManager??? Bean ???????????????.
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .exceptionHandling()
        .accessDeniedHandler(jwtAccessDeniedHandler)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests() // ????????? ?????? ???????????? ??????
        .antMatchers("/test/**")
        .authenticated()
        .antMatchers("/admin/**")
        .hasRole("ADMIN")
        .antMatchers("/user/**")
        .hasRole("USER")
        .antMatchers("/**")
        .permitAll()
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
    // JwtAuthenticationFilter??? UsernamePasswordAuthenticationFilter ?????? ?????????
    // + ????????? ????????? ??????????????? ??????????????? ?????? ????????? CustomUserDetailService ???????????? ???????????????.
  }
}
