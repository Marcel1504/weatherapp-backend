package me.marcelberger.weatherapp.core.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageData<DATA> {
    private Long total;
    private Boolean hasNext;
    private List<DATA> list;
}
