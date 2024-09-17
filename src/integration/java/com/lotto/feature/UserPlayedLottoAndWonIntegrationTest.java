package com.lotto.feature;

import com.lotto.BaseIntegrationTest;
import org.junit.jupiter.api.Test;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_user_win_and_system_should_generate_winners() {
//        Step 1: User made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and system returned OK(200) with message: “success” and Ticket (DrawDate:19.11.2022 12:00 (Saturday), TicketId: sampleTicketId)
//        Step 2: System generated winning numbers for draw date: 19.11.2022 12:00
//        Step 3: 3 days and 1 minute passed, and it is 1 minute after the draw date (19.11.2022 12:01)
//        Step 4: System generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00, and saved it with 6 hits
//        Step 5: 3 hours passed, and it is 1 minute after announcement time (19.11.2022 15:01)
//        Step 6: User made GET /results/sampleTicketId and system returned 200 (OK)
    }
}
