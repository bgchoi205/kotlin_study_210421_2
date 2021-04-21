import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
명령어
-------------
article write
aa
asdf
article write
qq
qwer
article write
zz
zxcv
article list

 */

fun main() {
    println("==프로그램 시작==")

    var lastId = 0
    var articles = mutableListOf<Article>()

    while(true){
        print("명령어 입력 : ")
        val command = readLine()!!.trim()
        if(command == "exit"){
            break
        }
        else if(command == "article write"){
            val id = lastId + 1
            print("제목 : ")
            val title = readLine()!!.trim()
            print("내용 : ")
            val body = readLine()!!.trim()
            val regDate = getRegDate()

            val article = Article(id, title, body, regDate)
            articles.add(article)

            println("$id 번 게시물이 등록되었습니다.")

            lastId = id
        }
        else if(command == "article list"){
            println("번호 / 날짜 / 제목")
            for(article in articles){

                println("${article.id}/ ${article.regDate} / ${article.title}")
            }
        }

    }


    println("==프로그램 끝==")
}

data class Article(
    val id : Int,
    val title : String,
    val body : String,
    val regDate : String
){

}

fun getRegDate() : String{
    var now = LocalDateTime.now()

    var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초"))
    return Strnow
}
