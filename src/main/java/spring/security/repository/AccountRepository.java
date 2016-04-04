package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.security.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}