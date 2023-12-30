package com.backend.rangurura.Services;

import com.backend.rangurura.dtos.RegisterDto;
import com.backend.rangurura.dtos.UserUpdateDto;
import com.backend.rangurura.dtos.VerifyOtpDto;
import com.backend.rangurura.response.ApiResponse;

public interface UserService {
    public ApiResponse<Object> registerUser(RegisterDto dto) throws Exception;
    public ApiResponse<Object> verifyOtp(VerifyOtpDto dto) throws Exception;
    public ApiResponse<Object> updateUser(UserUpdateDto dto) throws Exception;
}
