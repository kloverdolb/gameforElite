//плохая концовка 1 - если не собраны все камни бесконечности за 8 дней или закончились ресурсы
fun badending1(){
    Thread.sleep(500)
    output("После продолжительного и сложного хрононалёта Мстители возвращаются в 2023 год")
    Thread.sleep(500)
    iron.dialog("Так, ну что ж, все 6 достали?")
    Thread.sleep(500)
    cap.dialog("К сожалению нет... мы смогли собрать лишь ${stoneslist.map{it.stone}.joinToString(", ")}.")
    Thread.sleep(500)
    var losedstones = (allstones-stoneslist)
    var losedavengers = (allavengers-LifeAvengers)
    if (losedavengers.size > 0) {
        thor.dialog("А ещё мы в этом приключении потеряли ${losedavengers.map { it.name }.joinToString(", ")}...")
        Thread.sleep(500)
        hulk.dialog("Всмысле... Это как, потеряли?...")
        Thread.sleep(500)
        iron.dialog("Брюс, мы все понимали, что это билет в один конец...")
        Thread.sleep(500)

    }
    hulk.dialog("А где оставшиеся ${losedstones.map { it.stone }.joinToString(", ")}?")
    Thread.sleep(500)
    cap.dialog("Увы, нам не хватило ресурсов...")
    roud.dialog("РЕСУРСОВ?! У нас же была половина запасов вибраниума со всей Земли...")
    Thread.sleep(500)
    thor.dialog("И что? Выходит мы не сможем заполнить перчатку бесконечности и всех воскресить?")
    Thread.sleep(500)
    output("...")
    Thread.sleep(500)
    iron.dialog("Выходит, что так...")
    Thread.sleep(500)
    rocket.dialog("Эх, ну хоть попытались что ли...")
    println()
    Thread.sleep(500)
    output("После шокирущей новости все Мстители разочаровались в проделанной работе и разъехались по своим местам.")
    Thread.sleep(500)
    output("Сразу после трагичной новости Тони Старк вернулся в домик на берегу озера.")
    Thread.sleep(500)
    output("*Смотрит на совместную фотографию с Питером Паркером*")
    Thread.sleep(500)
    iron.mind("Прости, паучок... И в этот раз мы не смогли...")
    println("${red}ВЫ ПРОИГРАЛИ (ВЫ НЕ УСПЕЛИ СОБРАТЬ ВСЕ КАМНИ БЕСКОНЕЧНОСТИ/ЗАКОНЧИЛИСЬ РЕСУРСЫ)${reset}")
}
//если все камни собраны - идет продолжение
fun afterclaiming(){
    Thread.sleep(500)
    output("После продолжительного и сложного хрононалёта Мстители возвращаются в 2023 год")
    Thread.sleep(500)
    println("Пропустить часть сюжета?[ДА/НЕТ]")
    val after = readln().lowercase()
    if (after == "нет") {
        iron.dialog("Ну что, все 6 достали?")

        Thread.sleep(500)
        roud.dialog("Выходит, фокус сработал?")
        Thread.sleep(500)
        hulk.dialog("Погодите, а где...")
        if (widow !in LifeAvengers) {
            hulk.dialog("... Наташа? Клинт, где она?")
            Thread.sleep(500)
            sokol.dialog("*опускает голову и молчит, сжимая кулаки*")
            Thread.sleep(500)
            hulk.dialog("Нет... Клинт, скажи, что это неправда.")
            Thread.sleep(500)
            sokol.dialog("Я должен был прыгнуть вместо неё...")
            Thread.sleep(500)
        }
        if (sokol !in LifeAvengers) {
            hulk.dialog("Клинт? Он что, потерялся?")
            Thread.sleep(500)
            widow.dialog("*вытирая слёзы* он не вернётся. Такова цена этого злополучного камня...")
            Thread.sleep(500)
            hulk.dialog("Дадим обещание, что мы вернём семью Клинта, ради которой он и решился на эту операцию.")
            Thread.sleep(500)
        }
        if (nebula !in LifeAvengers) {
            roud.dialog("А ещё... Небула тоже не вернулась... У нас была стычка с Квиллом")
            Thread.sleep(500)
            rocket.dialog(
                """НЕБУЛА! *тяжело вздыхая* Она была единственной, кто остался у меня
            |после потери других стражей галактики...
            """.trimMargin()
            )
            Thread.sleep(500)
        }
        output(
            """Спустя нескольких минут скорби Мстители отчётливо понимали: необходимо как можно быстрее создать
        |перчатку Бесконечности для спасения мира.
        """.trimMargin()
        )
        Thread.sleep(1000)
        output("Спустя пару часов Тони Старк совместно с Ракетой создали перчатку и поместили в неё все найденные камни.")
        Thread.sleep(500)
        iron.dialog("Итак, осталось теперь решить, кто будет щёлкать")
        Thread.sleep(500)
        hulk.dialog("Щёлкать буду я. Вспомните: энергия камней чуть не убила самого Таноса, вы все погибнете")
        Thread.sleep(500)
        cap.dialog("А ты уверен, что выживешь?")
        Thread.sleep(500)
        hulk.dialog("Нет, но излучение в основном гамма. А моя форма Халка как раз излучает такой поток частиц.")
        Thread.sleep(500)
    }
    output("${hulk.avengername} сильно обжигает руку, однако ему удается совершить щелчок.")
    Thread.sleep(500)
    iron.dialog("Брюс, ты как?")
    Thread.sleep(500)
    hulk.dialog("*еле двигая телом* ну как, получилось?")
    Thread.sleep(500)
    iron.dialog("Ещё неизвестно...")
    Thread.sleep(500)
    output("Внезапно территория, на которой находились Мстители, подвергается обстрелу с неба.")
    output("Мстители не успевают ничего понять.")
    Thread.sleep(1000)
    cap.dialog("Все живы? Тони, что это может быть?")
    Thread.sleep(500)
    iron.dialog("Нет... Не может этого быть...")
    Thread.sleep(500)
    println()
    output("И вдруг из груды обломков после бомбежки появляется... Танос!")
    Thread.sleep(1000)
    thanos.dialog("Старк... Неужели вы думали, что сможете всё изменить, вернув камни?")
    Thread.sleep(500)
    iron.dialog("Да ты прям нас раскусил, но в этот раз у тебя ничего не получится.")
    Thread.sleep(500)
    thanos.dialog("""Я думал: "вот сотру половину Вселенной, а другая будет процветать". 
Однако вы доказали, что такого не будет, пока есть вы - люди. Поэтому в этот раз я уничтожу не 
половину Вселенной, а её всю!
    """)
    Thread.sleep(500)
    thor.dialog("Тони, мы с ними нашим составом не справимся.")
    Thread.sleep(500)
    println()
    output("В эту минуту Мстители понимают, что обречены, как вдруг...")
    Thread.sleep(500)
    output("*появление в воздухе порталов*")
    Thread.sleep(500)
    cap.mindAsAvenger("Неужто получилось...")
    Thread.sleep(500)
    println()
    output("""Благодаря щелчку Халка исчезнувшие 5 лет назад Мстители вернулись благодаря
порталам Доктора Стрэнджа (в том числе и Питер Паркер).
    """)
    Thread.sleep(500)
    spider.dialog("Мистер Старк! Вы живы!")
    Thread.sleep(500)
    iron.dialog("Питер! вы как здесь оказались?")
    Thread.sleep(500)
    spider.dialog("Ну короче мы появились и этот колдун в плаще...")
    Thread.sleep(500)
    iron.dialog("То есть Стрэндж?")
    Thread.sleep(500)
    spider.dialog("Да, вот он в общем за 15 минут смог наколдовать эти порталы и всех нас выпустить!")
    Thread.sleep(500)
    println()
    thanos.dialog("Довольно разговоров. Даже возвращение ваших войск вам ничего не даст.")
    Thread.sleep(500)
    thor.dialog("А вот это мы сейчас и узнаем")
    Thread.sleep(500)
    cap.dialog("Мстители... К бою!")
    Thread.sleep(500)
    avengersteam = Avengers(
        name = "Команда Мстителей",
        damage = LifeAvengers.sumOf { it.damage } * 2,
        maxhealth = LifeAvengers.sumOf { it.health } * 2,
        avengername = "Команда Мстителей",
        energy = LifeAvengers.sumOf { it.energy } * 2,
        info = "-"
    )
    println("Силы Мстителей увеличились в 2 раза!")
    Thread.sleep(500)
    var endactions = listOf(
        "В БОЙ" to {avengersorthanos(); false}
    )
    menu(endactions)
}
fun lose(numb: Double){
    val maxi = listoffixedvibr.max()
    val mini = listoffixedvibr.min()
    val maxindex = listoffixedvibr.indexOf(maxi)
    val minindex = listoffixedvibr.indexOf(mini)
    println("""                 ${red}ПОРАЖЕНИЕ${reset}           """)
    println("""Итоги прохождения:""")
    println("""Камней собрано: ${stoneslist.size}/6""")
    println("Лучший день: день ${maxindex+1} (разница с предыдущим днём: $maxi Осколков Вибраниума)")
    println("Худший день: день ${minindex+1} (разница с предыдущим днём: $mini Осколков Вибраниума)")
    println("Игровое время: ${(numb / 60).toInt()} мин. ${(numb % 60).toInt()} сек.")
}

fun avengersorthanos(){
    println("Сравнение характеристик Таноса и Мстителей:")
    println("""Команда Мстителей:
    ● Здоровье: ${avengersteam.health}/${avengersteam.maxhealth}
    ● Урон: ${avengersteam.damage}
    ● Энергия: ${avengersteam.energy}
    """)
    Thread.sleep(1000)
    println("""Танос:
    ● Здоровье: ${thanos.health}/${thanos.maxhealth}
    ● Урон: ${thanos.damage}
    ● Энергия: ${thanos.energy}
    """)
    Thread.sleep(1000)
}
//вторая плохая концовка (если силы таноса > сил мстителей)
fun badending2(){
    output("""В процессе ожесточенной борьбы Мстителей за сохранение перчатки с камнями Бесконечности
        |Танос всё таки смог выхватить перчатку несмотря на серьёзное сопротивление.
    """.trimMargin())
    thanos.dialog("Вот и всё. Для вашей планеты всё кончено.")
    Thread.sleep(500)
    spider.dialog("Мистер Старк, что происходит? Не говорите только, что я снова исчезну и вы тоже...")
    Thread.sleep(500)
    iron.dialog("Увы...")
    Thread.sleep(500)
    thanos.dialog("${red}Я - сама неотвратимость!${reset}")
    output("*раздаётся яркая вспышка из-за щелка перчаткой*")
    Thread.sleep(1500)
    println()
    output("""Повторный щелчок Таноса привёл к неисправимым последствиям: в этот раз
        |исчезла вся жизнь во Вселенной. Рассыпаясь в пыль, Тони Старк прежде всего думал о Питере и 
        |своей семье: Пеппер и Морган, которых он не смог сохранить. 
        |На месте старого мира единственный выживший Танос воздвиг новую реальность, где никто
        |и никогда не вспомнит о прошлом.
    """.trimMargin())
    println("${red}ВЫ ПРОИГРАЛИ (СИЛЫ ТАНОСА ПРЕВЗОШЛИ СИЛ МСТИТЕЛЕЙ)${reset}")
}
//победная концовка
fun happyend(){
    output("""После продолжительной битвы между силами Мстителей и армией Таноса 
        |перчатка с камнями Бесконечностей упала прямо на середину поля боя.
    """.trimMargin())
    Thread.sleep(500)
    iron.dialog("Питер, Тор, не дайте ему завладеть перчаткой!")
    Thread.sleep(500)
    spider.dialog("Мы пытаемся, но он слишком силён.")
    Thread.sleep(500)
    thanos.dialog("Напрасно сопротивляетесь... Я в любом случае изменю реальность!")
    Thread.sleep(500)
    println()
    output("Спустя пару мгновений Таносу удается завладеть перчаткой Бесконечности.")
    Thread.sleep(500)
    thanos.dialog("Вот и всё. Я сама неотвратимо...")
    Thread.sleep(500)
    thanos.mind("*шокированно смотря на перчатку* куда делись камни бесконечности?")
    Thread.sleep(500)
    println()
    output("""в тот момент, когда Танос, казалось, завладел перчаткой, Тони Старк сумел 
        |перенести все камни на ней на запасную перчатку на своей руке.
    """.trimMargin())
    Thread.sleep(500)
    iron.dialog("*еле сдерживаясь от излучения радиации* а я так... просто... ${red}ЖЕЛЕЗНЫЙ ЧЕЛОВЕК${reset}")
    output("*яркая вспышка от щелчка ослепляет всех вокруг*")
    Thread.sleep(1500)
    output("Спустя несколько минут Старк очнулся")
    Thread.sleep(500)
    spider.dialog("Старк! Слышите меня? У нас получилось! Танос и его армия исчезли!")
    Thread.sleep(500)
    iron.dialog("Паучок... Родной...")
    Thread.sleep(500)
    spider.dialog("Мистер Старк, вы чего... Не покидайте нас!")
    Thread.sleep(1500)
    output("""В конце концов Железный человек погиб из-за переизлучения радиацией. После его щелчка
        |Танос и его армия пропали, а на Землю вернулись люди, которые пропали 5 лет назад. Несмотря
        |на вcё своё высокомерие и эгоизм Тони Старк пожертвовал своей жизнью ради спасения Вселенной
        |и своих близких. Со временем жизнь на Земле наладилась и восстановилась, однако поступок 
        |инженера-гения никогда не будет забыт.
    """.trimMargin())

}
fun win(numb: Double){
    val maxi = listoffixedvibr.max()
    val mini = listoffixedvibr.min()
    val maxindex = listoffixedvibr.indexOf(maxi)
    val minindex = listoffixedvibr.indexOf(mini)
    println("""                         ${green}ПОБЕДА${reset}           """)
    println("""Итоги прохождения:""")
    println("""Камней собрано: ${stoneslist.size}/6""")
    println("Лучший день: день ${maxindex+1} (разница с предыдущим днём: $maxi Осколков Вибраниума)")
    println("Худший день: день ${minindex+1} (разница с предыдущим днём: $mini Осколков Вибраниума)")
    println("Игровое время: ${(numb/60).toInt()} мин. ${(numb%60).toInt()} сек.")
}