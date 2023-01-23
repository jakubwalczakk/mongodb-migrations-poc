package pl.walczak.jakub.mongodbmigrationspoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

}
