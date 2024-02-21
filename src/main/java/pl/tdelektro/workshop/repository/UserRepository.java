package pl.tdelektro.workshop.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long userId);

    List<User> findByIdNotNull();

    User findByCars_Id(Long id);
}
