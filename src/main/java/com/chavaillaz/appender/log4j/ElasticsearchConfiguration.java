package com.chavaillaz.appender.log4j;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@Setter
public class ElasticsearchConfiguration {

    private String user;
    private String password;
    private String url;
    private String index;
    private String indexSuffix;
    private DateTimeFormatter indexSuffixFormatter;
    private int batchSize;

    public void setIndexSuffix(String indexSuffix) {
        this.indexSuffix = indexSuffix;
        this.indexSuffixFormatter = DateTimeFormatter.ofPattern(indexSuffix);
    }

    /**
     * Generates the index name using the suffix if present.
     *
     * @param utcDateTime The date and time of the event in the format
     *                    {@link java.time.format.DateTimeFormatter#ISO_OFFSET_DATE_TIME}
     *                    or {@code null} to avoid using the prefix
     * @return The index name calculated
     */
    public String generateIndexName(String utcDateTime) {
        if (utcDateTime != null && indexSuffixFormatter != null) {
            OffsetDateTime odt = OffsetDateTime.parse(utcDateTime);
            return getIndex() + odt.format(indexSuffixFormatter);
        } else {
            return getIndex();
        }
    }

}
