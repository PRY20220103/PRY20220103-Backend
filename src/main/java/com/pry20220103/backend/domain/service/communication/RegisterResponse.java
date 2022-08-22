package com.pry20220103.backend.domain.service.communication;

import com.pry20220103.backend.resource.UserResource;
import com.pry20220103.backend.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}
