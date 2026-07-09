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
    val rows: Int,
    val columns: Int,
    val hallId: String
) {
    private val seats = ArrayList<Seat>()

    init {
        initializeSeats(rows, columns)
    }

    private fun initializeSeats (row: Int, column: Int){
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
        }
    }

    private fun toIndex(row: Int, col: Int) = row * columns + col

    private fun isValid(row: Int, col: Int) = (row in 0..<this.rows && col in 0..<this.columns)

    fun isSeatBooked(row: Int, col: Int): Boolean{
        if (!isValid(row, col)) throw IllegalArgumentException("row or column out of bounds")
        return seats[toIndex(row, col)].isBooked
    }

    fun bookSeat(row: Int, col: Int) : Boolean {
        if (!isValid(row, col)) return false
        val index = toIndex(row, col)
        if (seats[index].isBooked) return false
        seats[index].isBooked = true
        return true
    }

    // Геттер для размеров поля
    val totalSeats = seats.size
}

// Место содержит свой ряд и колнку, а также заданный конструктором false на вопрос занято или нет
class Seat (
    val row: Int,
    val column: Int,
    var isBooked: Boolean = false
)
