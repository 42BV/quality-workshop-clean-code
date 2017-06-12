package nl._42.qualityws.cleancode.controller;

import javax.validation.Valid;

import nl._42.qualityws.cleancode.model.Collector;
import nl._42.qualityws.cleancode.service.CollectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collectors")
public class CollectorController {

    @Autowired
    private CollectorService collectorService;
    
    @GetMapping
    public Page<Collector> list(@SortDefault("name") Pageable pageable) {
        return collectorService.list(pageable);
    }
    
    @PostMapping
    public Collector create(@Valid @RequestBody Collector collector) {
        return collectorService.create(collector);
    }
}
