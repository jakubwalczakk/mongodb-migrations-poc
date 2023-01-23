package pl.walczak.jakub.mongodbmigrationspoc.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExternalBookingSource {

    public List<String> bookingReferences() {
        return IntStream.rangeClosed(1, 1000)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }

}
