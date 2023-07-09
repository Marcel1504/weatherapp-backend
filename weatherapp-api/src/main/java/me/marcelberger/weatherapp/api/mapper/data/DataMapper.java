package me.marcelberger.weatherapp.api.mapper.data;

import me.marcelberger.weatherapp.api.dto.response.data.DataResponseDto;
import me.marcelberger.weatherapp.api.mapper.Mapper;
import me.marcelberger.weatherapp.core.entity.data.DataEntity;

public interface DataMapper<SOURCE extends DataEntity, TARGET extends DataResponseDto> extends Mapper<SOURCE, TARGET> {
}
