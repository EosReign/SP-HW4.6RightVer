package prosky.sphw46rightver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import prosky.sphw46rightver.entity.Avatar;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long>, PagingAndSortingRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(long studentId);
}
