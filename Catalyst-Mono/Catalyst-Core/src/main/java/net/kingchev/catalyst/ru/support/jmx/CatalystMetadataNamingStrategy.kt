package net.kingchev.catalyst.ru.support.jmx

import org.springframework.jmx.export.naming.MetadataNamingStrategy
import javax.management.MalformedObjectNameException
import javax.management.ObjectName

class CatalystMetadataNamingStrategy : MetadataNamingStrategy() {
    @Throws(MalformedObjectNameException::class)
    override fun getObjectName(managedBean: Any, beanKey: String?): ObjectName {
        var beanKey = beanKey
        if (beanKey == null) {
            beanKey = managedBean.javaClass.name
        }
        var objectName = super.getObjectName(managedBean, beanKey)
        if (managedBean is JmxNamedResource) {
            objectName = buildObjectName(managedBean as JmxNamedResource, objectName.domain)
        }
        return objectName
    }

    /**
     * Construct our object name by calling the methods in [JmxNamedResource].
     */
    @Throws(MalformedObjectNameException::class)
    private fun buildObjectName(namedObject: JmxNamedResource, domainName: String): ObjectName {
        val typeNames: Array<String> = namedObject.jmxPath
            ?: throw MalformedObjectNameException("getJmxPath() is returning null for object $namedObject")

        val nameBuilder = StringBuilder()
        nameBuilder.append(domainName)
        nameBuilder.append(':')

        /*
     * Ok. This is a HACK. It seems like something in the JMX mbean naming process actually sorts the names
     * lexicographically. The stuff before the '=' character seems to be ignored anyway but it does look ugly.
     */
        var needComma = false
        for ((typeNameC, typeName) in typeNames.withIndex()) {
            if (needComma) {
                nameBuilder.append(',')
            }
            // this will come out as 00=partition
            nameBuilder.append(String.format("%02d", typeNameC))
            nameBuilder.append('=')
            nameBuilder.append(typeName)
            needComma = true
        }
        if (needComma) {
            nameBuilder.append(',')
        }
        nameBuilder.append("name=")
        nameBuilder.append(namedObject.getJmxName())
        return ObjectName.getInstance(nameBuilder.toString())
    }
}