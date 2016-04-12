package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.security.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, String> {
    Menu findByUrl(String url);
}