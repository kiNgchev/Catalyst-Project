package net.kingchev.catalyst.ru.support.jmx;

public interface JmxNamedResource {

    String getJmxName();

    default String[] getJmxPath() {
        return new String[]{};
    }
}
