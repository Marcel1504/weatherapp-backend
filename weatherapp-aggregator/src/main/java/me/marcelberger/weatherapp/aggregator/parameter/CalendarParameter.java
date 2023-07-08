package me.marcelberger.weatherapp.aggregator.parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Getter
public final class CalendarParameter {

    private final Map<Item, String> values;

    private CalendarParameter(LocalDateTime timestamp, Set<Item> items) {
        values = new HashMap<>();
        items.forEach(item -> values.put(item, timestamp.format(DateTimeFormatter.ofPattern(item.getPattern()))));
    }

    public static CalendarParameter from(LocalDateTime timestamp, Set<Item> items) {
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items cannot be null or empty");
        }
        return new CalendarParameter(timestamp, items);
    }

    public String getValue(Item item) {
        if (values.containsKey(item)) {
            return values.get(item);
        }
        throw new NoSuchElementException(String.format("No value found for item %s", item.toString()));
    }

    @Override
    public String toString() {
        return values.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CalendarParameter
                && ((CalendarParameter) obj).values.equals(this.values);
    }

    @Getter
    @AllArgsConstructor
    public enum Item {
        HOUR("HH"),
        DAY("yyyy-MM-dd"),
        MONTH("MM"),
        YEAR("yyyy");
        private final String pattern;
    }
}
