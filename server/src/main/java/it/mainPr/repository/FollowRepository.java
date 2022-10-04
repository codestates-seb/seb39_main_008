package it.mainPr.repository;

import it.mainPr.model.Follow;
import it.mainPr.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowee(Member follower, Member followee);
    List<Follow> findByFollower(Member Follower);
    List<Follow> findByFollowee(Member Followee);
}
