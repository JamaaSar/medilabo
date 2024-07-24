package com.service.note.exception;
import static feign.RequestTemplate.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.service.note.exceptions.CustomErrorDecoder;
import feign.Request;
import feign.Request.HttpMethod;
import feign.RequestTemplate;
import feign.Response;
import feign.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomErrorDecoderTest {
    private final CustomErrorDecoder customErrorDecoder = new CustomErrorDecoder();

    @Test
    public void decode_shouldReturnNotFoundException_whenResponseIs404() throws Exception {
        String errorMessage = "Resource not found";
        Request request = Request.create(Request.HttpMethod.GET, "http://localhost",
                new HashMap<>(), null, StandardCharsets.UTF_8, null);
        Response response = Response.builder()
                .status(404)
                .reason("Not Found")
                .request(request)
                .body(errorMessage, StandardCharsets.UTF_8)
                .build();

        Exception exception = customErrorDecoder.decode("methodKey", response);

        assertNotNull(exception);
        assertNotNull(exception.getMessage());
    }

    @Test
    public void decode_shouldReturnBadRequestException_whenResponseIs400() throws Exception {
        String errorMessage = "Bad request";
        Request request = Request.create(Request.HttpMethod.GET, "http://localhost",
                new HashMap<>(), null, StandardCharsets.UTF_8, null);
        Response response = Response.builder()
                .status(400)
                .reason("Bad Request")
                .request(request)
                .body(errorMessage, StandardCharsets.UTF_8)
                .build();

        Exception exception = customErrorDecoder.decode("methodKey", response);

        assertNotNull(exception);
        assertNotNull(exception.getMessage());
    }

    @Test
    public void decode_shouldReturnDefaultException_whenResponseIsNot400Or404() throws Exception {
        String errorMessage = "Internal server error";
        Request request = Request.create(Request.HttpMethod.GET, "http://localhost",
                new HashMap<>(), null, StandardCharsets.UTF_8, null);
        Response response = Response.builder()
                .status(500)
                .reason("Internal Server Error")
                .request(request)
                .body(errorMessage, StandardCharsets.UTF_8)
                .build();

        Exception exception = customErrorDecoder.decode("methodKey", response);
        assertNotNull(exception);
    }
    @Test
    public void decode_shouldHandleIOException_whenReadingResponseBody() throws Exception {
        // Given
        Response response = mock(Response.class);
        Request request = Request.create(HttpMethod.GET, "http://localhost", new HashMap<>(), null, StandardCharsets.UTF_8, null);

        when(response.status()).thenReturn(404);
        Response.Body body = mock(Response.Body.class);
        when(response.body()).thenReturn(body);
        when(body.asInputStream()).thenThrow(new IOException("Simulated IOException"));

        Exception exception = customErrorDecoder.decode("methodKey", response);

        assertNotNull(exception);
    }
}
