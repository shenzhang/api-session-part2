package com.github.shenzhang.controller.versioning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Zhang Shen
 * Date: 5/31/16
 * Time: 4:54 PM.
 */
@RestController
public class CustomerV1Controller {
    @RequestMapping("/v1/customers")
    public String getAllCustomers() {
        return "V1: URL";
    }

    @RequestMapping(value = "/customers", headers = "api-version=1")
    public String getAllCustomers1() {
        return "V1: Custom Header";
    }

    @RequestMapping(value = "/customers", produces = "application/vnd.customer.v1+json")
    public String getAllCustomers2() {
        return "V1: Accept Header";
    }
}
