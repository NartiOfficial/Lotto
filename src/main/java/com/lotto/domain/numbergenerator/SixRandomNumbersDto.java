package com.lotto.domain.numbergenerator;

import lombok.Builder;

import java.util.Set;

@Builder
record SixRandomNumbersDto(Set<Integer> numbers) {
}
