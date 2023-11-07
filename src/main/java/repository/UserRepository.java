package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stockanalyzer.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameOrEmail(String username, String email);
    User findByUsernameOrEmail(String username, String email);
}


