import kotlin.math.roundToInt
import kotlin.random.Random

val red = "\u001b[31m"
val green = "\u001b[32m"
val blue = "\u001b[34m"
val purple = "\u001b[95m"
val orange = "\u001b[38;5;208m"
val yellow = "\u001b[33m"
val reset = "\u001b[0m"
//создание списка камней бесконечности - которые уже в инвентаре
val stoneslist = mutableListOf<Stones>()
//создание списка всех камней - для наглядного отображения в меню
val allstones = mutableListOf<Stones>()
//Создание дата-класса ресурсов и их задание через обьекты
val maxvibranium: Double = 1000.0
val maxpim: Double = 100.0
data class Resourses(val itemname: String, val itemshape: String, val itemcolor: String, var maxvalue: Double){
    val coloritemshape = "${itemcolor}$itemshape$reset"
    var itemvalue: Double = maxvalue
        set(value3) {
            field = value3.coerceIn(0.0, maxvalue)
        }
}
var pim = Resourses("Частицы Пима", "Ⓟ",red,100.0)
var vibranium = Resourses("Осколки вибраниума", "❖",purple,1000.0)
//дата класс камней бесконечности
data class Stones(val item: String, val color: String){
    val stone = "${color}$item$reset"
    val shape = "${color}⬢$reset"
    val shapeinlist: String
        get() = if (this !in stoneslist) "⬢" else "${color}⬢$reset"
}

//создания класса нпс - имя и функция включения в беседу
class Person(val name: String) {
    fun dialog(phrase: String) {
        print("$name: ")
        output("$phrase")
    }
}
//создание интерфейса ключевых существ
interface Entity {
    val name: String
    val maxhealth: Int
    var health: Int
    var damage: Int
    val status: Boolean
    fun dialog(phrase: String) {
        println("$name: $phrase")
    }

    fun mind(phrase: String) {
        println("($name: $phrase)")
    }

    fun DoDamage(other: Entity) {}
    fun DoHealth()
    fun fight(other: Entity) {}
}
//создание класса мстителей
var LifeAvengers = mutableListOf<Avengers>()
class Avengers(override val name:String, override var damage: Int, override var maxhealth: Int,
               val avengername: String, var energy: Int, val info: String) : Entity {
    var recenergy: Int = energy
        set(value1) {
            field = value1.coerceIn(0, energy)
        }
    override var health: Int = maxhealth
        set(value2) {
            field = value2.coerceIn(0, maxhealth)
            if (field == 0) {
                println("$avengername погиб!")
                LifeAvengers.remove(this)
            }
        }
    override val status: Boolean
        get() = health > 0

    override fun DoDamage(other: Entity){
        println("${this.avengername} наносит $damage урона по ${other.name}")
        other.health -= this.damage

    }
    //функция для битвы с вылетом одного из участников
    fun fight(other: Enemy, award: Events) {
        while (this.health > 0 && other.health>0) {
            this.DoDamage(other)
            Thread.sleep(1000)
            if (this.health==0 || other.health == 0) break
            other.DoDamage(this, 1)
            Thread.sleep(1000)

        }
        if (this.status == false) {
            println("${this.avengername} пал в бою от рук ${other.evilname}!")

        } else {
            println("${this.name} побеждает ${other.evilname}!")
            //дроп с врага
            val droppedvibranium = award.getVibranium()
            val droppedpim = award.getPims()
            vibranium.itemvalue += droppedvibranium
            pim.itemvalue += droppedpim
            println("Враг повержен! Получено ${droppedvibranium} Осколков вибраниума и ${droppedpim} частиц Пима")
        }
    }
    fun combinefight(comrade: Avengers, other: Enemy, award: Events) {
        this.dialog("...Будем сражаться вместе, ${comrade.avengername}!")
        while ((this.health > 0 || comrade.health > 0) && other.health>0) {
            if (this.health>0 && comrade.health>0) {
                this.DoDamage(other)
                Thread.sleep(1000)
                comrade.DoDamage(other)
                Thread.sleep(1000)
                if (other.health == 0) break
                other.DoDamage(this,2)
                other.DoDamage(comrade,2)
                Thread.sleep(1000)
            }
            else if (this.health>0 && comrade.health==0) {
                this.DoDamage(other)
                Thread.sleep(1000)
                if (other.health == 0) break
                other.DoDamage(this,2)
                Thread.sleep(1000)
            }
            else if (this.health==0 && comrade.health>0) {
                comrade.DoDamage(other)
                Thread.sleep(1000)
                if (other.health == 0) break
                other.DoDamage(comrade,2)
                Thread.sleep(1000)
            }
        }
        if (this.status == false && comrade.status == true) {
            println("${this.avengername} пал в бою от рук ${other.evilname}, но ${comrade.avengername} победил ${other.evilname}!")
            val droppedvibranium = award.getVibranium()
            val droppedpim = award.getPims()
            vibranium.itemvalue += droppedvibranium
            pim.itemvalue += droppedpim
            println("Враг повержен! Получено ${droppedvibranium} Осколков вибраниума и ${droppedpim} частиц Пима")
        }
        if(this.status == true && comrade.status == false) {
            println("${comrade.avengername} пал в бою от рук ${other.evilname}, но ${this.avengername} победил ${other.evilname}!")
            val droppedvibranium = award.getVibranium()
            val droppedpim = award.getPims()
            vibranium.itemvalue += droppedvibranium
            pim.itemvalue += droppedpim
            println("Враг повержен! Получено ${droppedvibranium} Осколков вибраниума и ${droppedpim} частиц Пима")
        }
        if(this.status == false && comrade.status == false) {
            println("${comrade.avengername} и ${this.avengername} пали в бою от ${other.evilname}!")
        } else {
            println("${this.avengername} и ${comrade.avengername} вместе побеждают ${other.evilname}!")
            val droppedvibranium = award.getVibranium()
            val droppedpim = award.getPims()
            vibranium.itemvalue += droppedvibranium
            pim.itemvalue += droppedpim
            println("Враг повержен! Получено ${droppedvibranium} Осколков вибраниума и ${droppedpim} частиц Пима")
        }
    }
    override fun dialog(phrase: String) {
        print("$name: ")
        output("$phrase")
    }
    override fun DoHealth() {
        this.health = this.maxhealth
        println("$name отправляется в высокотехнологический мед центр Ваканды, где восстанавливает все здоровье.")
    }
    fun dialogAsAvenger(phrase: String) {
        println("$avengername: $phrase")
    }
    override fun mind(phrase: String) {
        output("[$name: $phrase]")
    }
    fun mindAsAvenger(phrase: String) {
        output("[$avengername: $phrase]")
    }
}

//создание класса противников
class Enemy(override val name:String, override var damage: Int, override val maxhealth: Int, val energy: Int, val evilname: String, val randlistVib: List<Double>, val randlistPim: List<Double>) : Entity {
    //переписывание логики хп - чтобы не опускалось ниже 0 или выше максимального значения
    override var health: Int = maxhealth
        set(value) {
            field = value.coerceIn(0, maxhealth)
        }
    //описание логики статуса "жизни"
    override val status: Boolean
        get() = if (health <= 0) {
            println("$name погиб!")
            false
        } else true
    fun dialogAsEvil(phrase: String) {
        println("$evilname: $phrase")
    }
    fun DoDamage(other: Entity, kef: Int){
        println("${this.evilname} наносит ${damage/kef} урона по ${other.name}")
        other.health -= this.damage/kef
    }
    fun fight(other: Avengers){
        while (this.health > 0 && other.health>0) {
            this.DoDamage(other)
            Thread.sleep(1000)
            if (this.health==0 || other.health == 0) break
            other.DoDamage(this)
            Thread.sleep(1000)

        }
        if (this.status == false) {
            println("${this.evilname} пал в бою от рук ${other.avengername}!")

        } else {
            println("${this.evilname} побеждает ${other.avengername}!")

        }
    }

    override fun dialog(phrase: String) {
        print("$name: ")
        output("$phrase")
    }
    override fun DoHealth() {
        this.health += maxhealth/4
        println("$name восстанавливает ${maxhealth/4} единиц здоровья")
    }
    override fun mind(phrase: String) {
        println("$name: $phrase")
    }
}
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
var mainactions = mutableListOf<Pair<String, () -> Boolean>>()
//создание противников
val quill = Enemy("Питер Квилл", 30, 280, 0, "Звёздный Лорд", listOf(40.0, 50.0), listOf(3.0, 5.0))
val loki = Enemy("Локи", 50, 600, 0,  "Локи", listOf(80.0, 100.0), listOf(5.0, 8.0))
val thanos = Enemy("Танос",600,5200, 900, "Безумный Титан", listOf(), listOf())
//Создание событий
val quillfight = Events(quill.randlistVib, quill.randlistPim)
val claimedluggage = Events(listOf(20.0,30.0), listOf(0.0,0.0))
val numberguessr = Events(listOf(20.0,30.0), listOf(0.0,0.0))
val fightwithloki = Events(loki.randlistVib, loki.randlistPim)
val reality = Dependent(listOf(0.1, 0.3), listOf(0.2, 0.4))
//создание переменных, которая хранит изменение валют
var listoffixedvibr = mutableListOf<Double>()
var listoffixedpim = mutableListOf<Double>()
