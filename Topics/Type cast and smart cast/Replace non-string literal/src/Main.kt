fun convertToStringList(list: List<Any>): List<String> {
    val stringList = mutableListOf<String>()
    for (element in list) {
        val stringElement = if (element as? String == null) "N/A" else element
        stringList.add(stringElement)
    }
    return stringList
}