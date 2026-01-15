package io.lab.core.modules.role.dto.request;

import io.lab.core.common.PageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request DTO for Role list retrieval with pagination and filters")
@Builder
@Getter
@Setter
public class RoleSearchReq extends PageReq  {

        @Schema(example = "null")
        @Nullable
        private String roleName;

        @Schema(example = "null")
        @Nullable
        private Boolean enabled;
}