package io.lab.core.modules.admin;

import io.lab.core.modules.admin.dto.AdminDto;
import io.lab.core.modules.admin.dto.projection.AdminDetailDto;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AdminMpp {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "lastLoginTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    AdminMdl toModel(AdminCreateReq adminCreateReq);

    @Mapping(target = "roleId", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    AdminDto toAdmin(AdminMdl adminMdl);

    AdminDetailDto toAdminDetail(AdminMdl adminMdl);
}