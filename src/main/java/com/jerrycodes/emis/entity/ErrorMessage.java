package com.jerrycodes.emis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private Timestamp timestamp;
    private String status;
    private String message;
    private String error;
}
