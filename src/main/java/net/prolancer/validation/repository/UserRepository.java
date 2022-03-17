package net.prolancer.validation.repository;

import net.prolancer.validation.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
