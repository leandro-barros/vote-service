package com.southsystem.voteservice.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class SessionRequestDto {

    private Integer sessionTimeInMinute;

}
