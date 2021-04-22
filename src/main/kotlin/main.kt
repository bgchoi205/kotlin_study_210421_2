import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var articles = mutableListOf<Article>()

fun getArticleById(id : Int):Article?{
    var articleToDel : Article? = null

    for(article in articles){
        if(article.id == id){
            articleToDel = article
            return articleToDel
            continue
        }
    }
    return null

}

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
            val regDate = Util.getRegDate()
            val updateDate = Util.getRegDate()

            val article = Article(id, title, body, regDate, updateDate)
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
        else if(command.startsWith("article delete ")){
            val id = command.trim().split(" ")[2].toInt()

            var articleToDel = getArticleById(id)

            if(articleToDel == null){
                println("존재하지 않는 게시물 입니다.")
                continue
            }
            articles.remove(articleToDel)
            println("${id}번 게시물이 삭제되었습니다.")

        }
        else if(command.startsWith("article modify ")){
            val id = command.trim().split(" ")[2].toInt()

            var articleToMod = getArticleById(id)

            if(articleToMod == null){
                println("${id}번 게시물은 존재하지 않습니다.")
                continue
            }

            print("새 제목 : ")
            articleToMod.title = readLine()!!.trim()
            print("새 내용 : ")
            articleToMod.body = readLine()!!.trim()
            articleToMod.updateDate = Util.getRegDate()

            println("${id}번 게시물 수정이 완료되었습니다.")
        }
        else if(command.startsWith("article detail ")){
            val id = command.trim().split(" ")[2].toInt()

            var articleToDetail = getArticleById(id)

            if(articleToDetail == null){
                println("${id}번 게시물은 존재하지 않습니다.")
                continue
            }

            println("번호 : ${articleToDetail.id}")
            println("작성날짜 : ${articleToDetail.regDate}")
            println("갱신날짜 : ${articleToDetail.updateDate}")
            println("제목 : ${articleToDetail.title}")
            println("내용 : ${articleToDetail.body}")

        }

    }


    println("==프로그램 끝==")
}

data class Article(
    val id : Int,
    var title : String,
    var body : String,
    val regDate : String,
    var updateDate : String
){

}

object Util {
    fun getRegDate(): String {
        var now = LocalDateTime.now()

        var Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초"))
        return Strnow
    }
}