package org.testzeug.parser;

/**
 * YAML-Parser interface in order to hide the parser implementation.
 *
 * @author Artun Subasi
 */
public interface YamlParser {

    /**
     * Parses a YAML-String as a {@link java.util.List} or {@link java.util.Map}.
     *
     * @param yamlString the YAML-String to parse
     * @return as a {@link java.util.List} or {@link java.util.Map} with contains Strings as keys.
     * The values of the map are lists, maps or simple types.
     */
    Object parse(final String yamlString);

}
