package org.example

val billBoard = mutableMapOf<Char, Screening>()

fun createSampleData(mapIndex: Char, hall: Hall, movieType: Movie) {
    billBoard[mapIndex] = Screening(movie = movieType, hall = hall)
}

fun getScreening(input: Char) : Screening? = billBoard[input]

fun allScreening() : String {
    var poster = ""
    for ((key, screening) in billBoard) {
        poster += "$key. ${screening.movie} (зал ${screening.hall.hallId})\n"
    }
    return poster
}

fun bookSeat(row: Int, col: Int, mapIndex: Char) : Boolean {
    return billBoard[mapIndex]?.hall?.bookSeat(row, col) ?: false
}