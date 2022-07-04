package com.vesmer.familytestbackend.controller;

import com.vesmer.familytestbackend.dto.RelativesDto;
import com.vesmer.familytestbackend.service.RelativesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/relatives")
public class RelativesController {
    private final RelativesService relativesService;

    @Autowired
    public RelativesController(RelativesService relativesService) {
        this.relativesService = relativesService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<RelativesDto> getRelativesByPerson(@PathVariable Long personId){
        return ResponseEntity.status(OK).body(relativesService.getRelativesByPerson(personId));
    }


    @GetMapping("/{personId}/parent/{parentId}")
    public ResponseEntity<RelativesDto> addPersonAsParent (@PathVariable Long personId,
                                                           @PathVariable Long parentId){
        return ResponseEntity.status(OK).body(relativesService.addPersonAsParent(personId, parentId));
    }
}
