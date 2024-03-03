package observerpattern

import java.util.*

class Coffee(val name: String)

// Subject
class Barista : Observable() {

    private lateinit var coffeeName: String

    fun orderCoffee(name: String) {
        this.coffeeName = name
    }

    fun makeCoffee() {
        setChanged() //update 변경 사항 반영
        notifyObservers(Coffee(this.coffeeName))
    }

}

// Observer
class Customer(val name: String) : Observer {

    override fun update(o: Observable?, arg: Any?) {
        val coffee = arg as Coffee // coffee 로 캐스팅
        println("${name}이 ${coffee.name}을 받았습니다")
    }
}


fun main() {
    val barista = Barista()
    barista.orderCoffee("아이스 아메리카노")

    val customer = Customer("고객1")
    val customer2 = Customer("고객2")
    val customer3 = Customer("고객3")

    barista.addObserver(customer)
    barista.addObserver(customer2)
    barista.addObserver(customer) // list에 동일한 객체가 두 번 들어가지 않음.

    barista.makeCoffee()

    // 옵저버 패턴은 데이터를 제공하는 측에서 데이터를 소비하는 측에 통지하는 푸시 (Push-Based) 방식이다.

}