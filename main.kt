class Dot<A>(vararg coordinate: A ){
    var coordinates = arrayListOf<A>()
    init{
    for (i in coordinate) coordinates.add(i)
    }
   
}
fun main() {
    var x = Dot(1.0,1.0)
    var sum = x.coordinates[0] + x.coordinates[1]
    print(sum)
}
