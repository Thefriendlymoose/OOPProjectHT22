package model;

import model.pojos.OrderPojo;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    @BsonProperty("_objectId")
    private String objectId;


    private OrderPojo orderPojo;

    private ObjectId id;

    private List<Article> articles; //inventory ???

    private String articleDescription;

//    @BsonProperty(date)
    private LocalDateTime createdDate;

    private String status;

    private String user;

    private String site;

    private LocalDateTime lastEdited;
    
    private Order() {
        
    }
    

    enum OrderStatus {
        ACTIVE,
        CANCELED,
        FINISHED
    }
}
