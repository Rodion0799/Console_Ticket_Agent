package app

/**
 * start
 */
fun main() {
    val flightList = mutableListOf<Flight>()
    val ticketList = mutableListOf<Flight>()
    while (true) {
        println("1. Добавить рейс\n" +
                "2. Показать все рейсы\n" +
                "3. Купить билет\n" +
                "4. Мои билеты\n" +
                "5. Общая сумма\n" +
                "6. Поиск\n" +
                "7. Выход")
        print("Выберите ID: ")
        val userID = readLine()!!.trim().toIntOrNull()
        if (userID == null || userID !in 1..7) {
            println("Ошибка!\n")
            return
        }
        when(userID) {
            1 -> addFlight(flightList)
            2 -> tickets(flightList)
            3 -> buyTicket(flightList, ticketList)
            4 -> myTickets(ticketList)
            5 -> sumTickets(ticketList)
            6 -> searchTicket(flightList, ticketList)
            7 -> {
                println("До свидание!")
                break
            }
        }
    }
}