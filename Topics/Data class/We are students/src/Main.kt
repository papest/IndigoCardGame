@SuppressWarnings("MagicNumber")
data class Student(val name: String, val money: Int) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Student) return false
        return name == other.name && other.money < 1500 && money < 1500
    }
}