package com.github.shenzhang.controller.versioning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Zhang Shen
 * Date: 5/31/16
 * Time: 4:54 PM.
 */
@RestController
public class CustomerV2Controller {
    @RequestMapping("/v2/customers")
    public String getAllCustomers() {
        return "V2: URL";
    }

    @RequestMapping(value = "/customers", headers = "api-version=2")
    public String getAllCustomers1() {
        return "V2: Custom Header";
    }

    @RequestMapping(value = "/customers", produces = "application/vnd.customer.v2+json")
    public String getAllCustomers2() {
        return "V2: Accept Header";
    }
}
