package pl.walczak.jakub.mongodbmigrationspoc.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Booking;
import pl.walczak.jakub.mongodbmigrationspoc.repository.BookingRepository;
import pl.walczak.jakub.mongodbmigrationspoc.service.ExternalBookingSource;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ChangeUnit(order = "005", id = "init_bookings")
public class BookingInitChangeUnit {
    private final MongoTemplate mongoTemplate;
    private final ExternalBookingSource externalBookingSource;
    private final BookingRepository bookingRepository;

    public BookingInitChangeUnit(MongoTemplate mongoTemplate, ExternalBookingSource externalBookingSource, BookingRepository bookingRepository) {
        this.mongoTemplate = mongoTemplate;
        this.externalBookingSource = externalBookingSource;
        this.bookingRepository = bookingRepository;
    }

    @Execution
    public void initBookings() {
        log.info("Init bookings");
        List<String> bookingIdReferences = externalBookingSource.bookingReferences();
        List<Booking> bookings = bookingIdReferences.stream()
                .map(refId -> Booking.builder()
                        .bookingReference(refId)
                        .build())
                .collect(Collectors.toList());
        bookingRepository.saveAll(bookings);
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Booking.class);
    }
}
