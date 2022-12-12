package academy.digitallab.test;

import academy.digitallab.test.entity.Category;
import academy.digitallab.test.entity.Product;
import academy.digitallab.test.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class TestRepositoryMockTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    public void whenFindByCategory_thenReturnListTest(){
        Test test01 = Test.builder()
                .name("Test de sangre")
                .category(Category.builder().id(1L).build())
                .description("Test de sangre avanzado para comprobar tus niveles de azucar, grasa y oxigeno")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("15.99"))
                .status("Created")
                .createAt(new Date()).build();
        testRepository.save(test01);

        List<Test> founds= testRepository.findByCategory(test01.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(3);


    }
}
