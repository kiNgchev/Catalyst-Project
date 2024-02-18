package net.kingchev.catalyst.ru.service

interface TransactionalService {
    fun runInTransaction(action: Runnable)

    fun runInNewTransaction(action: Runnable)

    fun runWithLockRetry(action: Runnable)

    fun <T> runInTransaction(action: () -> T): T

    fun <T> runInNewTransaction(action: () -> T): T

    fun <T> runWithLockRetry(action: () -> T): T
}