package me.marcelberger.weatherapp.api.mapper;

import me.marcelberger.weatherapp.api.dto.PageData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Mapper<FROM, TO> {
    TO map(FROM object);

    default PageData<TO> mapPage(Page<FROM> page) {
        return PageData.<TO>builder()
                .list(page.getContent().stream().map(this::map).toList())
                .total(page.getTotalElements())
                .hasNext(page.hasNext())
                .build();
    }

    default PageData<TO> mapList(List<FROM> list) {
        return PageData.<TO>builder()
                .list(list.stream().map(this::map).toList())
                .total((long) list.size())
                .hasNext(false)
                .build();
    }
}
