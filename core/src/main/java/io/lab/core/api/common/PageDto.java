package io.lab.core.api.common;

import java.util.List;

public record PageDto<T>(Long total, List<T> data) {

    public static <T> PageDto<T> list(Long total, List<T> data) {
        return new PageDto<T>(total, data);
    }
}
