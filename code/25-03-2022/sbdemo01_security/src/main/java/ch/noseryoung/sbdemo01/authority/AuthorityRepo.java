package ch.noseryoung.sbdemo01.authority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}
