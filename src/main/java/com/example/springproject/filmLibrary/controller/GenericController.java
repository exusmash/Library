package com.example.springproject.filmLibrary.controller;

import com.example.springproject.filmLibrary.model.GenericModel;
import com.example.springproject.filmLibrary.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public abstract class GenericController<T extends GenericModel> {
    private final GenericRepository<T> genericRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericController(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Operation(description = "Получить запись по ID", method = "getOneByID")
    @RequestMapping(value = "/getOneById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getOneById(@RequestParam(value = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Данные по переданному ID не найдены!")));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(genericRepository.findAll());
    }

    @Operation(description = "Создать запись", method = "add")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody T newEntity) {
        log.info(newEntity.toString());
        newEntity.setCreatedWhen(LocalDateTime.now());
        genericRepository.save(newEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntity);
    }


    @Operation(description = "Обновить запись", method = "update")
    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody T updatedEntity,
                                    @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        genericRepository.save(updatedEntity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedEntity);
    }

    //localhost:8080/authors/delete?id=1 - @RequestParam
    //localhost:8080/authors/delete/1 - @PathVariable
    @Operation(description = "Удалить запись", method = "delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        genericRepository.deleteById(id);
    }

}
