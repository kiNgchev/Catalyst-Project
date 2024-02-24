package net.kingchev.catalyst.ru.config;

import net.kingchev.catalyst.ru.support.jmx.CatalystMetadataNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;

@Configuration
public class MBeanConfiguration {
    @Bean
    public AnnotationJmxAttributeSource annotationJmxAttributeSource() {
        return new AnnotationJmxAttributeSource();
    }

    @Bean
    public MetadataMBeanInfoAssembler infoAssembler() {
        MetadataMBeanInfoAssembler assembler = new MetadataMBeanInfoAssembler();
        assembler.setAttributeSource(annotationJmxAttributeSource());
        return assembler;
    }

    @Bean
    public CatalystMetadataNamingStrategy namingStrategy() {
        CatalystMetadataNamingStrategy strategy = new CatalystMetadataNamingStrategy();
        strategy.setAttributeSource(annotationJmxAttributeSource());
        return strategy;
    }

    @Bean
    @Lazy(false)
    public MBeanExporter mBeanExporter() {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setAutodetect(true);
        exporter.setNamingStrategy(namingStrategy());
        exporter.setAssembler(infoAssembler());
        exporter.setEnsureUniqueRuntimeObjectNames(false);
        return exporter;
    }
}
