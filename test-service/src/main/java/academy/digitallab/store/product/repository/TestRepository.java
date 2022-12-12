package academy.digitallab.test.repository;

import academy.digitallab.test.entity.Category;
import academy.digitallab.test.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TestRepository  extends JpaRepository<Test, Long> {

    public List<Test> findByCategory(Category category);
}
