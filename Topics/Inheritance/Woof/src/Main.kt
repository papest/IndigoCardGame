open class Dog(val name: String, val color: String, val weight: Int) {
    fun printInfo() {
        println("The dog's name is $name, his color is $color and his weight is $weight")
    }
}

class Labrador(name: String, color: String, weight: Int) : Dog(name, color, weight)