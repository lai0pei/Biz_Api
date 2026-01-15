package io.lab.core.modules.admin.dto.request;

import io.lab.core.common.PageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.*;

@Schema(description = "Request DTO for Admin list retrieval with pagination and filters")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminSearchReq extends PageReq {

        @Schema(example = "null")
        @Nullable
        private String username;

        @Schema(example = "null")
        @Nullable
        private Boolean enabled;

        @Schema(example = "null")
        @Nullable
        private String lastLoginTime;

}

