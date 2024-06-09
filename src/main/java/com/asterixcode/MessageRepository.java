package com.asterixcode;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.List;
import java.util.UUID;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ListCrudRepository<Message, UUID> {

  String UPGRADE_SKIPLOCKED = "-2";

  /**
   * TIP:
   * This configuration solves 2 issues:
   * 1) OutOfMemoryError: we can limit the results of query methods by using the first or top keywords
   * or Pageable parameter:
   * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.limit-query-result
   * 2) Race Condition: using SKIP LOCKED here allows this feature to work in clustered environments;
   * NOTES:
   * 1) AvailableSettings.JAKARTA_LOCK_TIMEOUT is equal to SpecHints.HINT_SPEC_LOCK_TIMEOUT
   * 2) Using native query: @Query(value = "SELECT * FROM message LIMIT 10 FOR UPDATE SKIP LOCKED", nativeQuery = true)
   * 3) value = LockOptions.SKIP_LOCKED + "" is deprecated.
   * 4) The value of UPGRADE_SKIPLOCKED is "-2" which is the same as LockOptions.SKIP_LOCKED. -2 is the value of SKIP_LOCKED.
   * Check https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/chapters/locking/Locking.html#locking-LockMode
   * PESSIMISTIC_WRITE with a jakarta.persistence.lock.timeout setting of -2:
   * The lock acquisition request skips the already locked rows. It uses a SELECT … FOR UPDATE SKIP LOCKED in Oracle and PostgreSQL 9.5,
   * or SELECT … with (rowlock, updlock, readpast) in SQL Server.
   */
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @QueryHints({
    @QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = UPGRADE_SKIPLOCKED)
  })
  public List<Message> findTop5ByOrderByCreatedAtAsc();
}
