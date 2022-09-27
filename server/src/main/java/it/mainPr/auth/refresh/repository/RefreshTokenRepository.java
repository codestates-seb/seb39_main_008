//package it.mainPr.auth.refresh.repository;
//
//import it.mainPr.auth.refresh.entity.RefreshToken;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
//    Optional<RefreshToken> findByTokenKey(String tokenKey);
//}