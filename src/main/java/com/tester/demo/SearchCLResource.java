package com.tester.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchclient.clientwrapper.domain.dto.SolrSearchResponseDTO;
import com.searchclient.clientwrapper.domain.port.api.SolrSearchRecordsServicePort;


@RestController
@RequestMapping("/test-api")
public class SearchCLResource {
    private final Logger logger = LoggerFactory.getLogger(SearchCLResource.class);
    @Autowired
    SolrSearchRecordsServicePort solrSearchRecordsServicePort;

    // http://localhost:<server.port>/saas-microservice-tester/test/get-all

    @GetMapping(value = "/{clientId}/{tableName}")
    public ResponseEntity<SolrSearchResponseDTO> searchRecords(
    		@PathVariable int clientId, 
    		@PathVariable String tableName, 
            @RequestParam(defaultValue = "*") String queryField, @RequestParam(defaultValue = "*") String searchTerm, 
            @RequestParam(defaultValue = "0") String startRecord,
            @RequestParam(defaultValue = "5") String pageSize, 
            @RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "asc") String order) {
        logger.debug("Egress CL tester REST call for records-search in the given table");
        
        SolrSearchResponseDTO responseDTO = solrSearchRecordsServicePort.setUpSelectQueryAdvancedSearch(
        		tableName, queryField, searchTerm, startRecord, pageSize, orderBy, order, null);
        
        
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(responseDTO);
    }
    
}
