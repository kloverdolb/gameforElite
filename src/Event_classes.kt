import kotlin.math.roundToInt
import kotlin.random.Random


//создание класса событий и его наследника dependent
open class Events(var randomlistvibr: List<Double>, var randomlistpim: List<Double>){
    open fun getVibranium(): Double {
        return (Random.nextDouble(randomlistvibr[0], randomlistvibr[1])*100).roundToInt()/100.0
    }
    open fun getPims(): Double {
        return (Random.nextDouble(randomlistpim[0], randomlistpim[1])*100).roundToInt()/100.0
    }
}
//класс событий, которые зависят от текущих ресурсов
class Dependent(randomlistvibr: List<Double>, randomlistpim: List<Double>): Events(randomlistvibr, randomlistpim) {
    override fun getVibranium(): Double {
        val a = vibranium.itemvalue * (Random.nextDouble(randomlistvibr[0], randomlistvibr[1])*100).roundToInt()/100.0
        return (a * 100).roundToInt() / 100.0
    }
    override fun getPims(): Double {
        val a = pim.itemvalue * (Random.nextDouble(randomlistpim[0], randomlistpim[1])*100).roundToInt()/100.0
        return (a * 100).roundToInt() / 100.0
    }
}
//класс событий, которые имеют фиксированный эффект
class Fixed(randomlistvibr: List<Double>, randomlistpim: List<Double>): Events(randomlistvibr, randomlistpim) {
    override fun getVibranium(): Double {
        vibranium.itemvalue -= (randomlistvibr).min()
        return vibranium.itemvalue
    }

    override fun getPims(): Double {
        pim.itemvalue += (randomlistpim).min()
        return pim.itemvalue
    }
}
//функция для вывода реплик с эффектом "печати"
fun output(text: String) {
    for (char in text) {
        print(char)
        Thread.sleep(40)
    }
    println()
}

//функция пропуска одного дня для увеличения энергии персонажей
fun menu(actions: List<Pair<String, () -> Boolean>>) {
    while (true) {
        for (i in actions.indices) {
            println("${i + 1} - ${actions[i].first}")
        }
        print("Выберите действие: ")
        val ch = readln().toIntOrNull()
        if (ch != null && ch in 1..actions.size) {
            val scontinue = actions[ch - 1].second()
            if (!scontinue) {
                return
            }
        }
        else println("Неверный ввод. Попробуйте снова.")
    }
}
//создание списка событий
var mainactions = mutableListOf<Pair<String, () -> Boolean>>()
//Создание событий
val quillfight = Events(quill.randlistVib, quill.randlistPim)
val claimedluggage = Events(listOf(20.0,30.0), listOf(0.0,0.0))
val numberguessr = Events(listOf(20.0,30.0), listOf(0.0,0.0))
val fightwithloki = Events(loki.randlistVib, loki.randlistPim)
val reality = Dependent(listOf(0.1, 0.3), listOf(0.2, 0.4))
val realitydone = Dependent(listOf(0.05, 0.1), listOf(0.1, 0.2))
val skipped = Fixed(listOf(0.0), listOf(10.0))
val arguinghulk = Fixed(listOf(10.0), listOf(0.0))
//создание переменных, которая хранит изменение валют
var listoffixedvibr = mutableListOf<Double>()
var listoffixedpim = mutableListOf<Double>()
