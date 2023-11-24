package com.sarathexp.offlinewallet.util

abstract class BaseUseCase<in Params, out Type> {

    protected abstract suspend fun run(params: Params): Type
    suspend operator fun invoke(params: Params): Type = run(params)

}
