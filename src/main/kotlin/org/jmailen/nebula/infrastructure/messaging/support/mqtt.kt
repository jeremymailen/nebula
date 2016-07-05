package org.jmailen.nebula.infrastructure.messaging.support

import org.fusesource.hawtbuf.Buffer

/**
 * Extend MQTT library to easily read tagged ascii: {"jsonkey": "value"} payloads.
 */
fun Buffer.jsonData(): ByteArray {
    val strData = String(this.data)
    val markerStart = strData.indexOf('{')
    if (markerStart > -1) {
        return strData.substring(markerStart).toByteArray()
    } else {
        return this.data
    }
}
