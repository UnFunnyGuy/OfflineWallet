package com.sarathexp.offlinewallet.app.base

import kotlinx.coroutines.flow.Flow

/**
 * Base use case interface.
 *
 * @param Params
 * @param Result
 */
interface BaseUseCase<in Params, out Result, > {

    /**
     * Perform an operation with no input parameters.
     * Will throw an exception by default, if not implemented but invoked.
     *
     * @return [Result]
     */
    suspend fun perform(): Result = throw NotImplementedError()

    /**
     * Perform an operation.
     *  Will throw an exception by default, if not implemented but invoked.
     *
     * @param params
     * @return [Result?]
     */
    suspend fun perform(params: Params): Result? = throw NotImplementedError()

    /**
     * Perform an operation with a nullable input parameters.
     *  Will throw an exception by default, if not implemented but invoked.
     *
     * @return [Flow]<[Result]>
     */
    fun performStreaming(params: Params? = null): Flow<Result> = throw NotImplementedError()


}
