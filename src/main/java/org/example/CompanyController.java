package org.example;

import org.example.entity.Company;
import org.example.entity.CompanyVersionTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CompanyController {

    @Autowired
    private Map<String, Company> companiesVersionOneMap;

    @Autowired
    private Map<String, CompanyVersionTwo> companiesVersionTwoMap;

    @GetMapping("/v1/companies/{id}")
    public ResponseEntity<Company> getCompanies(@PathVariable("id") String id) {

        System.out.println("In Service1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json"));

        if(companiesVersionOneMap.containsKey(id))
            return new ResponseEntity<Company>(companiesVersionOneMap.get(id), headers, HttpStatus.OK);
        return new ResponseEntity<Company>(new Company(), headers, HttpStatus.NOT_FOUND);

    }
    @GetMapping("/v2/companies/{id}")
    public ResponseEntity<CompanyVersionTwo> getCompaniesVersionTwo(@PathVariable("id") String id) {

        System.out.println("In Service2");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json"));

        if(companiesVersionTwoMap.containsKey(id))
            return new ResponseEntity<CompanyVersionTwo>(companiesVersionTwoMap.get(id), headers, HttpStatus.OK);
        return new ResponseEntity<CompanyVersionTwo>(new CompanyVersionTwo(), headers, HttpStatus.NOT_FOUND);

    }
}
