package com.lotto.apivalidatonerror;

import com.lotto.BaseIntegrationTest;
import com.lotto.infrastructure.apivalidation.ApiValidationErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_has_empty_input_numbers() throws Exception {
        // Given
        // When
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "inputNumbers": []
                        }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder(
                "inputNumbers must not be empty");
    }

    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_is_does_not_have_input_numbers() throws Exception {
        // Given
        // When
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {}
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder(
                "inputNumbers must not be null",
                "inputNumbers must not be empty");
    }
}
