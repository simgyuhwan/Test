package com.only.practice.testcontainer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Gyuhwan
 */
interface CustomerRepository extends JpaRepository<Customer, Long> {

}
