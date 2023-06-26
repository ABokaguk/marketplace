package com.marketplace.accountservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * ExampleDTO.
 *
 */
@Data
@Schema(description = "Объект для представления")
public class ExampleDto {

    @Schema(example = "123e4567-e89b-12d3-a456-426614174000", description = "Уникальный идентификатор")
    private UUID id;

    @NotEmpty(message = "account.dto.validate.accountNumber.isEmpty")
    @Size(min = 20, max = 20, message = "account.dto.validate.accountNumber.size")
    @Pattern(regexp = "^\\d+$", message = "account.dto.validate.accountNumber.pattern")
    @Schema(example = "40817810099910004312", description = "Номер счета")
    private String accountNumber;
}
