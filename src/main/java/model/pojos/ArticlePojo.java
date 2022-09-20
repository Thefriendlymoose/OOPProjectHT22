package model.pojos;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class ArticlePojo {
    private ObjectId id;
    @BsonProperty(value = "article_number")
    private String articleNumber;
}
