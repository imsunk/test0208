package test.example.org.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import test.example.org.domain.user.Role;
import test.example.org.domain.user.User;

import java.util.Map;
//OAuth2UserService 를 통해 가져온 OAuth2User의 attribute를 담을 클래스
@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name,String email, String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name =name;
        this.email = email;
        this.picture = picture;
    }

    //of(): OAuth2User에서 반환하는 사용자 정보는 Map이라 값 하나하나를 변환해야만 함

    public  static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // User엔티티생성, 엔티티를 생성하는 시점을 처음 가입할때, 가입시 기본 권한을 Guest로 지정
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
