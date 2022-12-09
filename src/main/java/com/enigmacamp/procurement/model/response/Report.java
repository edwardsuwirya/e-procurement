package com.enigmacamp.procurement.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private String materialCode;
    private Date transactionDate;

    private String vendorName;
    private String materialName;
    private String materialType;
    private Double materialPrice;
    private Double qty;
    private Double total;

    public String toCsv() {
        return materialCode + "," +
                transactionDate + "," +
                vendorName + "," +
                materialName + "," +
                materialType + "," +
                materialPrice + "," +
                qty + "," +
                total + "\n";
    }
}
