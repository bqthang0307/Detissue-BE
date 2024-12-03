package com.DIY.Detissue.controller;

import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.SignupRequest;
import com.DIY.Detissue.payload.request.UserRequest;
import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.payload.response.ShopOrderResponse;
import com.DIY.Detissue.payload.response.ShoppingCartItemResponse;
import com.DIY.Detissue.service.Imp.ShopOrderServiceImp;
import com.DIY.Detissue.service.Imp.ShoppingCartItemServiceImp;
import com.DIY.Detissue.service.Imp.UserServiceImp;
import com.DIY.Detissue.utils.JwtHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng ký thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ. Kiểm tra các lỗi trong dữ liệu đầu vào.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ nội bộ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class)))
    })
    @PostMapping("signup")
    public ResponseEntity<?> signup(@Valid SignupRequest signupRequest, BindingResult bindingResult) {
        // Get list error
        List<FieldError> listError = bindingResult.getFieldErrors();
        for (FieldError errors : listError) {
            throw new RuntimeException(errors.getDefaultMessage());
        }

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.addUser(signupRequest));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng nhập thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "403", description = "Thông tin xác thực không hợp lệ. Vui lòng kiểm tra tên người dùng và mật khẩu.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ. Kiểm tra các lỗi trong dữ liệu đầu vào.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ nội bộ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class)))
    })
    @PostMapping("signin")
    public ResponseEntity<?> signin(
            @Parameter(description = "Username for signin", example = "nguyenvana") @RequestParam String username,
            @Parameter(description = "Password for signin", example = "12345678") @RequestParam String password
    ) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(token);
        User user = userServiceImp.findByUsername(username);
        String jwt = jwtHelper.generateToken(String.valueOf(user.getId()));
        userServiceImp.updateLoginTime(username);

        //Nếu chứng thực thành công sẽ chạy code tiếp theo còn nếu thất bại thì sẽ văng lỗi chưa chứng thực
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setData(jwt);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("address")
    public ResponseEntity<?> findAllAdressByUserId(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.findAddressByUserId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ủy quyền thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "401", description = "Không có quyền truy cập. Vui lòng kiểm tra token.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "403", description = "Truy cập bị cấm. Người dùng không có quyền truy cập tài nguyên này.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ. Kiểm tra các lỗi trong dữ liệu đầu vào.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ nội bộ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class)))
    })
    @PostMapping("authorize")
    public ResponseEntity<?> authorize(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.authorizeAdminByUserId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<?> getUserDetail(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.getUserById(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String token, @Valid UserRequest request, BindingResult bindingResult) {
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);

        if (bindingResult.hasErrors()) {
            List<FieldError> listError = bindingResult.getFieldErrors();
            for (FieldError errors : listError) {
                throw new CustomException(errors.getDefaultMessage());
            }
        }
        request.setId(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.updateUser(request));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
