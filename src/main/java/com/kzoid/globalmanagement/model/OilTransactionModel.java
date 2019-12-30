package com.kzoid.globalmanagement.model;

import com.kzoid.globalmanagement.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class OilTransactionModel {

    private Date timestamp;
    private OilModel oil;
    private Integer quantity;
    private TransactionType type;
    private BigDecimal price;

}
