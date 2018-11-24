package fun.connor.cafe.domain;

import fun.connor.cafe.domain.cafe.Cafe;
import xyz.morphia.annotations.*;

import java.util.List;
import java.util.UUID;

@Entity
@Indexes(
        @Index(value = "subjectId", fields = @Field("subjectId"))
)
public class Account {

    @Id
    private UUID uuid;

    @Embedded
    private List<CafeVisit> cafeVisits;


    private String subjectId;

    public Account() {}

    public Account(UUID uuid, List<CafeVisit> cafeVisits, String subjectId) {
        this.uuid = uuid;
        this.cafeVisits = cafeVisits;
        this.subjectId = subjectId;
    }



}
