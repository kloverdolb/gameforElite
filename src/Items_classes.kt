
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
//создание объектов-камней бесконечности
val timestone = Stones("Камень времени", green)
val spacestone = Stones("Камень пространства", blue)
val mindstone = Stones("Камень разума", yellow)
val realitystone = Stones("Камень реальности", red)
val powerstone = Stones("Камень силы", purple)
val soulstone = Stones("Камень души", orange)
