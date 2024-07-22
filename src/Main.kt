import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
suspend fun main() = runBlocking() {
    //TODO 1. Написать программу покупки продукта в магазине. Она запускается с
    // фразы "Программа покупки продуктов". Далее через секундную задержку предлагает
    // оплатить товар: "Купить товар:\n1.Да\n2.Нет". Если да, то запускается корутина,
    // в которой идет процесс оплаты:
    // "Оплата продукта началась"-> через 2 сек -> "Сканирование и обработка..." ->
    // через 2 сек ->"Покупка оплачена."
    // Если нет, то запускается корутина отмены оплаты:
    // "Отмена покупки..." -> через 2 сек ->"Покупка отменена."
    // В любом другом случае – выходит ошибка.
    // Написать корутину, которая отвечает за окончание работы и выводит сообщение
    // "До свидания". Она может запускаться при любом вышеуказанном исполнении
    // программы в конце.
    println("1. Задание\n")

    println("Программа покупки продуктов")
    delay(1000L)
    println("Купить товар:\n1.Да\n2.Нет")
    val product = readln()

    try {
        checkProduct(product)
        productConsole(product)
    } catch (e: Exception) {
        println(e.message)
        jobPayment(::jobWrong)
        getLine("_")
    }

    getLine("=")

    //TODO 2. Написать программу, выводящую в консоль следующее сообщение:
    // Начало программы
    // 1
    // 2
    // Произошел ленивый запуск
    // 3
    // 4
    // Программа завершена
    // Реализацию вывода переменной счетчика необходимо исполнить с помощью корутины
    // с задержкой внутри 1 сек. Вывод строки "Произошел ленивый запуск" выполнить,
    // передав в параметр своей корутины CoroutineStart.LAZY, задержку выдержать в
    // соответствии  заявленным выводом (после вывода 2). Сообщение "Программа
    // завершена" также выполняется в своей корутине с отложенным запуском с таким
    // расчетом, чтобы она сработала после полного вывода переменной счетчика.
    println("2. Задание\n")

    println("Начало программы\n")

    val counter = 4

    val message = launch(start = CoroutineStart.LAZY) {
        delay(2000L)
        println("Произошел ленивый запуск")
    }


    val count = launch {
        for (count in 1..counter) {
            if (count == counter / 2) message.start()
            delay(1000L)
            println(count)
        }
        launch {
            delay(2000L)
            println("Программа завершена\n")
        }
    }
    count.join()

    getLine("^")
}