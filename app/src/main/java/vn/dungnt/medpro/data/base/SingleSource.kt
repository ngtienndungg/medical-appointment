package vn.dungnt.medpro.data.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.greenrobot.eventbus.EventBus
import vn.dungnt.medpro.R
import vn.dungnt.medpro.application
import vn.dungnt.medpro.data.models.EventType
import vn.dungnt.medpro.data.models.MessageEvent
import vn.dungnt.medpro.utils.Utils.getString
import vn.dungnt.medpro.utils.Utils.isNetworkAvailable
import vn.dungnt.medpro.utils.is401UnauthorizedError

fun <M> resultFlow(
    networkCall: suspend () -> NetworkResult<M>
): Flow<NetworkResult<M>> =
    flow {
        emit(NetworkResult.Loading())
        processResultFlow(networkCall)
    }.flowOn(Dispatchers.IO)

private suspend fun <M> FlowCollector<NetworkResult<M>>.processResultFlow(
    networkCall: suspend () -> NetworkResult<M>
) {
    if (!isNetworkAvailable()) {
        emit(
            NetworkResult.Failure(
                message = getString(application!!, R.string.please_check_your_network)
            )
        )
    } else when (val responseStatus = networkCall.invoke()) {
        is NetworkResult.Success -> {
            Log.d("SingleSource", "processResultFlow: emitted")
            emit(
                NetworkResult.Success(
                    responseStatus.message,
                    responseStatus.data
                )
            )
        }

        is NetworkResult.Failure -> {
            when {
                responseStatus.exception?.is401UnauthorizedError() == true -> {
                    EventBus.getDefault().post(MessageEvent(EventType.CLEAR_DATA_GO_TO_LOGIN))
                }

                else -> {
                    emit(
                        NetworkResult.Failure(
                            message = responseStatus.message,
                            exception = responseStatus.exception,
                            data = responseStatus.data
                        )
                    )
                }
            }
        }

        else -> {}
    }
}