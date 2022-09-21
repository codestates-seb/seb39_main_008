package it.mainPr.config;

import it.mainPr.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private CorsConfig corsConfig; //@CrossOrigin (인증 x) 시큐리티 필터에 등록 (인증o)
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        return http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .apply(new MyCustomDsl()) // 커스텀 필터 등록
//                .and()
//                .authorizeHttpRequests(authorize -> authorize.antMatchers("/api/v1/members/**")
//                        .access("hasRole('ROLE_MEMBER') or hasRole('ROLE_NON_MEMBER')")
//                        .antMatchers("/api/v1/NON_MEMBER/**")
//                        .access("hasRole('ROLE_NON_MEMBER')")
//                        .anyRequest().permitAll())
//                .build();

//    }
//
//    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//            http
//                    .addFilter(corsConfig.corsFilter())
//                    .addFilter(new JWTAuthenticationFilter())
//        }
//    }
//}
