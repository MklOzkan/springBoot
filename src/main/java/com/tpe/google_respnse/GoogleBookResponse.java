package com.tpe.google_respnse;


import lombok.Data;

import java.util.List;

import java.util.List;

//to deserialize json response
@Data
public class GoogleBookResponse {
    private List<GoogleBookItem> items;
}
