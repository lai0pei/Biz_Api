package io.lab.core.modules.role;

import io.lab.core.modules.role.dto.request.RoleCreateReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RoleMpp {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permMdl" , ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    RoleMdl toRoleMdl(RoleCreateReq reqC);

}

