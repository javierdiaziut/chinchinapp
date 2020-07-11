package gt.tribal.core.base.responseWithMessage

data class ResponseWithMessage<T>(
    val data: T,
    val status: Boolean,
    val message: String,
    val messageDescription: MessageDescription
)