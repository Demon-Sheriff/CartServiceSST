package dev.anant.cartserviceapplicationsst.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Cart {
    Long id;
    Long userId;
    String date;
    int ItemCount;
    double TotalPrice;
    List<Product> products;
}
