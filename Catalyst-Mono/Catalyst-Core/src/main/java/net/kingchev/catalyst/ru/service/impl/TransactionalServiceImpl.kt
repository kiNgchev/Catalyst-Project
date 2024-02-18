package net.kingchev.catalyst.ru.service.impl

import net.kingchev.catalyst.ru.service.TransactionalService
import org.hibernate.StaleStateException
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionalServiceImpl : TransactionalService {
    @Transactional(propagation = Propagation.REQUIRED)
    override fun runInTransaction(action: Runnable) {
        return action.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun runInNewTransaction(action: Runnable) {
        return action.run();
    }

    @Retryable(StaleStateException::class, maxAttempts = 5, backoff = Backoff(delay = 500))
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun runWithLockRetry(action: Runnable) {
        return action.run();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun <T> runInTransaction(action: () -> T): T {
        return action.invoke();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun <T> runInNewTransaction(action: () -> T): T {
        return action.invoke();
    }

    @Retryable(StaleStateException::class, maxAttempts = 5, backoff = Backoff(delay = 500))
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun <T> runWithLockRetry(action: () -> T): T {
        return action.invoke();
    }
}