package academy.digitallab.test.service;

import academy.digitallab.test.entity.Category;
import academy.digitallab.test.entity.Test;
import academy.digitallab.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl  implements TestService{


    private final TestRepository testRepository;

    @Override
    public List<Test> listAllTest() {
        return testRepository.findAll();
    }

    @Override
    public Product getTest(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Override
    public Test createTest(Test test) {
        test.setStatus("CREATED");
        test.setCreateAt(new Date());

        return testRepository.save(test);
    }

    @Override
    public Test updateTest(Test test) {
        Test testDB = getTest(test.getId());
        if (null == testDB){
            return null;
        }
        testDB.setName(test.getName());
        testDB.setDescription(test.getDescription());
        testDB.setCategory(test.getCategory());
        testDB.setPrice(test.getPrice());
        return testRepository.save(testDB);
    }

    @Override
    public Test deleteTest(Long id) {
        Test testDB = getTest(id);
        if (null == testDB){
            return null;
        }
        testDB.setStatus("DELETED");
        return testRepository.save(testDB);
    }

    @Override
    public List<Test> findByCategory(Category category) {
        return testRepository.findByCategory(category);
    }

    @Override
    public Test updateStock(Long id, Double quantity) {
        Test testDB = getProduct(id);
        if (null == testDB){
            return null;
        }
        Double stock =  testDB.getStock() + quantity;
        testDB.setStock(stock);
        return testRepository.save(testDB);
    }
}
