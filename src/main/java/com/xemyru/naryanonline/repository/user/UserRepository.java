package com.xemyru.naryanonline.repository.user;

import com.xemyru.naryanonline.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.xemyru.naryanonline.tables.Users.USERS;

@Repository
public class UserRepository {

    private final DSLContext dslContext;

    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public UsersRecord save(UsersRecord usersRecord) {
        dslContext.insertInto(USERS)
                .set(usersRecord)
                .execute();

        Integer generatedId = dslContext.select(USERS.ID)
                .from(USERS)
                .where(USERS.EMAIL.eq(usersRecord.getEmail()))
                .fetchOneInto(Integer.class);

        if (generatedId == null) {
            throw new IllegalStateException("Failed to retrieve generated ID for user with email: " + usersRecord.getEmail());
        }

        usersRecord.setId(generatedId);
        return usersRecord;
    }

    public boolean existsByEmail(String email) {
        return dslContext.fetchExists(
                dslContext.selectFrom(USERS)
                        .where(USERS.EMAIL.eq(email))
        );
    }

    public UsersRecord findByEmail(String email) {
        return dslContext.selectFrom(USERS)
                .where(USERS.EMAIL.eq(email))
                .fetchOne();
    }

    public Optional<UsersRecord> findById(int userId) {
        return Optional.ofNullable(dslContext.selectFrom(USERS)
                .where(USERS.ID.eq(userId))
                .fetchOne());
    }

    public boolean existsById(int userId) {
        return dslContext.fetchExists(dslContext.selectOne()
                .from(USERS)
                .where(USERS.ID.eq(userId)));
    }
}
