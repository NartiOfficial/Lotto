package com.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.lotto.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_user_win_and_system_should_generate_winners() {
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
    //  Step 3: User made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and system returned OK(200) with message: “success” and Ticket (DrawDate:19.11.2022 12:00 (Saturday), TicketId: sampleTicketId)
    //  Step 4: 3 days and 1 minute passed, and it is 1 minute after the draw date (19.11.2022 12:01)
    //  Step 5: System generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00, and saved it with 6 hits
    //  Step 6: 3 hours passed, and it is 1 minute after announcement time (19.11.2022 15:01)
    //  Step 7: User made GET /results/sampleTicketId and system returned 200 (OK)
    }
}
