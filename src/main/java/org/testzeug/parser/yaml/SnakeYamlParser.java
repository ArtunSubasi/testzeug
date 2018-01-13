package org.testzeug.parser.yaml;

import org.testzeug.parser.YamlParser;
import org.yaml.snakeyaml.Yaml;

/**
 * {@link YamlParser} implementation using SnakeYaml.
 * 
 * @author Artun Subasi
 */
public class SnakeYamlParser implements YamlParser {
    
    @Override
    public Object parse(final String yamlString) {
        return new Yaml().load(yamlString);
    }
    
}
