package com.habuma.spitter.mvc;

import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class MockBindingResultMaker {

  public static BindingResult createMockBindingResult(boolean hasErrors) {
    BindingResult result = mock(BindingResult.class);
    when(result.hasErrors()).thenReturn(hasErrors);
    return result;
  }
}
