package academy.digitallab.test.controller;

import academy.digitallab.test.entity.Category;
import academy.digitallab.test.entity.Test;
import academy.digitallab.test.service.TestService;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/tests")
public class TestController {

    @Autowired
    private TestService testService ;

    @GetMapping
    public ResponseEntity<List<Test>> listTest(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Test> tests = new ArrayList<>();
        if (null ==  categoryId){
            tests = testService.listAllTest();
            if (tests.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            tests = testService.findByCategory(Category.builder().id(categoryId).build());
            if (tests.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(tests);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Test> getTest(@PathVariable("id") Long id) {
        Test test =  testService.getTest(id);
        if (null==test){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(test);
    }

    @PostMapping
    public ResponseEntity<Test> createTest(@Valid @RequestBody Test test, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Test testCreate =  testService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

   @PutMapping(value = "/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") Long id, @RequestBody Test test){
        test.setId(id);
        Test testDB =  testService.updateTest(test);
        if (testDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Test> deleteTest(@PathVariable("id") Long id){
        Test testDelete = testService.deleteTest(id);
        if (testDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testDelete);
    }
    @PutMapping (value = "/{id}/stock")
    public ResponseEntity<Test> updateStockTest(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity){
        Test test = testService.updateStock(id, quantity);
        if (test == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(test);
    }
    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
