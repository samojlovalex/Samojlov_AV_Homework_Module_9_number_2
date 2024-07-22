import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KSuspendFunction0

suspend fun payment() {
    println("Оплата продукта началась")
    delay(2000L)
    println("Сканирование и обработка...")
    delay(2000L)
    println("Покупка оплачена.")
}

suspend fun cancellationOfPayment() {
    println("Отмена покупки...")
    delay(2000L)
    println("Покупка отменена.")
}

suspend fun jobWrong() {
    delay(2000L)
    println("Ошибка выбора")
}

suspend fun goodbye() {
    delay(2000L)
    println("До свидания")
}

suspend fun jobPayment(func: KSuspendFunction0<Unit>) = runBlocking {
    val job = launch {
        func()
        goodbye()
    }
    job.join()
}

fun checkProduct(product: String): String {
    val checkReadln: Boolean = product == "1" || product == "2"

    if (!checkReadln) {
        throw Exception(
            "Введено недопустимое значение"
        )
    }
    return product
}

suspend fun productConsole(product: String) {
    if (product == "1") {
        Loop@ while (true) {
            jobPayment(::payment)
            getLine("_")
            println("Купить товар:\n1.Да\n2.Нет")
            val productNew = readln()
            checkProduct(productNew)
            if (productNew == "2") {
                while (true) {
                    jobPayment(::cancellationOfPayment)
                    getLine("_")
                    break@Loop
                }
            }
        }
    } else {
        jobPayment(::cancellationOfPayment)
        getLine("_")
    }
}