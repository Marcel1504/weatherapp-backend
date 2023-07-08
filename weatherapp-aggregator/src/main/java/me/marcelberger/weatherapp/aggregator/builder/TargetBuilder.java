package me.marcelberger.weatherapp.aggregator.builder;

import java.util.List;

public interface TargetBuilder<INPUT, TARGET> {
    boolean hasContent();

    void pushSourceEntities(List<INPUT> input);

    TARGET build(TARGET base);
}
