package nl._42.qualityws.cleancode.collector;

import static io.beanmapper.spring.PageableMapper.map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.beanmapper.BeanMapper;
import io.beanmapper.spring.web.MergedForm;

@RestController
@RequestMapping("collectors")
public class CollectorController {

    @Autowired
    private BeanMapper beanMapper;
    @Autowired
    private CollectorService collectorService;
    
    @RequestMapping
    public Page<CollectorResult> list(@SortDefault("name") Pageable pageable) {
        return map(collectorService.list(pageable), CollectorResult.class, beanMapper);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public CollectorResult create(@Valid @MergedForm(value = CollectorForm.class) Collector collector) {
        return beanMapper.map(collectorService.create(collector), CollectorResult.class);
    }
}
