package com.apps.schedulerApp.api.controller;


import  io.swagger.annotations.Api;
import  io.swagger.annotations.ApiOperation;
import  io.swagger.annotations.ApiResponse;
import  io.swagger.annotations.ApiResponses;

import  lombok.AllArgsConstructor;
import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import	org.springframework.http.HttpHeaders;
import  org.springframework.http.HttpStatus;
import  org.springframework.http.MediaType;
import  org.springframework.http.ResponseEntity;
import  org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="", produces = MediaType.TEXT_HTML_VALUE)
@Api(value = "Return poc settings", produces = MediaType.TEXT_HTML_VALUE)
@AllArgsConstructor

public class AppsController {
    private static Logger logger = LoggerFactory.getLogger(AppsController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Say hello!")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = String.class)})
    public ResponseEntity<?> sayHelo() throws Exception {    	
        String resource = "<html><b><p>Happy Fathers Day Family!!</p></b></html>";
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}