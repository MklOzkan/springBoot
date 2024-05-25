package com.tpe.google_respnse;

import lombok.Data;

import java.util.List;

@Data
public class VolumeInfo {
    private String title;
    private String publishedDate;
    private List<String> authors;
    private List<IndustryIdentifier> industryIdentifiers;

}