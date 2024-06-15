package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.request.CreateProductRequest;
import com.DIY.Detissue.payload.request.UpdateProductRequest;
import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("")
    ResponseEntity<?> findAllProduct(@RequestParam int page,
                                     @RequestParam int size) {

        List<ProductResponse> list = productServiceImp.findAllProduct(page, size);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findProductById(@PathVariable int id) {
        ProductResponse result = productServiceImp.findById(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category")
    ResponseEntity<?> findProductByCategory(@RequestParam int page,
                                            @RequestParam int size,
                                            @RequestParam int id) {
        List<ProductResponse> list = productServiceImp.findProductByCategory(page, size, id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<?> searchProduct(@RequestParam int page,
                                    @RequestParam int size,
                                    @RequestParam String keyword) {
        List<ProductResponse> list = productServiceImp.findProductBySearch(keyword, page, size);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request,
                                           BindingResult bindingResult
    ) {

        List<FieldError> listError = bindingResult.getFieldErrors();
        for (FieldError errors : listError) {
            throw new RuntimeException(errors.getDefaultMessage());
        }

        BaseResponse response = new BaseResponse();
        response.setData(productServiceImp.addProduct(request));
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<?> deleteProduct(@RequestParam int id) {
        BaseResponse response = new BaseResponse();
        response.setData(productServiceImp.deleteProduct(id));
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<?> updateProduct(@Valid UpdateProductRequest request,
                                           BindingResult bindingResult
    ) {

        List<FieldError> listError = bindingResult.getFieldErrors();
        for (FieldError errors : listError) {
            throw new RuntimeException(errors.getDefaultMessage());
        }

        BaseResponse response = new BaseResponse();
        response.setData(productServiceImp.updateProduct(request));
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
