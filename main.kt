class Dot<A>(vararg coordinate: A ){
    var coordinates = arrayListOf<A>()
    init{
    for (i in coordinate) coordinates.add(i)
    }
    operator fun get(index:Int):A{ return coordinates[index]}
   
}
fun main() {
    var x = Dot(1.0,1.0)
    var sum = x[0] + x[1]
    print(sum)
}
