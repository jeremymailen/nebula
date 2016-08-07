package org.jmailen.nebula.infrastructure.messaging.support

import java.util.*

const val WILDCARD_SINGLE_LEVEL = "+"
const val WILDCARD_MULTI_LEVEL = "#"

class TopicTree() {
    private val root = TopicTreeNode()

    fun subscribe(topicFilter: String, notifier: (ByteArray) -> Unit): Boolean {
        if (topicFilter.isEmpty()) {
            throw IllegalArgumentException("topicFilter must not be an empty string")
        }
        return root.subscribe(elements(topicFilter), notifier)
    }

    fun deliver(topic: String, message: ByteArray) {
        root.deliver(elements(topic), message)
    }

    private fun elements(topic: String) = LinkedList(topic.split('/'))
}

private class TopicTreeNode() {
    val subscribers = mutableListOf<(ByteArray) -> Unit>()
    val subtreeSubscribers = mutableListOf<(ByteArray) -> Unit>()
    val children = mutableMapOf<String, TopicTreeNode>()

    fun subscribe(topicElements: MutableList<String>, notifier: (ByteArray) -> Unit): Boolean {
        if (topicElements.isEmpty()) {
            subscribers.add(notifier)
            return subscribers.size == 1

        } else {
            val prefix = topicElements.pop()
            when (prefix) {
                WILDCARD_MULTI_LEVEL -> {
                    subtreeSubscribers.add(notifier)
                    return subtreeSubscribers.size == 1
                }
                else -> {
                    if (children[prefix] == null) {
                        children[prefix] = TopicTreeNode()
                    }
                    return children[prefix]!!.subscribe(topicElements, notifier)
                }
            }
        }
    }

    fun deliver(topicElements: MutableList<String>, message: ByteArray) {
        if (topicElements.isEmpty()) {
            subscribers.forEach { it(message) }
        } else {
            subtreeSubscribers.forEach { it(message) }

            val prefix = topicElements.pop()
            children[WILDCARD_SINGLE_LEVEL]?.deliver(topicElements, message)
            children[prefix]?.deliver(topicElements, message)
        }
    }
}

fun <T> MutableList<T>.pop() = removeAt(0)
