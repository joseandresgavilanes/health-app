package academy.digitallab.test.service;

import academy.digitallab.test.entity.Category;
import academy.digitallab.test.entity.Test;

import java.util.List;

public interface TestService {
    public List<Test> listAllTest();
    public Test getTest(Long id);

    public Test createTest(Test test);
    public Test updateTest(Test test);
    public  Test deleteTest(Long id);
    public List<Test> findByCategory(Category category);
    public Test updateStock(Long id, Double quantity);
}
