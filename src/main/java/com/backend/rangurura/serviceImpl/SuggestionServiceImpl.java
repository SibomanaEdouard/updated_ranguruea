package com.backend.rangurura.serviceImpl;

import com.backend.rangurura.Services.SuggestionService;
import com.backend.rangurura.dtos.SuggestionDto;
import com.backend.rangurura.entities.Suggestions;
import com.backend.rangurura.exceptions.BadRequestException;
import com.backend.rangurura.repositories.SuggestionRepository;
import com.backend.rangurura.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@RequiredArgsConstructor
@Service
public class SuggestionServiceImpl implements SuggestionService {
    private final SuggestionRepository suggestionRepository;

    @Override
    public ApiResponse<Object> PostSuggestion(@Valid SuggestionDto dto) throws Exception {
        try {
            // Convert DTO to entity
            Suggestions suggestionEntity = convertDtoToEntity(dto);

            // Save the suggestion to the repository
            Suggestions savedSuggestion = suggestionRepository.save(suggestionEntity);

            // You can return the saved suggestion or a success message
            return new ApiResponse<>("Suggestion saved successfully", savedSuggestion);

        } catch (Exception e) {
            // Log the exception or handle it in a way that provides meaningful feedback
            throw new Exception("Failed to process suggestion", e);
        }
    }

    private Suggestions convertDtoToEntity(SuggestionDto dto) {

        // Implement logic to convert DTO to Entity
        Suggestions suggestions = new Suggestions();

        suggestions.setUrwego(dto.getUrwego());
        suggestions.setPhoneNumber(dto.getPhoneNumber());
        suggestions.setIgitekerezo(dto.getIgitekerezo());
        suggestions.setCategory(dto.getCategory());

        return suggestions;
    }
}
