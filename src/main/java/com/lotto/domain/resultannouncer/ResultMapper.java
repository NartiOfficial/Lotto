package com.lotto.domain.resultannouncer;

import com.lotto.domain.resultannouncer.dto.ResponseDto;

public class ResultMapper {
    static ResponseDto mapToDto(ResultResponse resultResponse) {
        return ResponseDto.builder()
                .drawDate(resultResponse.drawDate())
                .hash(resultResponse.hash())
                .hitNumbers(resultResponse.hitNumbers())
                .numbers(resultResponse.numbers())
                .isWinner(resultResponse.isWinner())
                .build();
    }
}