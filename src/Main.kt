import kotlin.Int
import kotlin.random.Random
import kotlin.time.TimeSource
import kotlin.math.roundToInt
import kotlin.time.DurationUnit

//тело игрового цикла
fun main() {
    println("«За 7 дней до Финала» по мотивам фильма «Мстители: Финал»")
    println("Продолжить? [ENTER]")
    val timeSource = TimeSource.Monotonic
    val mark = timeSource.markNow()
    var start = readln()
    //список живых мстителей
    LifeAvengers = mutableListOf<Avengers>(iron, cap, widow,ant,hulk,rocket,thor,roud,nebula,sokol)
    allstones.add(powerstone)
    allstones.add(mindstone)
    allstones.add(spacestone)
    allstones.add(realitystone)
    allstones.add(timestone)
    allstones.add(soulstone)
    //задание начальных значений ресурсов
    vibranium.itemvalue = 100.0
    pim.itemvalue = 10.0
    var fixedvibranium = vibranium.itemvalue
    var fixedpim = pim.itemvalue
    //ВАЖНЫЕ ФУНКЦИИ В МЕНЮ
    fun heal(person: Avengers){
        println("Вы уверены, что хотите вылечить персонажа? [ДА/НЕТ]")
        val ans = readln().lowercase()
        if (ans == "да") {
            if (vibranium.itemvalue > 50) {
                vibranium.itemvalue -= 50
                person.DoHealth()
            } else {
                println("Недостаточно средств.")
            }
        }

    }

    fun avengerstats(person: Avengers){
        println("""
            ${person.avengername}:
            ● Имя Мстителя: ${person.name}
            ● Здоровье: ${person.health}/${person.maxhealth}
            ● Урон: ${person.damage}
            ● Энергия: ${person.energy}
""")
        var avengerstatsmenu = listOf(
            "Вылечить персонажа" to {heal(person); true},
            "Посмотреть краткую информацию о герое" to {output(person.info); true},
            "Выйти" to {println(); false}
        )
        menu(avengerstatsmenu)
    }
    fun enemystats(person: Enemy){
        println("""
            ${person.evilname}:
            ● Имя врага: ${person.name}
            ● Здоровье: ${person.health}/${person.maxhealth}
            ● Урон: ${person.damage}
            ● Энергия: ${person.energy}
""")
    }
    fun balance(){
        println("""             БАЛАНС
    ${vibranium.coloritemshape} Осколки вибраниума: ${vibranium.itemvalue}
    ${pim.coloritemshape} Частицы Пима: ${pim.itemvalue}
        """)
    }

    fun seeLifeAvengers(){
        val avengerMenu= mutableListOf<Pair<String, () -> Boolean>>()
        println("Информация о мстителях в игре:")
        for (i in LifeAvengers) {
            avengerMenu.add("${i.avengername}" to { avengerstats(i)
                false
            })
        }
        menu(avengerMenu)
    }
    fun seestones(){
        val res = allstones.map{it.shapeinlist}.joinToString(separator = " ")
        println("    ${res}")
        println("Камней бесконечности собрано: ${stoneslist.size}/6")
    }
    fun seestats(){
        println("""Характеристики всех Мстителей:
        ● Здоровье: ${LifeAvengers.sumOf { it.health }}
        ● Урон: ${LifeAvengers.sumOf { it.damage }}
        ● Энергия: ${LifeAvengers.sumOf { it.energy }}
            !ВАЖНО: характеристики Мстителей повышаются в 2 раза, если собраны все камни.""")
    }
    fun seestatsOfThanos(){
        println("""Танос:
        ● Здоровье: ${thanos.health}/${thanos.maxhealth}
        ● Урон: ${thanos.damage}
        ● Энергия: ${thanos.energy}
    """)
    }
    fun buystoneforpim(stone: Stones): Boolean{
        println("Вы уверены, что хотите потратить 10 ${pim.itemname}?")
        var quest = readln().lowercase()
        if (quest=="да") {
            if (pim.itemvalue > 10.0) {
                pim.itemvalue -= 10
                stoneslist.add(stone)
                println("${stone.stone} был успешно получен! Камней бесконечности: ${stoneslist.size}/6. Возвращаемся в главное меню...")
                return false
            }else{
                println("Этого ресурса недостаточно. Попробуйте купить за ${vibranium.itemname}")
                return true
            }
        }
        else return true
    }

    fun buystoneforvibranium(stone: Stones): Boolean{
        println("Вы уверены, что хотите потратить 50 ${vibranium.itemname}? [ДА/НЕТ]")
        var quest = readln().lowercase()
        if (quest=="да") {
            if (vibranium.itemvalue > 50.0) {
                vibranium.itemvalue -= 50
                stoneslist.add(stone)
                println("${stone.stone} был успешно получен! Камней бесконечности: ${stoneslist.size}/6. Возвращаемся в главное меню...")
                return false
            }else{
                println("Этого ресурса недостаточно. Попробуйте купить за ${pim.itemname}")
                return true
            }
        }
        else return true
    }
    fun buystone(stone: Stones){
        println("За какую валюту желаете приобрести ${stone.stone}?")
        balance()
    }
    fun luggagelugg(avenger: Avengers){
        if (avenger.name == cap.name) {
            var randvibr = claimedluggage.getVibranium()
            output("""${cap.avengername} пытается унести чемодан и был замечен агентами Г.И.Д.Р.А! Вы потеряли ${randvibr} Осколков вибраниума!""")
            vibranium.itemvalue -= randvibr
            Thread.sleep(500)
            cap.dialog("Всё-таки заметили... Надо убежать")
        }
        else {
            var randvibr = claimedluggage.getVibranium()
            output("Благодаря своей способности ${ant.avengername} уменьшился и незаметно пронес чемодан! Вы получили ${randvibr} Осколков вибраниума!")
            vibranium.itemvalue += randvibr
        }
    }
    fun GuessTheNumber() {
        var t = false
        println("Помогите Стиву Роджерсу и Скотту Лэнгу разгадать двузначный пароль от чемодана.")
        println("В общей сложности даётся 6 попыток с подсказками, больше это число или меньше загаданного")
        while (vibranium.itemvalue > 0) {
            println("Загадывается число...")
            var unlucky = mutableListOf<Int>()
            val number = (10..99).random()
            for (i in 1..6) {
                println("Попытка ${i}:")
                var s = readln().toIntOrNull()
                if (s==null){
                    println("Введите корректное двузначное число")
                    unlucky.add(1)
                }
                else if (s == number) {
                    println("Вы угадали число! Код: ${number}")
                    return
                }
                else if (s > number) {
                    println("Кодовое число меньше написанного. Попробуйте ещё раз.")
                    unlucky.add(s)
                } else {
                    println("Кодовое число больше написанного. Попробуйте ещё раз.")
                    unlucky.add(s)
                }
            }
            if (unlucky.size == 6) {
                var randvibr = numberguessr.getVibranium()
                vibranium.itemvalue -= randvibr
                println("""К сожалению не удалось угадать код (${number}). Вы потеряли ${randvibr} Осколков вибраниума.
Баланс: ${vibranium.itemvalue}. Пробуйте отгадать снова.""")
                unlucky.clear()
            }
            if (vibranium.itemvalue == 0.0) {
                println("К сожалению вы не смогли получить ${mindstone.stone}, т.к. у вас закончились Осколки вибраниума.")
            }


        }
    }
    fun getrealitystone(){
        println("Помогите Ракете достать ${realitystone.stone} из тела Джейн Фостер.")
        Thread.sleep(1000)
        println("Для этого настройте экстрактор: помогите Ракете нажать на нужную кнопку.")
        Thread.sleep(1000)
        println("Даётся 5 кнопок с разным эффектом: 2 правильные (✓), 2 нейтральные (-) и 1 негативная (✖).")
        println("При выборе правильных кнопок - выкачка Эфира из Джейн Фостер, при негативной - отнимаются ресурсы.")
        println("Каждую попытку располагаемые кнопки меняются")
        println("Желаем удачи!")
        Thread.sleep(1000)
        while (pim.itemvalue > 0 && vibranium.itemvalue > 0) {
            println("Расставляются объекты...")
            println("[1] [2] [3] [4] [5]")
            val buttons = mutableListOf<String>("[✖]", "[✓]", "[✓]", "[-]", "[-]").shuffled()
            val button = readln().toIntOrNull()
            if (button == null){
                println("Укажите корректное число от 1 до 5")
                continue
            }
            if (button in 1..5) {
                if (buttons[button-1] == "[-]"){
                    println("Вы нажали на нейтральную кнопку; повторите снова")
                    println("Значения кнопок: ${buttons.joinToString(" ")}")
                }
                else if (buttons[button-1] == "[✓]"){
                    println("Вы нажали на правильную кнопку; вы угадали!")
                    println("Значения кнопок: ${buttons.joinToString(" ")}")
                    return
                }
                else {
                    var randvibr = reality.getVibranium()
                    var randpim = reality.getPims()
                    println("""Вы нажали на неправильную кнопку; вы потеряли ${randvibr} Осколков вибраниума и
${randpim} Частиц Пима.""")
                    vibranium.itemvalue -= randvibr
                    pim.itemvalue -= randpim
                }
            }

        }
    }
    //функция действий на локации с камнем силы
    var claimpowerstone = false
    fun topowerstone() {
        if (claimpowerstone == false) {
            output("2014, Мораг")
            Thread.sleep(1000)
            roud.dialog("Вроде оказались в правильном месте... Так где тут хранится камень?")
            Thread.sleep(500)
            nebula.dialog("Вон в том храме... Идём")
            Thread.sleep(500)
            roud.dialog("А это кто? Неужели Квилл? В этом времени он нам враг или друг?")
            println()
            output(
                """Небула и Роудс переместились именно в тот момент, когда ${quill.name} - 
${quill.evilname} и по совместительству союзник Мстителей в будущем - сам шёл забрать камень силы.
"""
            )
            Thread.sleep(500)
            roud.dialog("Ничего страшного - опередим его и возьмем камень первыми.")
            Thread.sleep(500)
            quill.dialogAsEvil("Стоять, а вы ещё кто такие?")
            nebula.dialog("Кажется, драка неизбежна.")
            println("Выберите персонажа для битвы:")
            var mainactions = listOf(
                "${nebula.name}" to { nebula.fight(other = quill, quillfight); false },
                "${roud.name}" to { roud.fight(other = quill, quillfight); false },
                "Показатели ${nebula.name}" to { avengerstats(person = nebula); true },
                "Показатели ${roud.name}" to { avengerstats(person = roud); true },
                "Показатели ${quill.evilname}" to { enemystats(person = quill); true }
            )
            menu(mainactions)
            Thread.sleep(500)
            if (nebula !in LifeAvengers) {
                roud.dialog("""НЕТ! НЕБУЛА... Надо быстрее хватать этот чёртов камень и валить отсюда""")
                Thread.sleep(500)
                quill.dialogAsEvil("*надрываясь после полученного урона* А ты попробуй")
                Thread.sleep(500)
                roud.dialog("Что ж, раз так...")
                roud.fight(quill,quillfight)
                Thread.sleep(500)
                roud.dialog("Надо как можно быстрее дуть в храм и забрать ${powerstone.stone}")
            } else {
                nebula.dialog("Роудс, вы как?")
                roud.dialog("Ранен, но вроде жив... Наша реальность же не поменяется после его смерти?")
                Thread.sleep(500)
                nebula.dialog(
                    """Мы находимся в другой реальности. Это его альтернативная версия,
так что переживать не о чем"""
                )
                roud.dialog("Тогда скорее идём в храм за ${powerstone.stone}")
                Thread.sleep(500)
            }
            println("Прогресс сохранён!")
            claimpowerstone = true
        }
        buystone(powerstone)
        var mainactions: List<Pair<String, () -> Boolean>> = listOf(
            "${vibranium.itemname}" to { buystoneforvibranium(powerstone) },
            "${pim.itemname}" to { buystoneforpim(powerstone) },
            "Выйти из данного меню (не хватает ресурсов)" to { println("К сожалению вы не выбрали ресурс для получения ${powerstone.stone}, однако вы всегда можете вернуться"); false }
        )
        menu(mainactions)

    }
    //функция действий на локации с камнем разума
    var claimmindstone = false
    fun tomindstone() {
        if (claimmindstone == false) {
            output("2012, Нью-Йорк")
            cap.dialog("Переместились вроде правильно. В этот момент Локи как раз арестовывают.")
            Thread.sleep(500)
            ant.dialog("Локи? Это тот самый братец Тора, царь Асгарда, от которого вы отбивались несколько лет назад?")
            Thread.sleep(500)
            cap.dialog("""Да, но нам сейчас нужен не он, а его скипетр. Именно в нём находится камень. А сам скипетр 
должны забирать сотрудники Г.И.Д.Р.А.""")
            ant.dialog("Погоди, разве Г.И.Д.Р.А. - это не нацистская организация, которая распалась после войны?")
            Thread.sleep(500)
            cap.dialog("""Нет. Они всё это время скрывались под организацией Щ.И.Т., хотя это долгая история... Нам надо быстрее
в башню Старка.
""")
            output("После этого ${ant.avengername} и ${cap.avengername} отравились в башню за скипетром Локи.")
            Thread.sleep(1000)
            cap.dialog("А вот и чемодан со скипетром... Только вряд ли я смогу его незаметно взять")
            println("Выберите того, кто унесёт чемодан")
            val mainactionsmind = listOf(
                "${cap.avengername}" to {luggagelugg(cap); false},
                "${ant.avengername}" to {luggagelugg(ant); false}
            )
            menu(mainactionsmind)
            Thread.sleep(1000)
            cap.dialog("Отлично, теперь можно забрать скипетр и уходить.")
            Thread.sleep(500)
            ant.dialog("Да, но тут какой то двузначный код...")
            Thread.sleep(500)
            cap.dialog("Код? Наверное установили чтобы точно никто не забрал...")
            Thread.sleep(500)
            ant.dialog("Погоди, а нельзя вместе с собой взять, а Старк, ну или же Халк, сломают чемодан?")
            Thread.sleep(500)
            cap.dialog("""Это Г.И.Д.Р.А. Они все придумывают наперед. Запросто могли настроить самоуничтожение содержимого,
как только оно покидает территорию, так что разгадать необходимо на месте.
""")
            GuessTheNumber()
            if (vibranium.itemvalue == 0.0){
                return
            }
            cap.dialog("Ну наконец-то, скипетр... Именно в его наконечнике и хранится ${mindstone.stone}")
            println("Прогресс сохранён!")
            claimmindstone = true
        }

        buystone(mindstone)
        var mainactions: List<Pair<String, () -> Boolean>> = listOf(
            "${vibranium.itemname}" to { buystoneforvibranium(mindstone) },
            "${pim.itemname}" to { buystoneforpim(mindstone) },
            "Выйти из данного меню (не хватает ресурсов)" to { println("К сожалению вы не выбрали ресурс для получения ${mindstone.stone}, однако вы всегда можете вернуться"); false }
        )
        menu(mainactions)
    }

    var claimspacestone = false
    fun tospacestone() {
        if (claimspacestone == false) {
            output("2012, Нью-Йорк")
            iron.dialog("Алло, кэп, ты где")
            Thread.sleep(500)
            output(
                """Сразу после того, как ${cap.avengername} нашёл камень разума, он вернулся к Тони Старку на 3 часа раньше
относительно времени, из которого перемещался.
        """.trimMargin()
            )
            cap.dialog("Камень вон там, рядом?")
            iron.dialog("Ну надо же, наконец то пришёл")
            Thread.sleep(500)
            cap.dialog("А что это за презрение в мою сторону? Между прочим мы договорились переместиться все вместе в Нью-Йорк")
            iron.dialog(
                """Так ладно, хватит споров. Объясняю ситуацию: мой хроновигатор из-за незначительной погрешности переместил
меня на пару часов раньше положенного. И да, Локи ещё не задержан, но от тессеракта с камнем далеко.
"""
            )
            Thread.sleep(500)
            cap.dialog("Тогда идём забирать.")
            Thread.sleep(1000)
            output("${cap.avengername} и ${iron.avengername} уже собирались взять камень, но вдруг...")
            Thread.sleep(500)
            loki.dialog("Ну надо же, кто пришёл... Старк и Стив Роджерс, самые опасные мстители!")
            Thread.sleep(500)
            iron.dialog("Всё таки поджидал нас... А я думал этот кубик тебе уже и не нужен.")
            Thread.sleep(500)
            loki.dialog("Какие же вы, люди, наивный народ... Вам никогда не заполучить тессеракт, ещё немного и мне подчинитесь ВЫ ВСЕ")
            Thread.sleep(500)
            cap.dialog("Тони, он нам не отдаст тессеракт без боя.")
            Thread.sleep(500)
            iron.dialog("Что ж... Поодиночке этот недобог нас размажет, значит...")
            println("Выберите персонажа для битвы с Локи")
            var mainactionsspace = listOf(
                "${cap.avengername}" to { cap.fight(loki, fightwithloki); false },
                "${iron.avengername}" to { iron.fight(loki, fightwithloki); false },
                "? -> (1+1)" to { iron.combinefight(cap, loki, fightwithloki); false },
                "Показатели ${iron.avengername}" to { avengerstats(person = iron); true },
                "Показатели ${cap.avengername}" to { avengerstats(person = cap); true },
                "Показатели ${loki.evilname}" to { enemystats(person = loki); true }
            )
            while (true) {
                menu(mainactionsspace)
                if (cap !in LifeAvengers) {
                    println("Капитан Америка погиб, попробуйте выбрать другой пункт в меню")
                    cap.health = cap.maxhealth
                    loki.health = loki.maxhealth
                    LifeAvengers.add(cap)
                }
                else if (iron !in LifeAvengers) {
                    println("Железный человек погиб, попробуйте выбрать другой пункт в меню")
                    iron.health = iron.maxhealth
                    loki.health = loki.maxhealth
                    LifeAvengers.add(iron)
                } else {
                    iron.dialog("Фух, после такого и отдых можно взять")
                    cap.dialog("Нелёгкая была битва, но главное, что теперь ${spacestone.stone} наш!")
                    break
                }
            }
            claimspacestone = true
            println("Прогресс сохранён!")
        }
        buystone(spacestone)
        var mainactions: List<Pair<String, () -> Boolean>> = listOf(
            "${vibranium.itemname}" to { buystoneforvibranium(spacestone) },
            "${pim.itemname}" to { buystoneforpim(spacestone) },
            "Выйти из данного меню (не хватает ресурсов)" to { println("К сожалению вы не выбрали ресурс для получения ${spacestone.stone}, однако вы всегда можете вернуться"); false }
        )
        menu(mainactions)
    }
    var claimtimestone = false
    fun totimestone(){
        if (claimtimestone == false) {
            output("2012, Нью-Йорк, Санктум Санкторум")
            Thread.sleep(500)
            output("""Брюс Беннер переместился прямо на крышу Санктум Санкторума (Святая Святых) - 
место, где охранял ${timestone.stone} некий Доктор Стрэндж - Верховный маг Земли.
""")
            hulk.mind("Так вот каков он дом Стрэнджа, Санктум Санкторум. Осталось только найти его")
            val ancient = Person("Древняя")
            Thread.sleep(500)
            hulk.dialogAsAvenger("Извините, вы не подскажете, где находится Доктор Стрэндж?")
            Thread.sleep(500)
            ancient.dialog("Интересно... Доктор Стрэндж ещё даже не с нами")
            println()
            hulk.mind("Что? Она его знает, несмотря на то, что в их времени они даже не пересекались?")
            output("После этого Брюс замечает талисман с камнем на шее у Древней")
            println()
            Thread.sleep(500)
            hulk.dialog("Хотя знаете, он и не особо нужен, дайте лишь вон тот камешек...")
            Thread.sleep(500)
            ancient.dialog("""Исключено! Я - Древняя, Верховный чародей, и моя задача охранять 
Землю от внешнего вторжения врагов. Наше главное оружие против них - ${timestone.stone}. Именно благодаря нему
я способна знать разные варианты событий в будущем, в том числе и появление Доктора Стрэнджа.""")
            hulk.dialog("""Но послушайте, в нашей Вселенной нужны все камни времени, Танос истребил половину
всего живого, мы должны собрать все 6 камней.
""")
            ancient.dialog("""А знали ли вы, Брюс, что если какой-то из камней бесконечности изъять из
Вселенной, то эта Вселенная обречена на темную альтернативную реальность и лишится защиты от угроз.
""")
            hulk.dialog("Именно поэтому Стрэндж отдал этот камень Таносу?")
            Thread.sleep(500)
            ancient.dialog("""Что... Отдал Таносу? Судя по всему он сделал это осознанно ради общего плана
по спасению Вселенной... Хорошо, я отдам вам камень, но при одном условии: Вы должны после операции вернуть все камни
на места, откуда их взяли.
""")
            hulk.dialog("Хорошо, даю слово.")
            claimtimestone = true
            println("Прогресс сохранён!")
        }
        buystone(timestone)
        var mainactions: List<Pair<String, () -> Boolean>> = listOf(
            "${vibranium.itemname}" to { buystoneforvibranium(timestone) },
            "${pim.itemname}" to { buystoneforpim(timestone) },
            "Выйти из данного меню (не хватает ресурсов)" to { println("К сожалению вы не выбрали ресурс для получения ${timestone.stone}, однако вы всегда можете вернуться"); false }
        )
        menu(mainactions)
    }
    var claimrealitystone = false
    fun torealitystone() {
        if (claimrealitystone==false) {
            output("2013, Асгард")
            Thread.sleep(1000)
            rocket.dialog("Ну и дыра... Ой, то есть, роскошный дворец! Так где эта твоя бывшая с Эфиром?")
            Thread.sleep(500)
            thor.dialog("Джейн сейчас в покоях, под присмотром лекарей. Эфир буквально высасывает из неё жизнь.")
            Thread.sleep(500)
            rocket.dialog("Значит, план такой: я тихо подкрадываюсь с экстрактором, выкачиваю эту красную жижу, и мы валим.")
            Thread.sleep(500)
            thor.dialog(
                """*тяжело вздыхает и озирается по сторонам* Погоди, Ракета... Я не уверен, что готов. Сегодня ведь тот
самый день... день нападения тёмных эльфов на Асгард, в который погибла моя мать..."""
            )
            Thread.sleep(500)
            rocket.dialog("Ну ты серьёзно?! У нас тут судьба Вселенной так то решается, а он киснуть вздумал.")
            Thread.sleep(500)
            println()
            output(
                """В этот момент к Тору и Ракете подходят Фригга - мать Тора, королева Асгарда, и Джейн Фостер - возлюбленная
Тора, которая была заражена Эфиром (${realitystone.stone}).
        """
            )
            val frigga = Person("Фригга")
            val jane = Person("Джейн Фостер")
            Thread.sleep(500)
            thor.dialog("Мама... Джейн...")
            frigga.dialog("Ты совсем не тот Тор, которого я знаю, верно?")
            Thread.sleep(500)
            thor.dialog("Да, это я, но из другой Вселенной. У нас там здоровяк фиолетовый полвселенной стёр, поэтому нам нужен камень.")
            Thread.sleep(500)
            frigga.dialog("Да, я сразу всё поняла")
            Thread.sleep(500)
            jane.dialog("Тор...")
            Thread.sleep(500)
            thor.dialog("Джейн... Послушай, я знаю, что в тебя вселился Эфир, мы сейчас его достанем, он нам как раз нужен.")
            Thread.sleep(500)
            rocket.dialog("Короче, у нас есть эта штука - экстрактор, с помощью него мы исцелим отсюда и свалим, да же, Тор?")
            Thread.sleep(500)
            thor.dialog("Подожди, Ракета. Мама, я должен тебя предупредить: в этот день прибудут эльфы и попытаются тебя убить...")
            Thread.sleep(500)
            rocket.dialog("ОЙ ГОСПОДИ, ну начинается снова! Мне что, одному доставать эфир?")
            Thread.sleep(500)
            getrealitystone()
            if (pim.itemvalue == 0.0 && vibranium.itemvalue == 0.0) {
                return
            }
            rocket.dialog("Ну наконец-то! Получилось! Тор, сматываемся быстрее отсюда")
            Thread.sleep(500)
            thor.dialog("Мама, послушай меня: ты погибнешь от...")
            Thread.sleep(500)
            frigga.dialog(
                """Тор, не стоит... Прошлое своей Вселенной ты уже никак не изменишь, а я останусь
тут, исполнять свой долг - защищать Асгард ценой даже собственной жизни. пусть произойдет то, что должно произойти.
"""
            )
            Thread.sleep(500)
            thor.dialog("Ладно, прощайте тогда...")
            println()
            Thread.sleep(500)
            output("Попрощавшись с Джейн и Фриггой, Тор и Ракета покидают Асгард с ${realitystone.stone}.")
            claimrealitystone = true
            println("Прогресс сохранён!")
        }
        buystone(realitystone)
        var mainactions: List<Pair<String, () -> Boolean>> = listOf(
            "${vibranium.itemname}" to { buystoneforvibranium(realitystone) },
            "${pim.itemname}" to { buystoneforpim(realitystone) },
            "Выйти из данного меню (не хватает ресурсов)" to { println("К сожалению вы не выбрали ресурс для получения ${realitystone.stone}, однако вы всегда можете вернуться"); false }
        )
        menu(mainactions)
    }
    var claimsoulstone = false
    fun tosoulstone(){
        if (claimsoulstone == false){
            println("2014, Вормир")
            Thread.sleep(500)
            sokol.dialog("Мрачное местечко... Небула точно не ошиблась с координатами?")
            Thread.sleep(500)
            widow.dialog("Она не могла ошибиться. Где то тут должен быть камень")
            Thread.sleep(500)
            output("*Наташа и Клинт поднимаются по каменной лестнице, где их встречает подозрительная фигура в плаще*")
            Thread.sleep(500)
            val redskull = Person("Красный Череп")
            Thread.sleep(500)
            redskull.dialog("Добро пожаловать на Вормир, ${widow.name} и ${sokol.name}.")
            widow.dialog("Откуда ты знаешь наши имена? Ты кто вообще?")
            Thread.sleep(500)
            redskull.dialog("Я - Красный Череп, сосланный сюда, на Вормир, охранять ${soulstone.stone}.")
            Thread.sleep(500)
            redskull.dialog("Мое проклятье - знать всё про всех, кто сюда приходит.")
            Thread.sleep(500)
            sokol.dialog("Это конечно всё очень интересно, но скажи нам, где лежит камешек.")
            Thread.sleep(500)
            redskull.dialog("Так вот он, прямо перед вами. Однако чтобы им завладеть, надо заплатить большую цену...")
            Thread.sleep(500)
            widow.dialog("Ну и что нужно взамен камню? Вибраниум? Частицы Пима?")
            Thread.sleep(500)
            redskull.dialog("""О нет... В обмен на камень вы должны пожертвовать душой любимого человека.
                |То есть как у нас это говорят - душу за душу.
            """.trimMargin())
            Thread.sleep(500)
            output("После этого ${widow.name} вспоминает, что Танос здесь был со своей приёмной дочерью")
            Thread.sleep(500)
            sokol.dialog("Чего? Душу за душу? Не пудри мозги, скажи прямо, какой ресурс тебе нужен.")
            Thread.sleep(500)
            widow.dialog("Клинт, он не врёт. Танос отправлялся сюда с Гаморой, а вернулся уже без неё")
            Thread.sleep(1000)
            sokol.dialog("Выходит, уйдёт отсюда только один из нас?")
            Thread.sleep(500)
            widow.dialog("Выходит, что так...")
            Thread.sleep(1000)
            println("Выберите персонажа, которым вы пожертвуете ради ${soulstone.stone}:")
            val soulactions = listOf(
                "${widow.name}" to {widow.health-=widow.health; false},
                "${sokol.name}" to {sokol.health-=sokol.health; false},
                "Показатели ${widow.name}" to { avengerstats(person = widow); true },
                "Показатели ${sokol.name}" to { avengerstats(person = sokol); true },
                "Посмотреть суммарные характеристики Мстителей" to {seestats(); true},
                "Показатели ${thanos.evilname}" to { enemystats(person = thanos); true }
            )
            menu(soulactions)
            if (widow !in LifeAvengers){
                output("""Ослепительная оранжевая вспышка на миг поглощает всё вокруг...
Когда свет гаснет, шум ветра на вершине стихает. Клинт с трудом открывает глаза:
он лежит на дне старой деревянной лодки, мерно покачивающейся на тёмной воде у подножия скалы.
""")
                Thread.sleep(1000)
                sokol.dialog("*тяжело дышит и оглядывается по сторонам* Наташа?.. Наташа, где ты?!")
                Thread.sleep(500)
                output("""
    Ответом ему служит лишь глухая тишина Вормира. Клинт медленно опускает взгляд
    и разжимает кулак — на его ладони тёплым оранжевым светом пульсирует ${soulstone.stone}.
""".trimIndent())
                Thread.sleep(1000)
                sokol.dialog("*сжимая камень* Я не дам твоей жертве пропасть зря. Мы вернём всех. Обещаю.")
            }
            if (sokol !in LifeAvengers){
                output("""
    Небо над Вормиром разрывает яркая вспышка, и мир на секунду погружается в тишину...
    Наташа приходит в себя в небольшой лодке посреди холодного зеркального озера у подножия горы.
""".trimIndent())
                Thread.sleep(1000)
                widow.dialog("*резко садится, хватаясь за голову* Клинт?! Нет-нет-нет, только не так...")
                Thread.sleep(500)
                output("""
    Она вглядывается в пустую вершину скалы наверху, но там никого нет.
    Вдруг её ладонь обжигает странное тепло — внутри светится ${soulstone.stone}.
""".trimIndent())
                Thread.sleep(1000)
                widow.dialog("*вытирая слёзы* Прости меня, друг... Но я закончу то, что мы начали. Ради твоей семьи.")
            }
            claimsoulstone = true
            println("Прогресс сохранён!")
        }
        stoneslist.add(soulstone)
        println("${soulstone.stone} был получен. Камней бесконечности: ${stoneslist.size}/6. Возвращаемся в главное меню...")

    }
    var avalaibleskip = true
    var wasskip = true
    fun skip(){
        if (avalaibleskip == true){
            println("Вы действительно хотите пропустить один день ради получения ${pim.itemname}? [ДА/НЕТ]")
            val isskip = readln().lowercase()
            if (isskip == "да"){
                for (av in LifeAvengers) {
                    av.energy = (av.energy * 0.8).toInt()
                }
                println("""Тони Старк и Капитан Америка в связи с недостатком Частиц Пима возвращаются в прошлое,
                    |на военную базу. Энергия всех Мстителей уменьшается на 20%, вы получаете 10 Частиц Пима
                """.trimMargin())
                pim.itemvalue += 10
                avalaibleskip = false
                wasskip = false
            }else{
                println("Возвращение в меню...")
                wasskip = true
            }
        }else{
            println("Вы уже использовали перемещение в прошлое для добычи Частиц Пима.")
            wasskip = true
        }
    }
    //начисление стартовых ресурсов
    vibranium.itemvalue = 100.0
    pim.itemvalue = 10.0
    mainactions = mutableListOf(
        "Отправиться за ${powerstone.stone}" to {topowerstone(); false},
        "Отправиться за ${mindstone.stone}" to {tomindstone(); false},
        "Отправиться за ${spacestone.stone}" to {tospacestone(); false},
        "Отправиться за ${timestone.stone}" to {totimestone(); false},
        "Отправиться за ${realitystone.stone}" to {torealitystone(); false},
        "Отправиться за ${soulstone.stone}" to {tosoulstone(); false},
        "Посмотреть баланс" to {balance(); true},
        "Посмотреть Мстителей" to {seeLifeAvengers(); true},
        "Посмотреть суммарные характеристики команды Мстителей" to {seestats(); true},
        "Посмотреть характеристики Таноса" to {enemystats(thanos); true},
        "Забранные камни" to {seestones(); true},
        "Пропустить 1 день за Частицы Пима" to {skip(); wasskip}
    )
    starting()
    continuing()
    rulesaboutgame()

    for (day in 1..8 ) {
        println("День ${day}")
        menu(mainactions)
        if (powerstone in stoneslist) {
            mainactions.removeAll{it.first == "Отправиться за ${powerstone.stone}"}
        }
        if (mindstone in stoneslist) {
            mainactions.removeAll{it.first == "Отправиться за ${mindstone.stone}"}
        }
        if (spacestone in stoneslist) {
            mainactions.removeAll{it.first == "Отправиться за ${spacestone.stone}"}
        }
        if (timestone in stoneslist) {
            mainactions.removeAll{it.first == "Отправиться за ${timestone.stone}"}
        }
        if (realitystone in stoneslist) {
            mainactions.removeAll{it.first == "Отправиться за ${realitystone.stone}"}
        }
        if (soulstone in stoneslist) {
            mainactions.removeAll{it.first == "Отправиться за ${soulstone.stone}"}
        }
        val diffVibr = ((vibranium.itemvalue - fixedvibranium) * 100).roundToInt() / 100.0
        val diffPim = ((pim.itemvalue - fixedpim) * 100).roundToInt() / 100.0
        listoffixedvibr.add(diffVibr)
        listoffixedpim.add(diffPim)
        fixedvibranium = vibranium.itemvalue
        fixedpim = pim.itemvalue
        if (pim.itemvalue == 0.0 || vibranium.itemvalue == 0.0 || stoneslist.size == 6){
            break
        }
    }

    if (pim.itemvalue == 0.0 || vibranium.itemvalue == 0.0 || stoneslist.size < 6){
        badending1()
        lose(mark.elapsedNow().toDouble(DurationUnit.SECONDS))
        return
    }else{
        afterclaiming()
        if (thanos.maxhealth <= avengersteam.maxhealth && thanos.damage <= avengersteam.damage && thanos.energy <= avengersteam.energy){
            happyend()
            win(mark.elapsedNow().toDouble(DurationUnit.SECONDS))
        }else{
            badending2()
            lose(mark.elapsedNow().toDouble(DurationUnit.SECONDS))
        }
    }
}
