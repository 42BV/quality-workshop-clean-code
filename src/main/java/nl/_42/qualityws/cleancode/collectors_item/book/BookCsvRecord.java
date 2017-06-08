package nl._42.qualityws.cleancode.collectors_item.book;

import org.csveed.annotations.CsvCell;
import org.csveed.annotations.CsvFile;
import org.csveed.bean.ColumnNameMapper;

@CsvFile(separator = ',', mappingStrategy = ColumnNameMapper.class)
public class BookCsvRecord {

    @CsvCell
    private String collector;
    @CsvCell
    private String website;
    @CsvCell
    private String title;
    @CsvCell
    private String author;

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
