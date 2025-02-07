package com.xemyru.naryanonline.repository.house;

import com.xemyru.naryanonline.tables.records.HousesRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.xemyru.naryanonline.tables.Houses.HOUSES;

@Repository
@RequiredArgsConstructor
public class HouseRepository {

    private final DSLContext dslContext;

    public List<HousesRecord> getAllHouse(){
        return dslContext.select()
               .from(HOUSES)
                .fetchInto(HousesRecord.class);
    }
    public Optional<HousesRecord> findById(int id){
        return Optional.ofNullable(dslContext.select()
                .from(HOUSES)
                .where(HOUSES.ID.eq(id))
                .fetchOneInto(HousesRecord.class));
    }

    public List<HousesRecord> findAllByCategoryId(Long categoryId) {
        return dslContext.selectFrom(HOUSES)
                .where(categoryId == null ? org.jooq.impl.DSL.trueCondition() : HOUSES.CATEGORY_ID.eq(Math.toIntExact(categoryId)))
                .fetch();
    }
}