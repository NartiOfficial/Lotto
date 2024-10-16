package com.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.lotto.BaseIntegrationTest;
import com.lotto.domain.numbergenerator.WinningNumbersGeneratorFacade;
import com.lotto.domain.numbergenerator.WinningNumbersNotFoundException;
import com.lotto.domain.numberreceiver.dto.NumberReceiverResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    public WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Test
    public void should_user_win_and_system_should_generate_winners() throws Exception {
    //  Step 1: External service returns 6 random numbers (1, 2, 3, 4, 5, 6)
        // Given
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                """.trim()
                        )));
        // When
        // Then


    //  Step 2: System fetched winning numbers for draw date: 19.11.2022 12:00
        // Given
        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0, 0);

        // When && Then
        await()
                .atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until( () -> {
                        try{
                            return !winningNumbersGeneratorFacade.retrieveWinningNumberByDate(drawDate).getWinningNumbers().isEmpty();
                        } catch (WinningNumbersNotFoundException e){
                            return false;
                        }
                    }
                );


    //  Step 3: User made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and system returned OK(200) with message: “success” and Ticket (DrawDate:19.11.2022 12:00 (Saturday), TicketId: sampleTicketId)
        // Given
        // When
        ResultActions performPostInputNumbers = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "inputNumbers": [1,2,30,50,55,61]
                        }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        MvcResult mvcResult = performPostInputNumbers.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        NumberReceiverResponseDto numberReceiverResponseDto = objectMapper.readValue(json, NumberReceiverResponseDto.class);
        assertAll(
                () -> assertThat(numberReceiverResponseDto.ticketDto().drawDate()).isEqualTo(drawDate),
                () -> assertThat(numberReceiverResponseDto.ticketDto().hash()).isNotNull(),
                () -> assertThat(numberReceiverResponseDto.message()).isEqualTo("SUCCESS")
        );

    /// Step 4: user made GET /results/notExistingId and system returned 404(NOT_FOUND) and body with (message: Not found for id: notExistingId and status NOT_FOUND)
        // Given
        // When
        ResultActions performGetResultsWithNotExistingId = mockMvc.perform(get("/results/notExistingId"));
        // Then
        performGetResultsWithNotExistingId.andExpect(status().isNotFound())
                .andExpect(content().json(
                        """
                        {
                        "message": "Not found for id: notExistingId",
                        "httpStatus": "NOT_FOUND"
                        }
                        """.trim()
                ));


    //  Step 5: 3 days and 1 minute passed, and it is 1 minute after the draw date (19.11.2022 12:01)


    //  Step 6: System generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00, and saved it with 6 hits
    //  Step 7: 3 hours passed, and it is 1 minute after announcement time (19.11.2022 15:01)
    //  Step 8: User made GET /results/sampleTicketId and system returned 200 (OK)
    }
}
