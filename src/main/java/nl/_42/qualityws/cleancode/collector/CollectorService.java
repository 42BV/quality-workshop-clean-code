package nl._42.qualityws.cleancode.collector;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class CollectorService {

    @Autowired
    private CollectorRepository collectorRepository;
    
    Collector create(Collector collector) {
        notNull(collector, "Collector must not be null.");
        isTrue(collector.isNew(), "Collector to be created must not exist.");
        return collectorRepository.save(collector);
    }
    
    Page<Collector> list(Pageable pageable) {
        return collectorRepository.findAll(pageable);
    }
}
