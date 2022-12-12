package academy.digitallab.result.client;

import academy.digitallab.result.model.Test;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test-service")
@RequestMapping(value = "/tests")
public interface TestClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Test> getTest(@PathVariable("id") Long id);

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Test> updateStockTest(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity);
    }
