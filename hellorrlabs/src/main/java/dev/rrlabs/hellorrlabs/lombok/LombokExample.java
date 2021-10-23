package dev.rrlabs.hellorrlabs.lombok;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class LombokExample {
    @NonNull
    private String name;
    private String lastname;
}
