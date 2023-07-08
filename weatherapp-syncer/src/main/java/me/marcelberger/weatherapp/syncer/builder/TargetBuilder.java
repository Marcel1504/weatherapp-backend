package me.marcelberger.weatherapp.syncer.builder;

import java.util.List;

public interface TargetBuilder<INPUT, TARGET> {
    boolean hasContent();

    void pushSourceEntities(List<INPUT> input);

    TARGET build(TARGET base);
}
