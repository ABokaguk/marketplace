package com.marketplace.accountservice.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * ValidationFieldInfo.
 *
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Объект для представления ошибки")
public class ValidationFieldInfo {

    @NotBlank
    @Schema(example = "accountNumber", description = "Имя поля")
    private String fieldName;

    @NotBlank
    @Schema(example = "Неверный номер счета(длина не соответствует требованиям)", description = "Сообщение об ошибки")
    private String message;

    @NotBlank
    @Schema(example = "408178L0099910004312", description = "Переданное не верное значение")
    private String rejectValue;
}
