//package it.mainPr.auth.refresh.entity;
//
//import it.mainPr.audit.BaseTime;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class RefreshToken extends BaseTime {
//
//    @Id
//    private String tokenKey;
//
//    private String tokenValue;
//
//    public RefreshToken updateToken(String tokenValue) {
//        this.tokenValue = tokenValue;
//        return this;
//    }
//
//    @Builder
//    public RefreshToken(String tokenKey, String tokenValue) {
//        this.tokenKey = tokenKey;
//        this.tokenValue = tokenValue;
//    }
//}