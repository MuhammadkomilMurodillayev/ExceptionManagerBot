package com.example.errormanager.api.validation;

import com.example.errormanager.api.domain.Developer;
import com.example.errormanager.api.dto.developer.DeveloperUpdateDTO;
import com.example.errormanager.api.repository.DeveloperRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Muhammadkomil Murodillayev, ср 11:42. 8/10/22
 */

@Component
public class DeveloperValidation implements BaseValidation {

    private final DeveloperRepository developerRepository;

    public DeveloperValidation(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public void checkUpdate(DeveloperUpdateDTO dto) {
        if (dto.getChatId() != null) {
            Optional<Developer> developerOptional = developerRepository.findByChatIdAndDeletedFalse(dto.getChatId());
            if (developerOptional.isPresent()) {
                Developer developer = developerOptional.get();
                developer.setChatId(null);
                developerRepository.save(developer);
            }
        }
    }
}
