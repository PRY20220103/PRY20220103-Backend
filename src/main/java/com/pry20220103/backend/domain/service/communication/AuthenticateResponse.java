package com.pry20220103.backend.domain.service.communication;

import com.pry20220103.backend.resource.AuthenticateResource;
import com.pry20220103.backend.shared.domain.service.communication.BaseResponse;


public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {

    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}
