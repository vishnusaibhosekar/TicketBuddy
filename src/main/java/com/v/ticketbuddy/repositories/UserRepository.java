package com.v.ticketbuddy.repositories;

import com.v.ticketbuddy.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * created on: 06/12/20
 * created by: Vishnu
 */

public interface UserRepository extends CrudRepository<User, Integer> {
}
