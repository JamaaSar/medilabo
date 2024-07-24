package com.service.note.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String message = "";

        try {
            if (response.body() != null) {
                message = IOUtils.toString(response.body().asInputStream(),
                        response.request().charset());
            }
        } catch (IOException e) {
            System.out.println("Erreur inconnu");
        }
        if (response.status() == 404) {
            return new NotFoundException(message);
        }else if (response.status() == 400) {
            return new BadRequestException(message);
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
