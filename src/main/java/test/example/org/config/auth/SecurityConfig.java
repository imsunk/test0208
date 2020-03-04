package test.example.org.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import test.example.org.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화시켜준다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    //authorizeRequests: url별 권한 관리를 설정하는 옵션의 시작점 (authorizeRequests을 선언해야만 antMatchers을 쓸 수있다
    //antMatchers: 권한 관리대상을 지정하는 옵션, permitAll=전체열람권한
    //anyRequest: 설정된 값들 외의 url들

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/","/css/**", "/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
