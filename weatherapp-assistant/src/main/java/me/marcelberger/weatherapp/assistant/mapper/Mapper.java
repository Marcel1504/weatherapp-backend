package me.marcelberger.weatherapp.assistant.mapper;

import java.util.ArrayList;
import java.util.List;

public interface Mapper<FROM, TO> {
    TO map(FROM object);

    default List<TO> map(List<FROM> list) {
        return list != null ? list.stream().map(this::map).toList() : new ArrayList<>();
    }
}
