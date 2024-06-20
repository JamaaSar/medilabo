package com.service.evaluation.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "note", url = "http://localhost:8082/patient")
public interface PatientServiceClient {


}
