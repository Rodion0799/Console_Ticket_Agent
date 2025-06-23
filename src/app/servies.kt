package app

typealias FlightTicket = MutableList<Flight>
typealias MyTicket = MutableList<Flight>
const val ERROR = "Ошибка!\n"


/**
 * Добавление рейса в систему
 */
fun addFlight(FlightList: FlightTicket) {
    print("Город отправления: ")
    val userFrom = readLine()!!.trim()
    if (userFrom.isBlank()) {
        println(ERROR)
        return
    }

    print("Город прибытия: ")
    val userTo = readLine()!!.trim()
    if (userFrom.isBlank()) {
        println(ERROR)
        return
    }

    print("Введите дату отправления через \"-\": ")
    val userDate = readLine()!!.trim()
    if (userDate.isBlank()) {
        println(ERROR)
        return
    }

    print("Введите цену: ")
    val userPrice = readLine()!!.trim().toIntOrNull()
    if (userPrice == null || userPrice !in 20000..1000000) {
        println(ERROR)
        return
    }

    print("Введите количество: ")
    val userStock = readLine()!!.trim().toIntOrNull()
    if (userStock == null || userStock < 1) {
        println(ERROR)
        return
    }

    FlightList.add(Flight(userFrom, userTo, userDate, userPrice, userStock))
    println("Билет добавлен!\n")
}


/**
 * Вывод рейсов
 */
fun tickets(flightList: FlightTicket) {
    if (flightList.isEmpty()) {
        println("Нету рейсов!\n")
        return
    }

    flightList.forEachIndexed { index, flight ->
        println("${index + 1}. ${flight.from} -> ${flight.to} - ${flight.date}, ${flight.price}р - Остаток [${flight.stock}]")
    }
    println()
}


/**
 * Покупка билета
 */
fun buyTicket(listFlight: FlightTicket, listTicket: MyTicket) {
    if (listFlight.isEmpty()) {
        println("Нету билетов для покупки!\n")
        return
    }

    listFlight.forEachIndexed { index, flight ->
        println("${index + 1}. ${flight.from} -> ${flight.to} - ${flight.date}, ${flight.price}р - Остаток [${flight.stock}]")
    }

    print("Выберите ID: ")
    val userByTicket = readLine()!!.trim().toIntOrNull()
    if (userByTicket == null || userByTicket > listFlight.size) {
        println(ERROR)
        return
    }

    val ticket = listFlight[userByTicket - 1]
    print("Введите количество: ")
    val userStock = readLine()!!.trim().toIntOrNull()
    if (userStock == null || userStock < 1) {
        println(ERROR)
        return
    }

    if (userStock > ticket.stock) {
        println("Нет столько билетов!\n")
        return
    }

    listTicket.add(Flight(ticket.from, ticket.to, ticket.date, ticket.price, userStock))
    ticket.stock -= userStock


    if (ticket.stock == 0) {
        listFlight.remove(ticket)
    } else {
        println("Билет куплен!\n")
    }
}


/**
 * Купленые билеты
 */
fun myTickets(myTickets: MyTicket) {
    if (myTickets.isEmpty()) {
        println("У вас нет билетов!\n")
        return
    }

    myTickets.forEachIndexed { index, flight ->
        println("${index + 1}. ${flight.from} -> ${flight.to} - ${flight.date}, ${flight.price}р - Остаток [${flight.stock}]")
    }
    println()
}

/**
 * Поиск билета
 */
fun searchTicket(listFlight: FlightTicket, listTicket: MyTicket) {
    if (listFlight.isEmpty()) {
        println("У вас нет билетов!\n")
        return
    }

    print("Выберите страну от куда летите, или куда: ")
    val userFromOrTo = readLine()!!.trim()
    if (userFromOrTo.isBlank()) {
        println(ERROR)
        return
    }

    val searchTicket = listFlight.filter { userFromOrTo == it.from || userFromOrTo == it.to }

    if (searchTicket.isEmpty()) {
        println("Не найдено!\n")
        return
    }

    when (searchTicket.size) {
        1 -> println("Найден: ${searchTicket.size} билет:\n")
        in 2..4 -> println("Найдено: ${searchTicket.size} билета:\n")
        in 5..20 -> println("Найдено: ${searchTicket.size} билетов:\n")
    }

    searchTicket.forEachIndexed { index, flight ->
        println("${index + 1}. ${flight.from} -> ${flight.to} - ${flight.date}, ${flight.price}р - Остаток [${flight.stock}]")
    }

    print("Хотите купить билет? \"Да\" или \"Нет\": ")
    val userYesOrNo = readLine()!!.trim()
    if (userYesOrNo.isBlank()) {
        println(ERROR)
        return
    }

    when (userYesOrNo) {
        "Да" -> {
            print("Выберите ID: ")
            val userID = readLine()!!.trim().toIntOrNull()
            if (userID == null || userID < 1 || userID > searchTicket.size) {
                println(ERROR)
                return
            }

            val tickets = searchTicket[userID - 1]
            print("Введите количество: ")
            val userStock = readLine()!!.trim().toIntOrNull()
            if (userStock == null || userStock !in 1..tickets.stock) {
                println(ERROR)
                return
            }

            listTicket.add(Flight(tickets.from, tickets.to, tickets.date, tickets.price, userStock))
            tickets.stock -= userStock
            println("Билет успешно добавлен в раздел: \"Мои\"\n")
        }
        "Нет" -> {
            println()
            return
        }
        else -> {
            println(ERROR)
            return
        }
    }
}


/**
 * Сумма билетов, добавленые в "Мои"
 */
fun sumTickets(listFlight: FlightTicket) {
    if (listFlight.isEmpty()) {
        println("У вас нет билетов!\n")
        return
    }

    val sumTickets = listFlight.sumOf { it.price * it.stock }
    println("Сумма ваших билетов = $sumTickets р.\n")
}