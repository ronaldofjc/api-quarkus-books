package br.com.books.resource.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
  @NotBlank(message = "Field title is mandatory")
  private String title;
  @NotBlank(message = "Field author is mandatory")
  private String author;
  @NotNull(message = "Field pages is mandatory")
  private Integer pages;
}
