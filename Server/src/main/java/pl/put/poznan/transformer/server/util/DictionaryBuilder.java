package pl.put.poznan.transformer.server.util;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A builder for immutable dictionaries. A dictionary built by this class is an immutable implementation of {@link Map}.
 *
 * @param <K> the type of keys in the dictionary
 * @param <V> the type of values in the dictionary
 */
public class DictionaryBuilder<K, V> {

    private final Map<K, V> map;

    /**
     * Creates a new dictionary builder. The initial capacity of the dictionary is 16.
     */
    public DictionaryBuilder() {
        map = new LinkedHashMap<>();
    }

    /**
     * Creates a new dictionary builder with the specified initial capacity. Specifying
     * the initial capacity is recommended when the approximate size of the dictionary
     * is known. This will prevent unnecessary reallocations of the underlying data structure.
     *
     * @param initialCapacity the initial capacity of the dictionary
     */
    public DictionaryBuilder(int initialCapacity) {
        map = new LinkedHashMap<>(initialCapacity);
    }

    /**
     * Adds a key-value pair to the dictionary.
     *
     * @param key   the entry's key
     * @param value the entry's value
     * @return this builder
     */
    public DictionaryBuilder<K, V> with(K key, V value) {
        map.put(key, value);
        return this;
    }

    /**
     * Finalizes the dictionary and returns it. The resulting dictionary is immutable.
     *
     * @return the dictionary
     */
    public @NotNull Map<K, V> build() {
        return Collections.unmodifiableMap(map);
    }
}
