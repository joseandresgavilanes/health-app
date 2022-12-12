package academy.digitallab.test;

import academy.digitallab.test.entity.Category;
import academy.digitallab.test.entity.Test;
import academy.digitallab.test.repository.TestRepository;
import academy.digitallab.test.service.TestService;
import academy.digitallab.test.service.TestServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestServiceMockTest {

    @Mock
    private TestRepository testRepository;

    private TestService testService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        testService =  new TestServiceImpl( testRepository);
        Test sangre =  Test.builder()
                .id(1L)
                .name("Test de sangre avanzado")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(testRepository.findById(1L))
                .thenReturn(Optional.of(sangre));
        Mockito.when(testRepository.save(sangre)).thenReturn(sangre);

    }

    @Test
   public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
       Assertions.assertThat(found.getName()).isEqualTo("computer");

   }

   @Test
   public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
   }
}
