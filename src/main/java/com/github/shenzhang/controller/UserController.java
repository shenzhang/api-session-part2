package com.github.shenzhang.controller;

import com.github.shenzhang.dto.UserDto;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Zhang Shen on 5/23/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Log LOG = LogFactory.getLog(ZipkinController.class);

    @ApiOperation(value = "getAllUsers", nickname = "getAllUsers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Shen")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        LOG.info("getAllUsers");
        return newArrayList(new UserDto(1, "zhangsan"), new UserDto(2, "lisi"));
    }
}
