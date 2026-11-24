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
//создание обьектов класса мстителей
val iron = Avengers("Тони Старк", 50, 300, "Железный человек", 100, ironDesc)
val cap = Avengers("Стив Роджерс", 30, 380, "Капитан Америка", 50, capDesc)
val widow = Avengers("Наталья Романофф", 20, 200, "Черная вдова", 100, widowDesc)
val ant = Avengers("Скотт Лэнг", 40, 300, "Человек-муравей", 50, antDesc)
val sokol = Avengers("Клинт Бартон", 30, 250, "Соколиный глаз", 50, sokolDesc)
val hulk = Avengers("Брюс Беннер", 50, 500, "Халк", 25, hulkDesc)
val rocket = Avengers("Ракета", 30, 250, "Ракета", 70, rocketDesc)
val thor = Avengers("Тор", 50, 400, "Бог грома", 50, thorDesc)
val roud = Avengers("Джеймс Роудс", 40, 280, "Воитель", 110, roudDesc)
val nebula = Avengers("Небула", 20, 300, "Небула", 75, nebulaDesc)
val spider = Person("Питер Паркер")
var avengersteam = Avengers("Команда Мстителей", LifeAvengers.map{it.damage}.sum(),LifeAvengers.map{it.health}.sum(), "Команда Мстителей", LifeAvengers.map{it.energy}.sum(), "-" )
//все мстители
val allavengers = listOf(iron, cap, widow, ant, thor, roud, nebula, rocket, hulk, sokol)
//создание противников
val quill = Enemy("Питер Квилл", 30, 280, 0, "Звёздный Лорд", listOf(40.0, 50.0), listOf(3.0, 5.0))
val loki = Enemy("Локи", 50, 600, 0,  "Локи", listOf(80.0, 100.0), listOf(5.0, 8.0))
val thanos = Enemy("Танос",600,5200, 900, "Безумный Титан", listOf(), listOf())