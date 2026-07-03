package org.example

// Сеанс, содержит в себе тип фильма + залЮ где проходит этот фильм
class Screening (
    val movie: Movie,
    val hall: Hall
)

// Фильм, содержит в себе типы фильмов
enum class Movie {
    ACTION, HORROR, DRAMA
}

// Зал, содержит в себе массив из количества мест, и свой Id
class Hall (
    val row: Int,
    val column: Int,
    val hallId: String
) {
    private val seats = ArrayList<Seat>()

    init {
        createHall(row, column)
    }

    private fun createHall (row: Int, column: Int){
        seats.clear()
        when {
            (row > 0 && column > 0) -> {
                for (rowIndex in 0..<row) {
                    for (columnIndex in 0..<column) {
                        val seatIndex: Seat = Seat(rowIndex, columnIndex)
                        seats.add(seatIndex)
                    }
                }
            }
            (row <= 0) -> {
                throw IllegalArgumentException("row must be more than 0.")
            }
            (column <= 0) -> {
                throw IllegalArgumentException("column must be more than 0.")
            }
            else -> throw IllegalArgumentException("Unknown error")
        }
    }

    fun getSeatIndex(row: Int, column: Int): Int {
        return row * this.column + column
    }

    fun getBooked(row: Int, column: Int): Boolean {
        return seats[row * this.column + column].isBooked
    }

    fun setBooked(row: Int, column: Int) {
        seats[row * this.column + column].isBooked = true
    }

    // Геттер для размеров поля
    val hallSize = seats.size
}

// Место содержит свой ряд и колнку, а также заданный конструктором false на вопрос занято или нет
class Seat (
    val row: Int,
    val column: Int,
    var isBooked: Boolean = false
)
